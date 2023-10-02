package com.safetynet.service;

import com.safetynet.model.Person;
import com.safetynet.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    PersonRepository personRepository;
    @InjectMocks@Autowired
    PersonService personService;

    private static List<Person>persons=new ArrayList<>();

    @BeforeAll
    static void setUp(){
        for(int i=0;i<3;i++){
            persons.add(new Person("firstname"+i,"lastname"+i,"address"+i,"city"+i,i,"phone"+i,"email"+i));
        }
    }
    @Test
    void getPerson() {
        when(personRepository.getPerson(anyString(),anyString())).thenReturn(persons.get(0));
        Optional<Person>result=personService.getPerson(anyString(),anyString());
        assertTrue(result.get().getFirstName().equals(persons.get(0).getFirstName()));
    }

    @Test
    void getPersons() {
        when(personRepository.getPersons()).thenReturn(persons);
        List<Person>result= (List<Person>) personService.getPersons();
        assertFalse(result.isEmpty());
    }

    @Test
    void deletePerson() {
        when(personRepository.deletePerson(anyString(),anyString())).thenReturn(true);
        Boolean result=personService.deletePerson(anyString(),anyString());
        assertTrue(result);
    }

    @Test
    void updatePerson() {
        when(personRepository.updatePerson(anyString(),anyString(),anyString(),anyString(),anyInt(),anyString(),anyString())).thenReturn(new Person());
        Person result=personService.updatePerson(anyString(),anyString(),anyString(),anyString(),anyInt(),anyString(),anyString());
        assertNotNull(result);
    }

    @Test
    void addPerson() {
        when(personRepository.addPerson(anyString(),anyString(),anyString(),anyString(),anyInt(),anyString(),anyString())).thenReturn(true);
        Boolean result=personService.addPerson(anyString(),anyString(),anyString(),anyString(),anyInt(),anyString(),anyString());
        assertTrue(result);
    }
}
