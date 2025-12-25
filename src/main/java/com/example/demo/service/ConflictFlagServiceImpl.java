package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ConflictCase;
import com.example.demo.model.ConflictFlag;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.repository.ConflictFlagRepository;
import com.example.demo.service.ConflictFlagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConflictFlagServiceImpl implements ConflictFlagService {

    private final ConflictFlagRepository conflictFlagRepository;
    private final ConflictCaseRepository conflictCaseRepository;

    public ConflictFlagServiceImpl(
            ConflictFlagRepository conflictFlagRepository,
            ConflictCaseRepository conflictCaseRepository
    ) {
        this.conflictFlagRepository = conflictFlagRepository;
        this.conflictCaseRepository = conflictCaseRepository;
    }

    @Override
    public ConflictFlag addFlag(ConflictFlag flag) {

        // ✅ Test requires case existence check
        ConflictCase conflictCase = conflictCaseRepository.findById(flag.getCaseId())
                .orElseThrow(() -> new ApiException("case not found"));

        return conflictFlagRepository.save(flag);
    }

    @Override
    public ConflictFlag getFlagById(Long id) {
        return conflictFlagRepository.findById(id)
                .orElseThrow(() -> new ApiException("flag not found"));
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public List<ConflictFlag> getFlagsByCase(Long caseId) {
        return conflictFlagRepository.findByCaseId(caseId);
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public List<ConflictFlag> getAllFlags() {
        return conflictFlagRepository.findAll();
    }
}
