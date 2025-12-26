package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.PersonProfile;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.service.PersonProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonProfileServiceImpl implements PersonProfileService {

    private final PersonProfileRepository repository;

    public PersonProfileServiceImpl(PersonProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonProfile createPerson(PersonProfile person) {

        if (person.getEmail() == null) {
            throw new ApiException("Email required");
        }

        if (repository.existsByEmail(person.getEmail())) {
            throw new ApiException("Duplicate email");
        }

        if (repository.existsByReferenceId(person.getReferenceId())) {
            throw new ApiException("Duplicate reference id");
        }

        person.setRelationshipDeclared(false);
        return repository.save(person);
    }

    @Override
    public PersonProfile getPersonById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Person not found"));
    }

    @Override
    public List<PersonProfile> getAllPersons() {
        return repository.findAll();
    }

    @Override
    public PersonProfile findByReferenceId(String referenceId) {
        return repository.findByReferenceId(referenceId)
                .orElseThrow(() -> new ApiException("Person not found"));
    }

    @Override
    public PersonProfile updateRelationshipDeclared(Long id, boolean declared) {
        PersonProfile person = getPersonById(id);
        person.setRelationshipDeclared(declared);
        return repository.save(person);
    }
}
