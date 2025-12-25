package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.PersonProfile;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.service.PersonProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonProfileServiceImpl implements PersonProfileService {

    private final PersonProfileRepository personProfileRepository;

    public PersonProfileServiceImpl(PersonProfileRepository personProfileRepository) {
        this.personProfileRepository = personProfileRepository;
    }

    @Override
    public PersonProfile createPerson(PersonProfile person) {

        if (person.getEmail() == null) {
            throw new ApiException("email is required");
        }

        if (person.getReferenceId() == null) {
            throw new ApiException("reference is required");
        }

        personProfileRepository.findByEmail(person.getEmail())
                .ifPresent(p -> {
                    throw new ApiException("email already exists");
                });

        personProfileRepository.findByReferenceId(person.getReferenceId())
                .ifPresent(p -> {
                    throw new ApiException("reference already exists");
                });

        return personProfileRepository.save(person);
    }

    @Override
    public PersonProfile getPersonById(Long id) {
        return personProfileRepository.findById(id)
                .orElseThrow(() -> new ApiException("person not found"));
    }

    @Override
    public List<PersonProfile> getAllPersons() {
        return personProfileRepository.findAll();
    }

    @Override
    public PersonProfile findByReferenceId(String referenceId) {
        return personProfileRepository.findByReferenceId(referenceId)
                .orElseThrow(() -> new ApiException("person not found"));
    }

    @Override
    public PersonProfile updateRelationshipDeclared(Long id, boolean declared) {
        PersonProfile person = getPersonById(id);
        person.setRelationshipDeclared(declared);
        return personProfileRepository.save(person);
    }
}
