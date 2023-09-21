package com.safetynet.service;

import com.safetynet.model.DTO.PersonInfoDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public class PersonInfoService implements PersonInfoInterface{
    public List<PersonInfoDTO> getAllPersonInfo(List<Person>persons, List<Medicalrecord>medicalrecords)throws Exception{
        List<PersonInfoDTO>result=null;
        persons.stream().forEach(p->{
            try {
                int age=AgeCalculatorService.calculateAgeOfAPerson((medicalrecords.stream().filter(m->m.getFirstName().equals(p.getFirstName())&&m.getLastName().equals(p.getLastName())).findAny().orElse(null)).getBirthdate());
                List<String>medications=medicalrecords.stream().filter(m->m.getFirstName().equals(p.getFirstName())&&m.getLastName().equals(p.getLastName())).findAny().orElse(null).getMedications();
                List<String>allergies=medicalrecords.stream().filter(m->m.getFirstName().equals(p.getFirstName())&&m.getLastName().equals(p.getLastName())).findAny().orElse(null).getAllergies();
                result.add(new PersonInfoDTO(p.getLastName(),p.getAddress(),age,p.getAddress(),medications,allergies));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }
}
