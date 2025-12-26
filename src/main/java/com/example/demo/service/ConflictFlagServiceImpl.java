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

    private final ConflictFlagRepository flagRepo;
    private final ConflictCaseRepository caseRepo;

    public ConflictFlagServiceImpl(
            ConflictFlagRepository flagRepo,
            ConflictCaseRepository caseRepo) {
        this.flagRepo = flagRepo;
        this.caseRepo = caseRepo;
    }

    @Override
    public ConflictFlag addFlag(ConflictFlag flag) {

        ConflictCase c = caseRepo.findById(flag.getCaseId())
                .orElseThrow(() -> new ApiException("Case not found"));

        if ("HIGH".equals(flag.getSeverity())) {
            c.setRiskLevel("HIGH");
        } else if ("MEDIUM".equals(flag.getSeverity())
                && !"HIGH".equals(c.getRiskLevel())) {
            c.setRiskLevel("MEDIUM");
        }

        caseRepo.save(c);
        return flagRepo.save(flag);
    }

    @Override
    public ConflictFlag getFlagById(Long id) {
        return flagRepo.findById(id)
                .orElseThrow(() -> new ApiException("Flag not found"));
    }

    @Override
    public List<ConflictFlag> getFlagsByCase(Long caseId) {
        return flagRepo.findByCaseId(caseId);
    }

    @Override
    public List<ConflictFlag> getAllFlags() {
        return flagRepo.findAll();
    }
}
