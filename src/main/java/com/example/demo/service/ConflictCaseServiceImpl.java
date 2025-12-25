package com.example.demo.service.impl;

import com.example.demo.model.ConflictCase;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.service.ConflictCaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<ConflictCase> getCaseById(Long id) {
        return conflictCaseRepository.findById(id);
    }

    @Override
    public List<ConflictCase> getAllCases() {
        return conflictCaseRepository.findAll();
    }
}
