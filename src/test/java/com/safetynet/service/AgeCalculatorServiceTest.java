package com.safetynet.service;

import com.safetynet.SafetyNetApplication;
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
    public void calculateAgeOfAPersonActuallyBornedTest() throws Exception{
        LocalDate birthdate=LocalDate.now();
        birthdate=birthdate.minusYears(400);
        AgeCalculatorService ageCalculatorService=new AgeCalculatorService();
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE);
        String birthday=birthdate.format(dtf);
        int result=ageCalculatorService.calculateAgeOfAPerson(birthday);
        assertEquals(400,result);
    }

    @Test
    public void calculateAgeOfAPersonNotBornedYet()throws Exception{
        AgeCalculatorService ageCalculatorService=new AgeCalculatorService();
        LocalDate birthdate=LocalDate.now();
        birthdate=birthdate.plusYears(400);
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE);
        String birthday=birthdate.format(dtf);
        int result=ageCalculatorService.calculateAgeOfAPerson(birthday);
        assertEquals(-1,result);
    }

    @Test
    public void calculateAgeOfAPersonWrongDateFormat()throws Exception{
        AgeCalculatorService ageCalculatorService=new AgeCalculatorService();
        int result=ageCalculatorService.calculateAgeOfAPerson("1996/11/07");
        assertEquals(-2,result);
    }
}
