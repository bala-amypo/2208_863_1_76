package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PersonProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String personType;
    private String referenceId;
    private String fullName;
    private String email;
    private String department;
    private Boolean relationshipDeclared = false;
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public PersonProfile() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPersonType() { return personType; }
    public void setPersonType(String personType) { this.personType = personType; }

    public String getReferenceId() { return referenceId; }
    public void setReferenceId(String referenceId) { this.referenceId = referenceId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Boolean getRelationshipDeclared() { return relationshipDeclared; }
    public void setRelationshipDeclared(Boolean relationshipDeclared) {
        this.relationshipDeclared = relationshipDeclared;
    }
}
