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
            throw new ApiException("Email required");
        }

        // Manual duplicate checks (repo does NOT have existsBy*)
        repository.findAll().forEach(p -> {
            if (p.getEmail().equals(person.getEmail())) {
                throw new ApiException("Duplicate email");
            }
            if (p.getReferenceId().equals(person.getReferenceId())) {
                throw new ApiException("Duplicate reference id");
            }
        });

        person.setRelationshipDeclared(false);
        return repository.save(person);
    }

    @Override
    public Optional<PersonProfile> getPersonById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PersonProfile> getAllPersons() {
        return repository.findAll();
    }

    @Override
    public Optional<PersonProfile> findByReferenceId(String referenceId) {
        return repository.findAll()
                .stream()
                .filter(p -> p.getReferenceId().equals(referenceId))
                .findFirst();
    }

    @Override
    public PersonProfile updateRelationshipDeclared(Long id, boolean declared) {
        PersonProfile person = repository.findById(id)
                .orElseThrow(() -> new ApiException("Person not found"));

        person.setRelationshipDeclared(declared);
        return repository.save(person);
    }
}
