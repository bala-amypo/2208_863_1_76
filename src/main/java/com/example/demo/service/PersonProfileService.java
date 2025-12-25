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
        return personProfileRepository.save(person);
    }

    @Override
    public PersonProfile getPersonById(Long id) {
        return personProfileRepository.findById(id)
                .orElseThrow(() -> new ApiException("person not found"));
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public List<PersonProfile> getAllPersons() {
        return personProfileRepository.findAll();
    }

    // ✅ RETURN TYPE MATCHES INTERFACE
    @Override
    public PersonProfile findByReferenceId(String referenceId) {
        return personProfileRepository.findByReferenceId(referenceId)
                .orElseThrow(() -> new ApiException("person not found"));
    }

    // ✅ RETURN TYPE MATCHES INTERFACE
    @Override
    public PersonProfile updateRelationshipDeclared(Long id, boolean declared) {
        PersonProfile person = getPersonById(id);
        person.setRelationshipDeclared(declared);
        return personProfileRepository.save(person);
    }
}
