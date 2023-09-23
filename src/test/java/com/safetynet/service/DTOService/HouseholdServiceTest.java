package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.HouseholdDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.AgeCalculatorService;
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
public class HouseholdServiceTest {
    @Mock
    AgeCalculatorService ageCalculatorService;

    @Autowired
    @InjectMocks
    HouseholdService householdService;

    private static String address;
    private static List<Person> persons;
    private static List<Medicalrecord>medicalrecords;

    @BeforeAll
    public static void setUp(){
        address="address";
        persons=new ArrayList<>();
        medicalrecords=new ArrayList<>();
        for (int i=0;i<3;i++){
            String firstname="firstname"+i;
            String lastname="lastname"+i;
            String birthdate="birthdate"+i;
            persons.add(new Person(firstname,lastname,address,"city",i,"phone","email"));
            medicalrecords.add((new Medicalrecord(firstname,lastname,birthdate,new ArrayList<>(),new ArrayList<>())));
        }
        persons.add(new Person("a","b","c","d",1,"e","f"));
        medicalrecords.add(new Medicalrecord("a","b","c",new ArrayList<>(),new ArrayList<>()));
    }
    @Test
    public void getHouseholdAtAddress() throws Exception {
        when(ageCalculatorService.calculateAgeOfAPerson(anyString())).thenReturn(25,27,3);
        HouseholdDTO result=householdService.getHouseholdAtAddress(address,persons,medicalrecords);
        verify(ageCalculatorService,times(3)).calculateAgeOfAPerson(anyString());
        assertTrue(result.getAddress().equals(address)&&result.getHouseholdMembers().size()==3);
    }

}
