package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ConflictFlag;
import com.example.demo.service.ConflictFlagService;

@RestController
@RequestMapping("/api/conflict-flags")
public class ConflictFlagController {

    private final ConflictFlagService conflictFlagService;

    public ConflictFlagController(ConflictFlagService conflictFlagService) {
        this.conflictFlagService = conflictFlagService;
    }

    @PostMapping
    public ConflictFlag addFlag(@RequestBody ConflictFlag flag) {
        return conflictFlagService.addFlag(flag);
    }

    @GetMapping("/{id}")
    public ConflictFlag getFlagById(@PathVariable Long id) {
        return conflictFlagService.getFlagById(id);
    }

    @GetMapping("/case/{caseId}")
    public List<ConflictFlag> getFlagsByCase(@PathVariable Long caseId) {
        return conflictFlagService.getFlagsByCase(caseId);
    }

    @GetMapping
    public List<ConflictFlag> getAllFlags() {
        return conflictFlagService.getAllFlags();
    }
}
