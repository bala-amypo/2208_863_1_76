package com.example.demo.service;

import com.example.demo.model.ConflictFlag;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ConflictFlagService {

    ConflictFlag addFlag(ConflictFlag flag);

    ConflictFlag getFlagById(Long id);

    List<ConflictFlag> getFlagsByCase(Long caseId);

    List<ConflictFlag> getAllFlags(); // ✅ ADD THIS
}
