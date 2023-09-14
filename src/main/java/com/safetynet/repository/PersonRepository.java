package com.safetynet.repository;

import com.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

 @Repository
public class PersonRepository {
    List <Person> persons;
    @Autowired
    JsonDataGetter jsonDataGetter;

    public List<Person> getPersonsFromSafetynet(){
        jsonDataGetter.dataGetter();
        return jsonDataGetter.getSafetynet().getPersons();
    }

    public boolean deletePerson(String firstname,String lastname){
        boolean result=false;
        for(Person p:persons){
            if(p.getFirstName().equals(firstname)&&p.getLastName().equals(lastname)){
                result=persons.remove(p);
                break;
            }
        }
        return result;
    }

    public boolean addPerson(String firstname,String lastname,String address,String city,int zip,String phone,String email){
        return persons.add(new Person(firstname,lastname,address,city,zip,phone,email));
    }

    public boolean updatePerson(String firstname,String lastname, String address,String city,int zip,String phone,String email){
        boolean result=false;
        for(Person p:persons){
            if(p.getFirstName().equals(firstname)&&p.getLastName().equals(lastname)){
                p.setFirstName(firstname);
                p.setLastName(lastname);
                p.setAddress(address);
                p.setCity(city);
                p.setZip(zip);
                p.setPhone(phone);
                p.setEmail(email);
                result=true;
                break;
            }
        }
        return result;
    }
}
