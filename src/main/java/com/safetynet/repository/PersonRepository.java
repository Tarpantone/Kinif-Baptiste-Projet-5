package com.safetynet.repository;

import com.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

 @Repository
public class PersonRepository implements PersonRepoInterface{
    List <Person> persons;
    @Autowired
    SafetynetRepository safetynetRepository;
    @Override
    public List<Person> getPersonsFromSafetynet(){
        safetynetRepository.dataGetter();
        return safetynetRepository.getSafetynet().getPersons();
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
    public void updatePerson(String firstname,String lastname, String address,String city,int zip,String phone,String email){
        this.persons.stream().filter(x->x.getFirstName().equals(firstname)&&x.getLastName().equals(lastname)).forEach(x-> {
            x.setAddress(address);
            x.setCity(city);
            x.setZip(zip);
            x.setPhone(phone);
            x.setEmail(email);
        });
    }
    @Override
    public Person getPerson(String firstname,String lastname){
        return this.persons.stream().filter(p->p.getFirstName().equals(firstname)&&p.getLastName().equals(lastname)).findAny().orElseGet(null);
    }
}
