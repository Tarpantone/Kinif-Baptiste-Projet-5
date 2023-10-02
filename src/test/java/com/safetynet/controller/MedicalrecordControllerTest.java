package com.safetynet.controller;

import com.safetynet.SafetyNetApplication;
import com.safetynet.service.MedicalrecordService;
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
class MedicalrecordControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MedicalrecordService medicalrecordService;
    @Test
    @Order(1)
    void addMedicalrecordTest() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/medicalrecord?firstname=Lisa&lastname=Boyd&birthdate=18/02/1983&medications=azenol:200mg,reactine:1mg&allergies="))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void getMedicalrecordsTest() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/medicalrecord/all"))
                .andExpect(status().isOk());

    }

    @Test
    @Order(3)
    void getMedicalrecordTest() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/medicalrecord?firstname=Lisa&lastname=Boyd"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void updateMedicalrecordTest() throws Exception {
        mockMvc.perform(patch("http://localhost:8080/api/medicalrecord?firstname=John&lastname=Boyd&birthdate=03/06/1984&medications=azenol:200mg,reactine:1mg&allergies=Poopilechat"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void deleteMedicalrecordTest() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/medicalrecord?firstname=Lisa&lastname=Boyd"))
                .andExpect(status().isOk());
    }
}