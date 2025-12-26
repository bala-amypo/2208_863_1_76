package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.PersonProfile;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.service.PersonProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonProfileServiceImpl implements PersonProfileService {

    private final PersonProfileRepository repository;

    public PersonProfileServiceImpl(PersonProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonProfile createPerson(PersonProfile person) {

        if (person.getEmail() == null) {
            throw new ApiException("Email is required");
        }

        if (repository.findByEmail(person.getEmail()).isPresent()) {
            throw new ApiException("Duplicate email");
        }

        if (person.getReferenceId() != null &&
                repository.findByReferenceId(person.getReferenceId()).isPresent()) {
            throw new ApiException("Duplicate referenceId");
        }

        if (person.getRelationshipDeclared() == null) {
            person.setRelationshipDeclared(false);
        }

        return repository.save(person);
    }

    @Override
    public Optional<PersonProfile> getPersonById(Long id) {
        Optional<PersonProfile> person = repository.findById(id);
        if (person.isEmpty()) {
            throw new ApiException("Person not found");
        }
        return person;
    }

    @Override
    public List<PersonProfile> getAllPersons() {
        return repository.findAll();
    }

    @Override
    public PersonProfile updateRelationshipDeclared(Long id, boolean declared) {
        PersonProfile person = repository.findById(id)
                .orElseThrow(() -> new ApiException("Person not found"));

        person.setRelationshipDeclared(declared);
        return repository.save(person);
    }

    @Override
    public Optional<PersonProfile> findByReferenceId(String referenceId) {
        return repository.findByReferenceId(referenceId);
    }
}
