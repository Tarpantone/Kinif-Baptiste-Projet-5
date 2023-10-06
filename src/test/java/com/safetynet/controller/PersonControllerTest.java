package com.safetynet.controller;

import com.safetynet.SafetyNetApplication;
import com.safetynet.model.Person;
import com.safetynet.service.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SafetyNetApplication.class)
@AutoConfigureMockMvc
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;
    private static List<Person>persons=new ArrayList<>();

    @BeforeAll
    static void setUp(){
        persons.add(new Person("Lisa","Boyd","1509 Culver St","Culver",97452,"841-874-6513","liboyd@email.com"));
    }

    @Test
    void addPersonWhereStatusIsOK() throws Exception{
        when(personService.addPerson(anyString(),anyString(),anyString(),anyString(),anyInt(),anyString(),anyString())).thenReturn(true);
        mockMvc.perform(post("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd&address=1509 Culver St&city=Culver&zip=97452&phone=841-874-6513&email=liboyd@email.com"))
                .andExpect(status().isOk());
    }
    @Test
    void addPersonWhereStatusIsINTERNAL_SERVER_ERROR() throws Exception{
        when(personService.addPerson(anyString(),anyString(),anyString(),anyString(),anyInt(),anyString(),anyString())).thenReturn(false);
        mockMvc.perform(post("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd&address=1509 Culver St&city=Culver&zip=97452&phone=841-874-6513&email=liboyd@email.com"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getPersonsWhereStatusIsOK() throws Exception {
        when(personService.getPersons()).thenReturn(persons);
        mockMvc.perform(get("http://localhost:8080/api/person/all"))
                .andExpect(status().isOk());
    }
    @Test
    void getPersonsWhereStatusIsINTERNAL_SERVER_ERROR() throws Exception {
        List<Person>emptyList=new ArrayList<>();
        when(personService.getPersons()).thenReturn(emptyList);
        mockMvc.perform(get("http://localhost:8080/api/person/all"))
                .andExpect(status().isInternalServerError());
    }
    @Test
    void getPersonWhereStatusIsOK() throws Exception{
        when(personService.getPerson(anyString(),anyString())).thenReturn(Optional.of(persons.get(0)));
        mockMvc.perform(get("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd"))
                .andExpect(status().isOk());
    }
    @Test
    void getPersonWhereStatusIsNOT_FOUND() throws Exception{
        when(personService.getPerson(anyString(),anyString())).thenReturn(Optional.of(new Person("w","w","w","w",1,"w","w")));
        mockMvc.perform(get("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd"))
                .andExpect(status().isNotFound());
    }
    @Test
    void updatePersonWhereStatusIsOK() throws Exception{
        when(personService.updatePerson(anyString(),anyString(),anyString(),anyString(),anyInt(),anyString(),anyString())).thenReturn(persons.get(0));
        mockMvc.perform(patch("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd&address=1509 Culver St&city=Culver&zip=97452&phone=841-874-6513&email=lisaboyd@email.com"))
                .andExpect(status().isOk());
    }
    @Test
    void updatePersonWhereStatusIsNOT_FOUND() throws Exception{
        when(personService.updatePerson(anyString(),anyString(),anyString(),anyString(),anyInt(),anyString(),anyString())).thenReturn(new Person("w","w","w","w",1,"w","w"));
        mockMvc.perform(patch("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd&address=1509 Culver St&city=Culver&zip=97452&phone=841-874-6513&email=lisaboyd@email.com"))
                .andExpect(status().isNotFound());
    }
    @Test
    void deletePersonWhereStatusIsOK() throws Exception{
        when(personService.deletePerson(anyString(),anyString())).thenReturn(true);
        mockMvc.perform(delete("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd"))
                .andExpect(status().isOk());
    }
    @Test
    void deletePersonWhereStatusIsNOT_FOUND() throws Exception{
        when(personService.deletePerson(anyString(),anyString())).thenReturn(false);
        mockMvc.perform(delete("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd"))
                .andExpect(status().isNotFound());
    }
}