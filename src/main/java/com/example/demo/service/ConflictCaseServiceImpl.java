package com.example.demo.service.impl;

import com.example.demo.model.ConflictCase;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.repository.ConflictFlagRepository;
import com.example.demo.service.ConflictCaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



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
public Optional<ConflictCase> getCaseById(Long id) {
    return conflictCaseRepository.findById(id);
}



    @Override
    public List<ConflictCase> getCasesByPerson(Long personId) {
        return conflictCaseRepository.findAll();
    }

    // ✅ THIS WAS MISSING
    @Override
    public ConflictCase updateCaseStatus(Long id, String status) {
        ConflictCase conflictCase = conflictCaseRepository.findById(id).orElse(null);
        if (conflictCase != null) {
            conflictCase.setStatus(status);
            return conflictCaseRepository.save(conflictCase);
        }
        return null;
    }

    @Override
    public List<ConflictCase> getAllCases() {
        return conflictCaseRepository.findAll();
    }
}
