package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.PersonProfile;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.service.PersonProfileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonProfileServiceImpl implements PersonProfileService {

    private final PersonProfileRepository personProfileRepository;

    public PersonProfileServiceImpl(PersonProfileRepository personProfileRepository) {
        this.personProfileRepository = personProfileRepository;
    }

    @Override
    public PersonProfile createPerson(PersonProfile person) {
        return personProfileRepository.save(person);
    }

    @Override
    public Optional<PersonProfile> findByReferenceId(String referenceId) {
        return personProfileRepository.findByReferenceId(referenceId);
    }

    @Override
    public PersonProfile getPersonById(Long id) {
        return personProfileRepository.findById(id)
                .orElseThrow(() -> new ApiException("Person not found"));
    }
}
