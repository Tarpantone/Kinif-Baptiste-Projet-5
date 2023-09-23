package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.AdultDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.AgeCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdultServiceTest {
    @Mock
    AgeCalculatorService ageCalculatorService;

    @Autowired @InjectMocks
    AdultService adultService;

    private Person Jhon;
    private Person Marina;
    private Person Sam;
    private List<Person> persons;
    private Medicalrecord JhonsMedicalrecord;
    private Medicalrecord MarinasMedicalrecord;
    private Medicalrecord SamsMedicalrecord;
    private List<Medicalrecord>medicalrecords;
    @BeforeEach
    private void setUp(){
        Jhon=new Person("Jhon","Doe","945 Street","Bagdad",9874,"0605487","mail@mail.mail");
        Marina=new Person("Marina","Doe","945 Street","Bagdad",9873,"0605487","mail@mail.mail");
        Sam=new Person("Sam","Doe","945 Street","Bagdad",9872,"0605487","mail@mail.mail");
        persons=new ArrayList<>();
        persons.add(Jhon);
        persons.add(Marina);
        persons.add(Sam);

        JhonsMedicalrecord=new Medicalrecord("Jhon","Doe","JhonsBirthday",null,null);
        MarinasMedicalrecord=new Medicalrecord("Marina","Doe","MarinasBirthday",null,null);
        SamsMedicalrecord=new Medicalrecord("Sam","Doe","SamsBirthday",null,null);
        medicalrecords=new ArrayList<>();
        medicalrecords.add(JhonsMedicalrecord);
        medicalrecords.add(MarinasMedicalrecord);
        medicalrecords.add(SamsMedicalrecord);
    }
    @Test
    public void getAdultsAtThisAddressWhereThereIsAdultTest(){
        try {
            when(ageCalculatorService.calculateAgeOfAPerson(eq("JhonsBirthday"))).thenReturn(115);
            when(ageCalculatorService.calculateAgeOfAPerson(eq("MarinasBirthday"))).thenReturn(134);
            when(ageCalculatorService.calculateAgeOfAPerson(eq("SamsBirthday"))).thenReturn(110);
            List<AdultDTO> result=adultService.getAdultsAtThisAddress(persons,medicalrecords,"945 Street");
            verify(ageCalculatorService,times(3)).calculateAgeOfAPerson(any());
            assertFalse(result.isEmpty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAdultsAtThisAddressWhereThereIsNoAdultTest(){
        try {
            when(ageCalculatorService.calculateAgeOfAPerson(eq("JhonsBirthday"))).thenReturn(12);
            when(ageCalculatorService.calculateAgeOfAPerson(eq("MarinasBirthday"))).thenReturn(14);
            when(ageCalculatorService.calculateAgeOfAPerson(eq("SamsBirthday"))).thenReturn(18);
            List<AdultDTO> result=adultService.getAdultsAtThisAddress(persons,medicalrecords,"945 Street");
            verify(ageCalculatorService,times(3)).calculateAgeOfAPerson(any());
            assertTrue(result.isEmpty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
