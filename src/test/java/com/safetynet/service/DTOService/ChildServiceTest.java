package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.ChildDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChildServiceTest {

    @Mock
    AgeCalculatorService ageCalculatorService;

    @Autowired @InjectMocks
    ChildService childService;
    private Person Jhon;
    private Person Marina;
    private Person Sam;
    private List<Person>persons;
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
    public void getChildsAtThisAddressWhereThereIsChildTest(){
        try {
            when(ageCalculatorService.calculateAgeOfAPerson(eq("JhonsBirthday"))).thenReturn(15);
            when(ageCalculatorService.calculateAgeOfAPerson(eq("MarinasBirthday"))).thenReturn(34);
            when(ageCalculatorService.calculateAgeOfAPerson(eq("SamsBirthday"))).thenReturn(10);
            List<ChildDTO> result=childService.getChildsAtThisAddress(persons,medicalrecords,"945 Street");
            verify(ageCalculatorService,times(5)).calculateAgeOfAPerson(any());
            assertFalse(result.isEmpty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getChildsAtThisAddressWhereThereIsNoChildTest(){
        try {
            when(ageCalculatorService.calculateAgeOfAPerson(eq("JhonsBirthday"))).thenReturn(52);
            when(ageCalculatorService.calculateAgeOfAPerson(eq("MarinasBirthday"))).thenReturn(34);
            when(ageCalculatorService.calculateAgeOfAPerson(eq("SamsBirthday"))).thenReturn(48);
            List<ChildDTO> result=childService.getChildsAtThisAddress(persons,medicalrecords,"945 Street");
            verify(ageCalculatorService,times(3)).calculateAgeOfAPerson(any());
            assertTrue(result.isEmpty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
