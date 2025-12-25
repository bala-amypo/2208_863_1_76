package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.PersonProfile;
import com.example.demo.repository.PersonProfileRepository;
import com.example.demo.service.PersonProfileService;
import org.springframework.stereotype.Service;

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

    // ✅ RETURN TYPE FIXED
    @Override
    public PersonProfile findByReferenceId(String referenceId) {
        return personProfileRepository.findByReferenceId(referenceId)
                .orElseThrow(() -> new ApiException("Person not found"));
    }

    @Override
    public PersonProfile getPersonById(Long id) {
        return personProfileRepository.findById(id)
                .orElseThrow(() -> new ApiException("Person not found"));
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public void updateRelationshipDeclared(Long id, boolean declared) {
        PersonProfile p = getPersonById(id);
        p.setRelationshipDeclared(declared);
        personProfileRepository.save(p);
    }
}
  