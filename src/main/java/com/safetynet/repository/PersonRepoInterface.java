package com.safetynet.repository;

import com.safetynet.model.Person;

import java.util.List;

public interface PersonRepoInterface {
    public List<Person> getPersons();
    public boolean deletePerson(String firstname,String lastname);
    public boolean addPerson(String firstname,String lastname,String address,String city,int zip,String phone,String email);
    public Person updatePerson(String firstname, String lastname, String address, String city, int zip, String phone, String email);
    Person getPerson(String firstname,String lastname);
}
