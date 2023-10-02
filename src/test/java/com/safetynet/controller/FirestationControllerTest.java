package com.safetynet.controller;

import com.safetynet.SafetyNetApplication;
import com.safetynet.service.FirestationService;
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
class FirestationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FirestationService firestationService;
    @Test
    @Order(1)
    void addFirestation() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/firestation?address=222 st realm&caserneID=2"))
                .andExpect(status().isOk());
    }
    @Test
    @Order(2)
    void getFirestations() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/firestation/all"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void getFirestation() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/firestation?address=222 st realm"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void updateFirstation() throws Exception{
        mockMvc.perform(patch("http://localhost:8080/api/firestation?address=222 st realm&caserneID=3"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void deleteFirestation() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/firestation?address=222 st realm"))
                .andExpect(status().isOk());
    }


}