package com.example.demo.controller;

import com.example.demo.model.ConflictCase;
import com.example.demo.service.ConflictCaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conflicts")
public class ConflictCaseController {

    private final ConflictCaseService service;

    public ConflictCaseController(ConflictCaseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ConflictCase> create(@RequestBody ConflictCase conflictCase) {
        return ResponseEntity.ok(service.createCase(conflictCase));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConflictCase> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCaseById(id));
    }

    @GetMapping
    public ResponseEntity<List<ConflictCase>> getAll() {
        return ResponseEntity.ok(service.getAllCases());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ConflictCase> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(service.updateCaseStatus(id, status));
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<ConflictCase>> getByPerson(@PathVariable Long personId) {
        return ResponseEntity.ok(service.getCasesByPerson(personId));
    }
}
