package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PersonInfoDTO;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonInfoServiceTest {
    @Mock
    AgeCalculatorService ageCalculatorService;
    @Autowired @InjectMocks
    PersonInfoService personInfoService;

    private static List<Person>persons;
    private static List<Medicalrecord>medicalrecords;

    @BeforeAll
    public static void setUp(){
        persons=new ArrayList<>();
        medicalrecords=new ArrayList<>();
        for(int i=0;i<4;i++){
            persons.add(new Person("firstname"+i,"lastname"+i,"adresse"+i,"city"+i,i,"phone"+i,"email"+i));
            medicalrecords.add(new Medicalrecord("firstname"+i,"lastname"+i,"birthdate"+i,new ArrayList<>(),new ArrayList<>()));
        }
    }
    @Test
    public  void getAllPersonInfoTest() throws Exception {
        when(ageCalculatorService.calculateAgeOfAPerson(anyString())).thenReturn(25,72,25,546);
        List<PersonInfoDTO>result=personInfoService.getAllPersonInfo(persons,medicalrecords);
        verify(ageCalculatorService,times(4)).calculateAgeOfAPerson(anyString());
        assertFalse(result.isEmpty());
    }
}
