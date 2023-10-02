package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PhoneAlertDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.FirestationService;
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
public class PhoneAlertSericeTest {
    @Mock
    PersonService personService;
    @Mock
    FirestationService firestationService;
    @Autowired@InjectMocks
    PhoneAlertService phoneAlertService;
    private static int caserneID;
    private static List<Person>persons;
    private static List<Firestation>firestations;

    @BeforeAll
    public static void setUp(){
        caserneID=1;
        persons=new ArrayList<>();
        firestations=new ArrayList<>();
        firestations.add(new Firestation("address0",caserneID));
        firestations.add(new Firestation("address1",caserneID));
        firestations.add(new Firestation("address",caserneID+1));
        for(int i=0;i<6;i++){
            String firstname="firstname"+i;
            String lastname="lastname"+i;
            String address="address"+i%3;
            String phone="phone"+i;
            String email="email"+i;
            String city="city"+i;
            int zip=i;
            persons.add(new Person(firstname,lastname,address,city,zip,phone,email));
        }
    }
    @Test
    public void getListedPhoneNumberCoveredByStationTest(){
        when(personService.getPersons()).thenReturn(persons);
        when(firestationService.getFirestations()).thenReturn(firestations);
        PhoneAlertDTO result=phoneAlertService.getListedPhoneNumberCoveredByStation(caserneID);
        assertTrue(result.getCaserneID()==1&&result.getPhoneNumbers().size()==4);
    }
}
