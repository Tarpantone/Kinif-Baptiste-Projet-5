package com.safetynet.service;

import com.safetynet.model.Person;
import com.safetynet.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PersonService implements PersonSerInterface {
    private static final Logger personServiceLogger= LoggerFactory.getLogger(PersonService.class);
    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(String firstname,String lastname){
        personServiceLogger.debug("getPerson");
        return Optional.ofNullable(this.personRepository.getPerson(firstname, lastname));
    }

    public Iterable<Person> getPersons(){
        personServiceLogger.debug("getPersons");
        return this.personRepository.getPersons();
    }

    public boolean deletePerson(String firstname,String lastname){
        personServiceLogger.debug("deletePerson");
        return this.personRepository.deletePerson(firstname,lastname);
    }
    public Person updatePerson (String firstname, String lastname, String address, String city, int zip, String phone, String email){
        personServiceLogger.debug("updatePerson");
        return this.personRepository.updatePerson(firstname,lastname,address,city,zip,phone,email);
    }
    public boolean addPerson(String firstname,String lastname,String address,String city,int zip,String phone,String email){
        personServiceLogger.debug("addPerson");
        return this.personRepository.addPerson(firstname,lastname,address,city,zip,phone,email);
    }
}
