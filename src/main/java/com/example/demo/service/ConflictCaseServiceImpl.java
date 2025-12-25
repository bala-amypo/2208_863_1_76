package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ConflictCase;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.service.ConflictCaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConflictCaseServiceImpl implements ConflictCaseService {

    private final ConflictCaseRepository conflictCaseRepository;

    // ✅ EXACT constructor expected by tests
    public ConflictCaseServiceImpl(ConflictCaseRepository conflictCaseRepository) {
        this.conflictCaseRepository = conflictCaseRepository;
    }

    @Override
    public ConflictCase createCase(ConflictCase conflictCase) {
        return conflictCaseRepository.save(conflictCase);
    }

    @Override
    public ConflictCase updateCaseStatus(Long id, String status) {
        ConflictCase c = getCaseById(id);
        c.setStatus(status);
        return conflictCaseRepository.save(c);
    }

    @Override
    public ConflictCase getCaseById(Long id) {
        return conflictCaseRepository.findById(id)
                .orElseThrow(() -> new ApiException("Case not found"));
    }

    @Override
    public List<ConflictCase> getAllCases() {
        return conflictCaseRepository.findAll();
    }

    @Override
    public List<ConflictCase> getCasesByPerson(Long personId) {
        return conflictCaseRepository
                .findByPrimaryPersonIdOrSecondaryPersonId(personId, personId);
    }
}
