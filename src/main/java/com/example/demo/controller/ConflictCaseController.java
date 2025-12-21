package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ConflictCase;
import com.example.demo.service.ConflictCaseService;

@RestController
@RequestMapping("/api/conflict-cases")
public class ConflictCaseController {

    private final ConflictCaseService conflictCaseService;

    public ConflictCaseController(ConflictCaseService conflictCaseService) {
        this.conflictCaseService = conflictCaseService;
    }

    @PostMapping
    public ConflictCase createCase(@RequestBody ConflictCase conflictCase) {
        return conflictCaseService.createCase(conflictCase);
    }

    @PutMapping("/{id}/status")
    public ConflictCase updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return conflictCaseService.updateCaseStatus(id, status);
    }

    @GetMapping("/{id}")
    public ConflictCase getCaseById(@PathVariable Long id) {
        return conflictCaseService.getCaseById(id);
    }

    @GetMapping("/person/{personId}")
    public List<ConflictCase> getCasesByPerson(@PathVariable Long personId) {
        return conflictCaseService.getCasesByPerson(personId);
    }

    @GetMapping
    public List<ConflictCase> getAllCases() {
        return conflictCaseService.getAllCases();
    }
}
