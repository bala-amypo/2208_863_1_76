package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ConflictCase;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.service.ConflictCaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConflictCaseServiceImpl implements ConflictCaseService {

    private final ConflictCaseRepository repository;

    public ConflictCaseServiceImpl(ConflictCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConflictCase createCase(ConflictCase conflictCase) {

        if (conflictCase.getPrimaryPersonId() == null ||
            conflictCase.getSecondaryPersonId() == null) {
            throw new ApiException("Persons required");
        }

        if (conflictCase.getStatus() == null) {
            conflictCase.setStatus("OPEN");
        }

        return repository.save(conflictCase);
    }

    @Override
    public Optional<ConflictCase> getCaseById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ConflictCase> getAllCases() {
        return repository.findAll();
    }

    @Override
    public ConflictCase updateCaseStatus(Long id, String status) {
        ConflictCase c = repository.findById(id)
                .orElseThrow(() -> new ApiException("Case not found"));
        c.setStatus(status);
        return repository.save(c);
    }

    @Override
    public List<ConflictCase> getCasesByPerson(Long personId) {
        return repository.findByPrimaryPersonIdOrSecondaryPersonId(personId, personId);
    }
}
