package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class ConflictCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long primaryPersonId;
    private Long secondaryPersonId;

    private String triggerSource;   
    private String riskLevel;      
    private String details;

    private String status;         
    private LocalDateTime detectedAt;

    public ConflictCase() {
    }

    @PrePersist
    public void onCreate() {
        this.detectedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "OPEN";
        }
    }

    

    public Long getId() {
        return id;
    }

    public Long getPrimaryPersonId() {
        return primaryPersonId;
    }

    public void setPrimaryPersonId(Long primaryPersonId) {
        this.primaryPersonId = primaryPersonId;
    }

    public Long getSecondaryPersonId() {
        return secondaryPersonId;
    }

    public void setSecondaryPersonId(Long secondaryPersonId) {
        this.secondaryPersonId = secondaryPersonId;
    }

    public String getTriggerSource() {
        return triggerSource;
    }

    public void setTriggerSource(String triggerSource) {
        this.triggerSource = triggerSource;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDetectedAt() {
        return detectedAt;
    }
}
