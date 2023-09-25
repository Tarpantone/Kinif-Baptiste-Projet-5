package com.safetynet.controller;

import com.safetynet.model.Person;
import com.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/persons")
    public Iterable<Person>getPersons(){
        return personService.getPersons();
    }
    @GetMapping("/person")
    public Optional<Person>getPerson(@RequestParam String firstname,@RequestParam String lastname){
        return personService.getPerson(firstname,lastname);
    }
    @DeleteMapping("/person")
    public boolean deletePerson(@RequestParam String firstname,@RequestParam String lastname){
        return personService.deletePerson(firstname,lastname);
    }
    @PostMapping("/person")
    public boolean addPerson(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam int zip,
            @RequestParam String phone,
            @RequestParam String email
    ){
        return personService.addPerson(firstname,lastname,address,city,zip,phone,email);
    }
    @PatchMapping("/person")
    public Person updatePerson(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam int zip,
            @RequestParam String phone,
            @RequestParam String email
    ){
        return personService.updatePerson(firstname,lastname,address,city,zip,phone,email);
    }
}
