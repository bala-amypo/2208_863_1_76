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
        if (repository.findByEmail(person.getEmail()).isPresent()) {
            throw new ApiException("Duplicate email");
        }
        if (repository.findByReferenceId(person.getReferenceId()).isPresent()) {
            throw new ApiException("Duplicate reference");
        }
        return repository.save(person);
    }

    // 🔴 MUST THROW ApiException (not Optional)
    @Override
    public PersonProfile getPersonById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Person not found"));
    }

    @Override
    public Optional<PersonProfile> findByReferenceId(String refId) {
        return repository.findByReferenceId(refId);
    }

    @Override
    public List<PersonProfile> getAllPersons() {
        return repository.findAll();
    }

    @Override
    public PersonProfile updateRelationshipDeclared(Long id, boolean declared) {
        PersonProfile p = getPersonById(id);
        p.setRelationshipDeclared(declared);
        return repository.save(p);
    }
}
