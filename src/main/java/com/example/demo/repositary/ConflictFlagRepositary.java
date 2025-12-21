package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ConflictFlag;

public interface ConflictFlagRepository
        extends JpaRepository<ConflictFlag, Long> {

    List<ConflictFlag> findByCaseId(Long caseId);
}
