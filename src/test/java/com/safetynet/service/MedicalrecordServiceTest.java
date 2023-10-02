package com.safetynet.service;

import com.safetynet.model.Medicalrecord;
import com.safetynet.repository.MedicalrecordRepository;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalrecordServiceTest {
    @Mock
    MedicalrecordRepository medicalrecordRepository;
    @InjectMocks@Autowired
    MedicalrecordService medicalrecordService;

    private static List<Medicalrecord>medicalrecords=new ArrayList<>();
    @BeforeAll
    static void setUp(){
        for(int i=0;i<3;i++){
            medicalrecords.add(new Medicalrecord("firstname"+i,"lastname"+i,"birthdate"+i,new ArrayList<>(),new ArrayList<>()));
        }
    }
    @Test
    void getMedicalrecord() {
        when(medicalrecordRepository.getMedicalrecord(anyString(),anyString())).thenReturn(medicalrecords.get(0));
        Optional<Medicalrecord>result=medicalrecordService.getMedicalrecord("firstname1","lastname1");
        assertTrue(result.get().equals(medicalrecords.get(0)));
    }

    @Test
    void getMedicalrecords() {
        when(medicalrecordRepository.getMedicalrecords()).thenReturn(medicalrecords);
        List<Medicalrecord>result= (List<Medicalrecord>) medicalrecordService.getMedicalrecords();
        assertFalse(result.isEmpty());
    }

    @Test
    void addMedicalrecord() {
        when(medicalrecordRepository.addMedicalrecords(anyString(),anyString(),anyString(),anyList(),anyList())).thenReturn(true);
        Boolean result=medicalrecordService.addMedicalrecord(anyString(),anyString(),anyString(),anyList(),anyList());
        assertTrue(result);
    }

    @Test
    void deleteMedicalrecord() {
        when(medicalrecordRepository.deleteMedicalrecords(anyString(),anyString())).thenReturn(true);
        Boolean result=medicalrecordService.deleteMedicalrecord(anyString(),anyString());
        assertTrue(result);
    }

    @Test
    void updateMedicalrecord() {
        when(medicalrecordRepository.updateMedicalrecords(anyString(),anyString(),anyString(),anyList(),anyList())).thenReturn(new Medicalrecord());
        Medicalrecord result=medicalrecordService.updateMedicalrecord(anyString(),anyString(),anyString(),anyList(),anyList());
        assertNotNull(result);
    }
}
