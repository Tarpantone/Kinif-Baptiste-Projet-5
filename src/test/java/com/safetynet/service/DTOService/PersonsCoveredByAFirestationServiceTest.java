package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PersonsCoveredByAFirestationDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.AgeCalculatorService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonsCoveredByAFirestationServiceTest {
    @Mock
    AgeCalculatorService ageCalculatorService;
    @Mock
    PersonService personService;
    @Mock
    MedicalrecordService medicalrecordService;
    @Mock
    FirestationService firestationService;
    @Autowired@InjectMocks
    PersonsCoveredByAFirestationService personsCoveredByAFirestationService;
    private static int caserneID;
    private static List<Person>persons;
    private static List<Medicalrecord>medicalrecords;
    private static List<Firestation>firestations;
    @BeforeAll
    public static void setUp(){
        caserneID=1;
        persons=new ArrayList<>();
        medicalrecords=new ArrayList<>();
        firestations=new ArrayList<>();
        firestations.add(new Firestation("address0",caserneID));
        firestations.add(new Firestation("address1",caserneID));
        firestations.add(new Firestation("address",caserneID+1));
        for(int i=0;i<5;i++){
            String firstname="firstname"+i;
            String lastname="lastname"+i;
            String address="address"+i%2;
            String phone="phone"+i;
            String email="email"+i;
            String city="city"+i;
            int zip=i;
            persons.add(new Person(firstname,lastname,address,city,zip,phone,email));
            medicalrecords.add(new Medicalrecord(firstname,lastname,"birthdate",new ArrayList<>(),new ArrayList<>()));
        }
    }
    @Test
    public void getPersonsCoveredByAFirestationTest() throws Exception {
        when(personService.getPersons()).thenReturn(persons);
        when(medicalrecordService.getMedicalrecords()).thenReturn(medicalrecords);
        when(firestationService.getFirestations()).thenReturn(firestations);
        when(ageCalculatorService.calculateAgeOfAPerson(anyString())).thenReturn(4,6,58,98,74);
        PersonsCoveredByAFirestationDTO result=personsCoveredByAFirestationService.getPersonsCoveredByAFirestation(caserneID);
        verify(ageCalculatorService,times(5)).calculateAgeOfAPerson(anyString());
        assertTrue(result.getAdultCount()==3&&result.getChildCount()==2&&!result.getPersonsCovered().isEmpty());
    }
}
