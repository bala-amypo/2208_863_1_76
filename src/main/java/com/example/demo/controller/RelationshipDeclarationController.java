package com.example.demo.controller;

import com.example.demo.model.PersonProfile;
import com.example.demo.service.PersonProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonProfileController {

    private final PersonProfileService service;

    public PersonProfileController(PersonProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PersonProfile> create(@RequestBody PersonProfile person) {
        return ResponseEntity.ok(service.createPerson(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonProfile> getById(@PathVariable Long id) {
        return service.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PersonProfile>> getAll() {
        return ResponseEntity.ok(service.getAllPersons());
    }

    @GetMapping("/lookup/{referenceId}")
    public ResponseEntity<PersonProfile> lookup(@PathVariable String referenceId) {
        return service.findByReferenceId(referenceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/relationship")
    public ResponseEntity<PersonProfile> updateRelationshipDeclared(
            @PathVariable Long id,
            @RequestParam boolean declared) {

        return ResponseEntity.ok(
                service.updateRelationshipDeclared(id, declared)
        );
    }
}
