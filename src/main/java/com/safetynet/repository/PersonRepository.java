package com.safetynet.repository;

import com.safetynet.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

 @Repository
public class PersonRepository {
    List <Person> persons;
    JsonDataGetter jsonDataGetter;
}
