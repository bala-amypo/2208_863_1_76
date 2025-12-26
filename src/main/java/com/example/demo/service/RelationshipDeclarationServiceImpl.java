package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.RelationshipDeclaration;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.repository.RelationshipDeclarationRepository;
import com.example.demo.service.RelationshipDeclarationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipDeclarationServiceImpl implements RelationshipDeclarationService {

    private final RelationshipDeclarationRepository repository;
    private final PersonProfileRepository personRepository;

    // ✅ REQUIRED by TESTS
    public RelationshipDeclarationServiceImpl(
            RelationshipDeclarationRepository repository,
            PersonProfileRepository personRepository
    ) {
        this.repository = repository;
        this.personRepository = personRepository;
    }

    @Override
    public RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration) {

        if (declaration == null || declaration.getPersonId() == null) {
            throw new ApiException("Person required");
        }

        // validate person exists (required by tests)
        personRepository.findById(declaration.getPersonId())
                .orElseThrow(() -> new ApiException("Person not found"));

        declaration.setIsVerified(false);
        return repository.save(declaration);
    }

    @Override
    public RelationshipDeclaration verifyDeclaration(Long id, boolean verified) {

        RelationshipDeclaration declaration = repository.findById(id)
                .orElseThrow(() -> new ApiException("Declaration not found"));

        declaration.setIsVerified(verified);
        return repository.save(declaration);
    }

    @Override
    public RelationshipDeclaration getDeclarationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Declaration not found"));
    }

    @Override
    public List<RelationshipDeclaration> getDeclarationsByPerson(Long personId) {
        return repository.findAll()
                .stream()
                .filter(d -> d.getPersonId() != null && d.getPersonId().equals(personId))
                .toList();
    }

    @Override
    public List<RelationshipDeclaration> getAllDeclarations() {
        return repository.findAll();
    }
}
