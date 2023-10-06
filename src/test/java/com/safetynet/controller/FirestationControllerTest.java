package com.safetynet.controller;

import com.safetynet.SafetyNetApplication;
import com.safetynet.model.Firestation;
import com.safetynet.service.FirestationService;
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
class FirestationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FirestationService firestationService;
    private static List<Firestation> firestations=new ArrayList<>();

    @BeforeAll
    static void setUp(){
        firestations.add(new Firestation("222 st realm",3));
    }
    @Test
    void addFirestationWhereStatusIsOK() throws Exception {
        when(firestationService.addFirestation(anyString(),anyInt())).thenReturn(true);
        mockMvc.perform(post("http://localhost:8080/api/firestation?address=222 st realm&caserneID=2"))
                .andExpect(status().isOk());
    }
    @Test
    void addFirestationWhereStatusIsINTERNAL_SERVER_ERROR() throws Exception {
        when(firestationService.addFirestation(anyString(),anyInt())).thenReturn(false);
        mockMvc.perform(post("http://localhost:8080/api/firestation?address=222 st realm&caserneID=2"))
                .andExpect(status().isInternalServerError());
    }
    @Test
    void getFirestationsWhereStatusIsOK() throws Exception {
        when(firestationService.getFirestations()).thenReturn(firestations);
        mockMvc.perform(get("http://localhost:8080/api/firestation/all"))
                .andExpect(status().isOk());
    }
    @Test
    void getFirestationsWhereStatusIsINTERNAL_SERVER_ERROR() throws Exception {
        List<Firestation>emptyList=new ArrayList<>();
        when(firestationService.getFirestations()).thenReturn(emptyList);
        mockMvc.perform(get("http://localhost:8080/api/firestation/all"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getFirestationWhereStatusIsOK() throws Exception {
        when(firestationService.getFirestation(anyString())).thenReturn(Optional.of(firestations.get(0)));
        mockMvc.perform(get("http://localhost:8080/api/firestation?address=222 st realm"))
                .andExpect(status().isOk());
    }
    @Test
    void getFirestationWhereStatusIsNOT_FOUND() throws Exception {
        when(firestationService.getFirestation(anyString())).thenReturn(Optional.of(new Firestation("wrong",-1)));
        mockMvc.perform(get("http://localhost:8080/api/firestation?address=222 st realm"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateFirstationWhereStatusIsOK() throws Exception{
        when(firestationService.updateFirestation(anyString(),anyInt())).thenReturn(firestations.get(0));
        mockMvc.perform(patch("http://localhost:8080/api/firestation?address=222 st realm&caserneID=3"))
                .andExpect(status().isOk());
    }
    @Test
    void updateFirstationWhereStatusIsNOT_FOUND() throws Exception{
        when(firestationService.updateFirestation(anyString(),anyInt())).thenReturn(new Firestation("wrong",1));
        mockMvc.perform(patch("http://localhost:8080/api/firestation?address=222 st realm&caserneID=3"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteFirestationWhereStatusIsOK() throws Exception {
        when(firestationService.deleteFirestation(anyString())).thenReturn(true);
        mockMvc.perform(delete("http://localhost:8080/api/firestation?address=222 st realm"))
                .andExpect(status().isOk());
    }
    @Test
    void deleteFirestationWhereStatusIsNOT_FOUND() throws Exception {
        when(firestationService.deleteFirestation(anyString())).thenReturn(false);
        mockMvc.perform(delete("http://localhost:8080/api/firestation?address=222 st realm"))
                .andExpect(status().isNotFound());
    }
}