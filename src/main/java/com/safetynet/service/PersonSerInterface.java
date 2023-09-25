package com.safetynet.service;

import com.safetynet.model.Person;

import java.util.Optional;

public interface PersonSerInterface {
    Optional<Person> getPerson(String firstname, String lastname);
    Iterable<Person> getPersons();
    boolean deletePerson(String firstname,String lastname);
    Person updatePerson (String firstname, String lastname, String address, String city, int zip, String phone, String email);
}
