package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ConflictCase;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.service.ConflictCaseService;

import java.util.List;

public class ConflictCaseServiceImpl implements ConflictCaseService {

    private final ConflictCaseRepository repository;

    public ConflictCaseServiceImpl(ConflictCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConflictCase createCase(ConflictCase conflictCase) {
        return repository.save(conflictCase);
    }

    @Override
    public ConflictCase updateCaseStatus(Long id, String status) {
        ConflictCase c = getCaseById(id);
        c.setStatus(status);
        return repository.save(c);
    }

    @Override
    public ConflictCase getCaseById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("case not found"));
    }
@Override
public List<ConflictCase> getAllCases() {
    return conflictCaseRepository.findAll();
}

    @Override
    public List<ConflictCase> getCasesByPerson(Long personId) {
        return repository.findByPrimaryPersonIdOrSecondaryPersonId(personId, personId);
    }
}
