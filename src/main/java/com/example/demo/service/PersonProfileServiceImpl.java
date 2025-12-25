package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.PersonProfile;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.service.PersonProfileService;

import java.util.List;

public class PersonProfileServiceImpl implements PersonProfileService {

    private final PersonProfileRepository repo;

    public PersonProfileServiceImpl(PersonProfileRepository repo) {
        this.repo = repo;
    }

    public PersonProfile createPerson(PersonProfile person) {
        if (repo.findByEmail(person.getEmail()).isPresent())
            throw new ApiException("email exists");

        if (repo.findByReferenceId(person.getReferenceId()).isPresent())
            throw new ApiException("reference exists");

        return repo.save(person);
    }

    public PersonProfile getPersonById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("person not found"));
    }

    public List<PersonProfile> getAllPersons() {
        return repo.findAll();
    }

    public PersonProfile updateRelationshipDeclared(Long id, boolean declared) {
        PersonProfile p = getPersonById(id);
        p.setRelationshipDeclared(declared);
        return repo.save(p);
    }
}
