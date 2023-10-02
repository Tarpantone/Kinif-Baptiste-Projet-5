package com.safetynet.controller;

import com.safetynet.SafetyNetApplication;
import com.safetynet.service.PersonService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SafetyNetApplication.class)
@AutoConfigureMockMvc
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;

    @Test
    @Order(1)
    void addPerson() throws Exception{
        mockMvc.perform(post("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd&address=1509 Culver St&city=Culver&zip=97452&phone=841-874-6513&email=liboyd@email.com"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void getPersons() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/person/all"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void getPerson() throws Exception{
        mockMvc.perform(get("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void updatePerson() throws Exception{
        mockMvc.perform(patch("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd&address=1509 Culver St&city=Culver&zip=97452&phone=841-874-6513&email=lisaboyd@email.com"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void deletePerson() throws Exception{
        mockMvc.perform(delete("http://localhost:8080/api/person?firstname=Lisa&lastname=Boyd"))
                .andExpect(status().isOk());
    }
}