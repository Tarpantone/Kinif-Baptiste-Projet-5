package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PersonInfoDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.AgeCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PersonInfoService implements PersonInfoInterface{
    @Autowired
    AgeCalculatorService ageCalculatorService;
    public List<PersonInfoDTO> getAllPersonInfo(List<Person>persons, List<Medicalrecord>medicalrecords)throws Exception{
        List<PersonInfoDTO>result=new ArrayList<>();
        persons.stream().forEach(p->{
            try {
                int age= this.ageCalculatorService.calculateAgeOfAPerson((medicalrecords.stream().filter(m->m.getFirstName().equals(p.getFirstName())&&m.getLastName().equals(p.getLastName())).findAny().orElse(null)).getBirthdate());
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
