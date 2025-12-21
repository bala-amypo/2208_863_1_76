package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ConflictCase;

public interface ConflictCaseRepository
        extends JpaRepository<ConflictCase, Long> {

    List<ConflictCase> findByPrimaryPersonIdOrSecondaryPersonId(
            Long primaryPersonId,
            Long secondaryPersonId
    );
}
