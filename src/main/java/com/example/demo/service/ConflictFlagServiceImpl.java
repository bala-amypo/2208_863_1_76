package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ConflictFlag;
import com.example.demo.repository.ConflictCaseRepository;
import com.example.demo.repository.ConflictFlagRepository;
import com.example.demo.service.ConflictFlagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConflictFlagServiceImpl implements ConflictFlagService {

    private final ConflictFlagRepository flagRepository;
    private final ConflictCaseRepository caseRepository;

    public ConflictFlagServiceImpl(ConflictFlagRepository flagRepository,
                                   ConflictCaseRepository caseRepository) {
        this.flagRepository = flagRepository;
        this.caseRepository = caseRepository;
    }

    @Override
    public ConflictFlag addFlag(ConflictFlag flag) {
        caseRepository.findById(flag.getCaseId())
                .orElseThrow(() -> new ApiException("case not found"));

        return flagRepository.save(flag);
    }

    @Override
    public ConflictFlag getFlagById(Long id) {
        return flagRepository.findById(id)
                .orElseThrow(() -> new ApiException("flag not found"));
    }

    @Override
    public List<ConflictFlag> getFlagsByCase(Long caseId) {
        return flagRepository.findByCaseId(caseId);
    }

    @Override
    public List<ConflictFlag> getAllFlags() {
        return flagRepository.findAll();
    }
}
