package com.example.demo.service.impl;

import com.example.demo.model.ConflictCase;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.repository.ConflictFlagRepository;
import com.example.demo.service.ConflictCaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConflictCaseServiceImpl implements ConflictCaseService {

    private final ConflictCaseRepository conflictCaseRepository;
    private final ConflictFlagRepository conflictFlagRepository;

    public ConflictCaseServiceImpl(ConflictCaseRepository conflictCaseRepository,
                                   ConflictFlagRepository conflictFlagRepository) {
        this.conflictCaseRepository = conflictCaseRepository;
        this.conflictFlagRepository = conflictFlagRepository;
    }

    @Override
    public ConflictCase createCase(ConflictCase conflictCase) {
        return conflictCaseRepository.save(conflictCase);
    }

    @Override
    public ConflictCase getCaseById(Long id) {
        return conflictCaseRepository.findById(id).orElse(null);
    }

    @Override
    public List<ConflictCase> getCasesByPerson(Long personId) {
        return conflictCaseRepository.findAll(); // simple implementation
    }

    @Override
    public List<ConflictCase> getAllCases() {
        return conflictCaseRepository.findAll();
    }
}
