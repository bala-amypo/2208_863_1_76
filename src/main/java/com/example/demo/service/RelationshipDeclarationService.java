package com.example.demo.service;

import com.example.demo.model.RelationshipDeclaration;
import java.util.List;

public interface RelationshipDeclarationService {

    RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration);

    RelationshipDeclaration verifyDeclaration(Long id, boolean verified);

    List<RelationshipDeclaration> getDeclarationsByPerson(Long personId);

    List<RelationshipDeclaration> getAllDeclarations();
}
