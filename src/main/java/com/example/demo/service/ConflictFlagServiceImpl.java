package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ConflictFlag;
import com.example.demo.repository.ConflictFlagRepository;
import com.example.demo.service.ConflictFlagService;

import java.util.List;

public class ConflictFlagServiceImpl implements ConflictFlagService {

    private final ConflictFlagRepository repository;

    public ConflictFlagServiceImpl(ConflictFlagRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConflictFlag addFlag(ConflictFlag flag) {
        return repository.save(flag);
    }

    @Override
    public ConflictFlag getFlagById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("flag not found"));
    }

    @Override
    public List<ConflictFlag> getFlagsByCase(Long caseId) {
        return repository.findByCaseId(caseId);
    }
    @Override
public List<ConflictFlag> getAllFlags() {
    return conflictFlagRepository.findAll();
}

}
