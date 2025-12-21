package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ConflictCase;

public interface ConflictCaseService {

    ConflictCase createCase(ConflictCase conflictCase);

    ConflictCase updateCaseStatus(Long caseId, String status);

    ConflictCase getCaseById(Long id);

    List<ConflictCase> getCasesByPerson(Long personId);

    List<ConflictCase> getAllCases();
}
