package com.safetynet.service;

import com.safetynet.main.SafetyNetApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SafetyNetApplication.class)
public class AgeCalculatorServiceTest {
    @Test
    public void contextLoads(){}
    @Test
    public void calculateAgeofAPersonTest() throws Exception{
        AgeCalculatorService acs=new AgeCalculatorService();
        LocalDate birthdate=LocalDate.now();
        birthdate=birthdate.minusYears(400);
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE);
        String birthday=birthdate.format(dtf);
        int result=acs.calculateAgeofAPerson(birthday);
        assertEquals(400,result);
    }
}
