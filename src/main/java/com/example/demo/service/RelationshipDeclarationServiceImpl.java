package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.RelationshipDeclaration;
import com.example.demo.repository.RelationshipDeclarationRepository;
import com.example.demo.service.RelationshipDeclarationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipDeclarationServiceImpl implements RelationshipDeclarationService {

    private final RelationshipDeclarationRepository repository;

    public RelationshipDeclarationServiceImpl(RelationshipDeclarationRepository repository) {
        this.repository = repository;
    }

    // ✅ REQUIRED by interface
    @Override
    public RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration) {

        if (declaration.getPersonId() == null) {
            throw new ApiException("Person required");
        }

        declaration.setIsVerified(false);
        return repository.save(declaration);
    }

    // ✅ REQUIRED by interface
    @Override
    public RelationshipDeclaration verifyDeclaration(Long id, boolean verified) {

        RelationshipDeclaration declaration = repository.findById(id)
                .orElseThrow(() -> new ApiException("Declaration not found"));

        declaration.setIsVerified(verified);
        return repository.save(declaration);
    }

    // ✅ REQUIRED by interface
    @Override
    public RelationshipDeclaration getDeclarationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Declaration not found"));
    }

    // ✅ REQUIRED by interface
    @Override
    public List<RelationshipDeclaration> getDeclarationsByPerson(Long personId) {
        return repository.findAll()
                .stream()
                .filter(d -> d.getPersonId().equals(personId))
                .toList();
    }

    // ✅ REQUIRED by interface
    @Override
    public List<RelationshipDeclaration> getAllDeclarations() {
        return repository.findAll();
    }
}
