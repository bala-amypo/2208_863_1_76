package com.example.demo.service;

import com.example.demo.model.ConflictCase;
import java.util.List;

public interface ConflictCaseService {

    ConflictCase createCase(ConflictCase conflictCase);

    ConflictCase updateCaseStatus(Long id, String status);

    ConflictCase getCaseById(Long id);

    List<ConflictCase> getCasesByPerson(Long personId);

    List<ConflictCase> getAllCases();
}
