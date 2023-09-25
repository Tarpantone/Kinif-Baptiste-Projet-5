package com.safetynet.service;

import com.safetynet.model.Person;
import com.safetynet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PersonService implements PersonSerInterface {
    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(String firstname,String lastname){
        return Optional.ofNullable(this.personRepository.getPerson(firstname, lastname));
    }

    public Iterable<Person> getPersons(){
        return this.personRepository.getPersons();
    }

    public boolean deletePerson(String firstname,String lastname){
        return this.personRepository.deletePerson(firstname,lastname);
    }
    public Person updatePerson (String firstname, String lastname, String address, String city, int zip, String phone, String email){
        return this.personRepository.updatePerson(firstname,lastname,address,city,zip,phone,email);
    }
    public boolean addPerson(String firstname,String lastname,String address,String city,int zip,String phone,String email){
        return this.personRepository.addPerson(firstname,lastname,address,city,zip,phone,email);
    }
}
