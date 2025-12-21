package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ConflictFlag;

public interface ConflictFlagService {

    ConflictFlag addFlag(ConflictFlag flag);

    ConflictFlag getFlagById(Long id);

    List<ConflictFlag> getFlagsByCase(Long caseId);

    List<ConflictFlag> getAllFlags();
}
