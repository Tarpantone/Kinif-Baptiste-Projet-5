package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.FireDTO;
import com.safetynet.model.DTO.HouseholdDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.FirestationService;
import com.safetynet.service.MedicalrecordService;
import com.safetynet.service.PersonService;
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
public class FireServiceTest {
    @Mock
    HouseholdService householdService;
    @Mock
    PersonService personService;
    @Mock
    MedicalrecordService medicalrecordService;
    @Mock
    FirestationService firestationService;
    @Autowired @InjectMocks
    FireService fireService;

    private static String address;
    private static List<Firestation>firestations;
    private static List<Medicalrecord>medicalrecords;
    private static List<Person>persons;

    @BeforeAll
    public static void setUp(){
        address="address";
        firestations=new ArrayList<>();
        medicalrecords=new ArrayList<>();
        persons=new ArrayList<>();
    }

    @Test
    public void getHouseholdAndItsFirestationTest() throws Exception {
        firestations.add(new Firestation(address,1));
        HouseholdDTO householdDTO=new HouseholdDTO(address,new ArrayList<>());
        when(householdService.getHouseholdAtAddress(address,persons,medicalrecords)).thenReturn(householdDTO);
        when(firestationService.getFirestations()).thenReturn(firestations);
        when(personService.getPersons()).thenReturn(persons);
        when(medicalrecordService.getMedicalrecords()).thenReturn(medicalrecords);
        FireDTO result=fireService.getHouseholdAndItsFirestation(address);
        assertTrue(result.getCaserneID()==1&&result.getHousehold().equals(householdDTO));
    }

}
