package com.safetynet.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
@Service
public class AgeCalculatorService implements AgeCalculatorInterface {
    public int calculateAgeOfAPerson(String birthday) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE);
            LocalDate birthdate = LocalDate.parse(birthday, formatter);
            LocalDate nowadays = LocalDate.now();
            Period age = Period.between(birthdate, nowadays);
            if (age.getYears() >= 0) {
                return age.getYears();
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e);
            return -2;
        }
    }
}
