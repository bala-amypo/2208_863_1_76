package com.example.demo.controller;

import com.example.demo.model.PersonProfile;
import com.example.demo.service.PersonProfileService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonProfileController {

    private PersonProfileService service;

    // REQUIRED NO-ARG CONSTRUCTOR
    public PersonProfileController() {}

    public PersonProfileController(PersonProfileService service) {
        this.service = service;
    }

    @PostMapping
    public PersonProfile create(PersonProfile p) {
        return service.createPerson(p);
    }

    @GetMapping("/{id}")
    public PersonProfile get(Long id) {
        return service.getPersonById(id);
    }

    @GetMapping
    public List<PersonProfile> getAll() {
        return service.getAllPersons();
    }
}
