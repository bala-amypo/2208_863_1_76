package com.example.demo.service;

import java.util.List;
import com.example.demo.model.RelationshipDeclaration;

public interface RelationshipDeclarationService {

    RelationshipDeclaration declareRelationship(RelationshipDeclaration declaration);

    RelationshipDeclaration verifyDeclaration(Long declarationId, boolean verified);

    List<RelationshipDeclaration> getDeclarationsByPerson(Long personId);

    List<RelationshipDeclaration> getAllDeclarations();
}
