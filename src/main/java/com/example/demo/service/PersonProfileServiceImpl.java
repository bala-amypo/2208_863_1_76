package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.PersonProfile;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.service.PersonProfileService;

import java.util.List;

public class PersonProfileServiceImpl implements PersonProfileService {

    private final PersonProfileRepository repository;

    public PersonProfileServiceImpl(PersonProfileRepository repository) {
        this.repository = repository;
    }
    @Override
public PersonProfile findByReferenceId(String referenceId) {
    return personProfileRepository.findByReferenceId(referenceId)
            .orElseThrow(() -> new ApiException("reference not found"));
}


    @Override
    public PersonProfile createPerson(PersonProfile person) {
        if (repository.findByEmail(person.getEmail()).isPresent()) {
            throw new ApiException("email already exists");
        }
        if (repository.findByReferenceId(person.getReferenceId()).isPresent()) {
            throw new ApiException("reference already exists");
        }
        return repository.save(person);
    }

    @Override
    public PersonProfile getPersonById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("person not found"));
    }

    @Override
    public List<PersonProfile> getAllPersons() {
        return repository.findAll();
    }

    @Override
    public PersonProfile updateRelationshipDeclared(Long id, boolean declared) {
        PersonProfile person = getPersonById(id);
        person.setRelationshipDeclared(declared);
        return repository.save(person);
    }
}
