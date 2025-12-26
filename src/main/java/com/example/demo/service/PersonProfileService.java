package com.example.demo.service;

import com.example.demo.model.PersonProfile;

import java.util.List;
import java.util.Optional;

public interface PersonProfileService {

    PersonProfile createPerson(PersonProfile person);

    Optional<PersonProfile> getPersonById(Long id);

    Optional<PersonProfile> findByReferenceId(String referenceId);

    List<PersonProfile> getAllPersons();

    PersonProfile updateRelationshipDeclared(Long id, boolean declared);
}
