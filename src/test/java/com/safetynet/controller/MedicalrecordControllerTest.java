package com.safetynet.controller;

import com.safetynet.SafetyNetApplication;
import com.safetynet.model.Medicalrecord;
import com.safetynet.service.MedicalrecordService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SafetyNetApplication.class)
@AutoConfigureMockMvc
class MedicalrecordControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MedicalrecordService medicalrecordService;
    private static List<Medicalrecord>medicalrecords=new ArrayList<>();

    @BeforeAll
    static void setUp(){
        medicalrecords.add(new Medicalrecord("John","Boyd","18/02/1983",new ArrayList<>(),new ArrayList<>()));
        medicalrecords.add(new Medicalrecord("Lisa","Boyd","18/02/1986",new ArrayList<>(),new ArrayList<>()));
    }
    @Test
    void addMedicalrecordTestWhereStatusIsOK() throws Exception {
        when(medicalrecordService.addMedicalrecord(anyString(),anyString(),anyString(),anyList(),anyList())).thenReturn(true);
        mockMvc.perform(post("http://localhost:8080/api/medicalrecord?firstname=Lisa&lastname=Boyd&birthdate=18/02/1983&medications=azenol:200mg,reactine:1mg&allergies="))
                .andExpect(status().isOk());
    }
    @Test
    void addMedicalrecordTestWhereStatusIsINTERNAL_SERVER_ERROR() throws Exception {
        when(medicalrecordService.addMedicalrecord(anyString(),anyString(),anyString(),anyList(),anyList())).thenReturn(false);
        mockMvc.perform(post("http://localhost:8080/api/medicalrecord?firstname=Lisa&lastname=Boyd&birthdate=18/02/1983&medications=azenol:200mg,reactine:1mg&allergies="))
                .andExpect(status().isInternalServerError());
    }
    @Test
    void getMedicalrecordsTestWhereStatusIsOK() throws Exception {
        when(medicalrecordService.getMedicalrecords()).thenReturn(medicalrecords);
        mockMvc.perform(get("http://localhost:8080/api/medicalrecord/all"))
                .andExpect(status().isOk());
    }
    @Test
    void getMedicalrecordsTestWhereStatusIsINTERNAL_SERVER_ERROR() throws Exception {
        List<Medicalrecord>emptyList=new ArrayList<>();
        when(medicalrecordService.getMedicalrecords()).thenReturn(emptyList);
        mockMvc.perform(get("http://localhost:8080/api/medicalrecord/all"))
                .andExpect(status().isInternalServerError());
    }
    @Test
    void getMedicalrecordTestWhereStatusIsOK() throws Exception {
        when(medicalrecordService.getMedicalrecord(anyString(),anyString())).thenReturn(Optional.of(medicalrecords.get(0)));
        mockMvc.perform(get("http://localhost:8080/api/medicalrecord?firstname=John&lastname=Boyd"))
                .andExpect(status().isOk());
    }
    @Test
    void getMedicalrecordTestWhereStatusIsNOT_FOUND() throws Exception {
        when(medicalrecordService.getMedicalrecord(anyString(),anyString())).thenReturn(Optional.of(medicalrecords.get(1)));
        mockMvc.perform(get("http://localhost:8080/api/medicalrecord?firstname=John&lastname=Boyd"))
                .andExpect(status().isNotFound());
    }
    @Test
    void updateMedicalrecordTestWhereStatusIsOK() throws Exception {
        when(medicalrecordService.updateMedicalrecord(anyString(),anyString(),anyString(),anyList(),anyList())).thenReturn(medicalrecords.get(0));
        mockMvc.perform(patch("http://localhost:8080/api/medicalrecord?firstname=John&lastname=Boyd&birthdate=18/02/1983&medications=azenol:200mg,reactine:1mg&allergies=Poopilechat"))
                .andExpect(status().isOk());
    }
    @Test
    void updateMedicalrecordTestWhereStatusIsNOT_FOUND() throws Exception {
        when(medicalrecordService.updateMedicalrecord(anyString(),anyString(),anyString(),anyList(),anyList())).thenReturn(medicalrecords.get(1));
        mockMvc.perform(patch("http://localhost:8080/api/medicalrecord?firstname=John&lastname=Boyd&birthdate=18/02/1983&medications=azenol:200mg,reactine:1mg&allergies=Poopilechat"))
                .andExpect(status().isNotFound());
    }
    @Test
    void deleteMedicalrecordTestWhereStatusIsOK() throws Exception {
        when(medicalrecordService.deleteMedicalrecord(anyString(),anyString())).thenReturn(true);
        mockMvc.perform(delete("http://localhost:8080/api/medicalrecord?firstname=Lisa&lastname=Boyd"))
                .andExpect(status().isOk());
    }
    @Test
    void deleteMedicalrecordTestWhereStatusIsNOT_FOUND() throws Exception {
        when(medicalrecordService.deleteMedicalrecord(anyString(),anyString())).thenReturn(false);
        mockMvc.perform(delete("http://localhost:8080/api/medicalrecord?firstname=Lisa&lastname=Boyd"))
                .andExpect(status().isNotFound());
    }
}