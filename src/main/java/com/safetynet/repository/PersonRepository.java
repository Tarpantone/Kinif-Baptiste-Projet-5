package com.safetynet.repository;

import com.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

 @Repository
public class PersonRepository implements PersonRepoInterface{
    @Autowired
    private List <Person> persons;
    @Autowired
    private SafetynetRepository safetynetRepository;

    public PersonRepository(){
        this.safetynetRepository=new SafetynetRepository();
        this.persons = safetynetRepository.getSafetynet().getPersons();
    }

    @Override
    public List<Person> getPersons(){
        return this.persons;
    }
    @Override
    public boolean deletePerson(String firstname,String lastname){
        return this.persons.removeIf(x->x.getFirstName().equals(firstname)&&x.getLastName().equals(lastname));
    }
    @Override
    public boolean addPerson(String firstname,String lastname,String address,String city,int zip,String phone,String email){
        return persons.add(new Person(firstname,lastname,address,city,zip,phone,email));
    }
    @Override
    public Person updatePerson(String firstname, String lastname, String address, String city, int zip, String phone, String email){
        Person person=new Person(firstname,lastname,address,city,zip,phone,email);
        this.persons.stream().filter(x->x.getFirstName().equals(firstname)&&x.getLastName().equals(lastname)).forEach(x-> {
            x.setAddress(address);
            x.setCity(city);
            x.setZip(zip);
            x.setPhone(phone);
            x.setEmail(email);
        });
        return person;
    }
    @Override
    public Person getPerson(String firstname,String lastname){
        return this.persons.stream().filter(p->p.getFirstName().equals(firstname)&&p.getLastName().equals(lastname)).findAny().orElseGet(null);
    }
}
