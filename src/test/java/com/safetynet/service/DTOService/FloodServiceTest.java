package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.FloodDTO;
import com.safetynet.model.DTO.HouseholdDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FloodServiceTest {
    @Mock
    HouseholdService householdService;
    @Autowired @InjectMocks
    FloodService floodService;

    private static int caserneID;
    private static List<Firestation>firestations;
    private static List<Person>persons;
    private static List<Medicalrecord>medicalrecords;

    @BeforeAll
    public static void setUp(){
        caserneID=1;
        firestations=new ArrayList<>();
        medicalrecords=new ArrayList<>();
        persons=new ArrayList<>();
    }
    @Test
    public void getAllHouseholdCoveredByAFirestationTest() throws Exception {
        firestations.add(new Firestation("zurganar",caserneID));
        firestations.add(new Firestation("Pokoloc",caserneID));
        firestations.add((new Firestation("zguigui",caserneID+1)));

        when(householdService.getHouseholdAtAddress("zurganar",persons,medicalrecords)).thenReturn(new HouseholdDTO("zurganar",new ArrayList<>()));
        when(householdService.getHouseholdAtAddress("Pokoloc",persons,medicalrecords)).thenReturn(new HouseholdDTO("Pokoloc",new ArrayList<>()));
        FloodDTO result=floodService.getAllHouseholdCoveredByAFirestation(caserneID,firestations,persons,medicalrecords);
        assertTrue(result.getCaserneID()==caserneID&&result.getHouseholds().size()==2);
    }
}
