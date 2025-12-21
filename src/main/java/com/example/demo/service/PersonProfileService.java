package com.example.demo.service;

import java.util.List;
import com.example.demo.model.PersonProfile;

public interface PersonProfileService {

    PersonProfile createPerson(PersonProfile person);

    PersonProfile getPersonById(Long id);

    List<PersonProfile> getAllPersons();

    PersonProfile findByReferenceId(String referenceId);

    PersonProfile updateRelationshipDeclared(Long id, boolean declared);
}
