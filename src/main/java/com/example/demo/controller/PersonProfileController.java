package com.example.demo.controller;

import com.example.demo.model.PersonProfile;
import com.example.demo.service.PersonProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonProfileController {

    private final PersonProfileService service;

    public PersonProfileController(PersonProfileService service) {
        this.service = service;
    }

    @PostMapping
    public PersonProfile create(@RequestBody PersonProfile person) {
        return service.createPerson(person);
    }

  @GetMapping("/{id}")
public ResponseEntity<PersonProfile> getPersonById(@PathVariable Long id) {

    return personProfileService.getPersonById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

    @GetMapping("/lookup/{referenceId}")
public ResponseEntity<PersonProfile> lookup(@PathVariable String referenceId) {
    return personProfileService
            .findByReferenceId(referenceId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}


    @GetMapping
    public List<PersonProfile> getAll() {
        return service.getAllPersons();
    }
}
