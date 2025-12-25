package com.example.demo.controller;

import com.example.demo.model.RelationshipDeclaration;
import com.example.demo.service.RelationshipDeclarationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relationships")
public class RelationshipDeclarationController {

    private final RelationshipDeclarationService service;

    public RelationshipDeclarationController(RelationshipDeclarationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RelationshipDeclaration> create(
            @RequestBody RelationshipDeclaration declaration) {
        return ResponseEntity.ok(service.createDeclaration(declaration));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelationshipDeclaration> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDeclarationById(id));
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<RelationshipDeclaration>> getByPerson(
            @PathVariable Long personId) {
        return ResponseEntity.ok(service.getDeclarationsByPerson(personId));
    }
}
