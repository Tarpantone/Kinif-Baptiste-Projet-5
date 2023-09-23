package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PhoneAlertDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneAlertSericeTest {

    PhoneAlertService phoneAlertService=new PhoneAlertService();
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
        PhoneAlertDTO result=phoneAlertService.getListedPhoneNumberCoveredByStation(persons,firestations,caserneID);
        assertTrue(result.getCaserneID()==1&&result.getPhoneNumbers().size()==4);
    }
}
