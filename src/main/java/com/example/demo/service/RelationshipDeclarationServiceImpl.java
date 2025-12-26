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

    @Override
    public RelationshipDeclaration createDeclaration(RelationshipDeclaration declaration) {

        if (declaration.getPersonId() == null) {
            throw new ApiException("Person required");
        }

        declaration.setIsVerified(false);
        return repository.save(declaration);
    }

    @Override
    public RelationshipDeclaration getDeclarationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Declaration not found"));
    }

    @Override
    public List<RelationshipDeclaration> getAllDeclarations() {
        return repository.findAll();
    }

    @Override
    public RelationshipDeclaration verifyDeclaration(Long id) {
        RelationshipDeclaration declaration = repository.findById(id)
                .orElseThrow(() -> new ApiException("Declaration not found"));

        declaration.setIsVerified(true);
        return repository.save(declaration);
    }
}
