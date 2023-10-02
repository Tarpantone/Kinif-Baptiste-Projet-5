package com.safetynet.service;

import com.safetynet.model.Firestation;
import com.safetynet.repository.FirestationRepository;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {
    @Mock
    FirestationRepository firestationRepository;

    @InjectMocks@Autowired
    FirestationService firestationService;

    private static List<Firestation>firestations=new ArrayList<>();

    @BeforeAll
    static void setUp(){
        for(int i=0;i<3;i++){
            firestations.add(new Firestation("address"+i,i));
        }
    }

    @Test
    void getFirestation() {
        when(firestationRepository.getFirestation(anyString())).thenReturn(firestations.get(0));
        Optional<Firestation> result=firestationService.getFirestation("address0");
        assertTrue(result.get().getAddress().equals("address0"));
    }

    @Test
    void getFirestations() {
        when(firestationRepository.getFirestations()).thenReturn(firestations);
        List<Firestation> result= (List<Firestation>) firestationService.getFirestations();
        assertFalse(result.isEmpty());
    }

    @Test
    void deleteFirestation() {
        when(firestationRepository.deleteFirestation(anyString())).thenReturn(true);
        boolean result=firestationService.deleteFirestation("address0");
        assertTrue(result);
    }

    @Test
    void addFirestation() {
        when(firestationRepository.addFirestation("address3",3)).thenReturn(true);
        boolean result=firestationService.addFirestation("address3",3);
        assertTrue(result);
    }

    @Test
    void updateFirestation() {
        when(firestationRepository.updateFirestation(anyString(),anyInt())).thenReturn(new Firestation("address2",6));
        Firestation result=firestationService.updateFirestation("address2",6);
        assertTrue(result.getAddress().equals("address2")&&result.getStation()==6);
    }
}
