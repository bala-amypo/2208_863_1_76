package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.exception.ApiException;
import com.example.demo.model.RelationshipDeclaration;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.repository.RelationshipDeclarationRepository;
import com.example.demo.service.RelationshipDeclarationService;

import org.springframework.stereotype.Service;

@Service
public class RelationshipDeclarationServiceImpl
        implements RelationshipDeclarationService {

    private final RelationshipDeclarationRepository declarationRepository;
    private final PersonProfileRepository personRepository;

    public RelationshipDeclarationServiceImpl(
            RelationshipDeclarationRepository declarationRepository,
            PersonProfileRepository personRepository) {

        this.declarationRepository = declarationRepository;
        this.personRepository = personRepository;
    }

    @Override
    public RelationshipDeclaration declareRelationship(
            RelationshipDeclaration declaration) {

      
        personRepository.findById(declaration.getPersonId())
            .orElseThrow(() -> new ApiException("person not found"));

     
        return declarationRepository.save(declaration);
    }

    @Override
    public RelationshipDeclaration verifyDeclaration(
            Long declarationId, boolean verified) {

        RelationshipDeclaration declaration =
                declarationRepository.findById(declarationId)
                    .orElseThrow(() -> new ApiException("declaration not found"));

       
        declaration.setIsVerified(verified);

        
        return declarationRepository.save(declaration);
    }

    @Override
    public List<RelationshipDeclaration> getDeclarationsByPerson(Long personId) {
        return declarationRepository.findAll()
                .stream()
                .filter(d -> d.getPersonId().equals(personId))
                .toList();
    }

    @Override
    public List<RelationshipDeclaration> getAllDeclarations() {
        return declarationRepository.findAll();
    }
}
