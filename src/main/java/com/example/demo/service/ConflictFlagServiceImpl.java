package com.example.demo.service.impl;

import com.example.demo.model.ConflictFlag;
import com.example.demo.repository.ConflictFlagRepository;
import com.example.demo.service.ConflictFlagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConflictFlagServiceImpl implements ConflictFlagService {

    private final ConflictFlagRepository conflictFlagRepository;

    public ConflictFlagServiceImpl(ConflictFlagRepository conflictFlagRepository) {
        this.conflictFlagRepository = conflictFlagRepository;
    }

    @Override
    public ConflictFlag createFlag(ConflictFlag flag) {
        return conflictFlagRepository.save(flag);
    }

    @Override
    public List<ConflictFlag> getAllFlags() {
        return conflictFlagRepository.findAll();
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public List<ConflictFlag> getFlagsByCase(Long caseId) {
        return conflictFlagRepository.findByCaseId(caseId);
    }
}
