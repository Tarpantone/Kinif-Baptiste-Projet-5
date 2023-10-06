package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PersonInfoDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.AgeCalculatorService;
import com.safetynet.service.MedicalrecordService;
import com.safetynet.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PersonInfoService implements PersonInfoInterface{
    private static final Logger personInfoServiceLogger= LoggerFactory.getLogger(PersonInfoService.class);
    @Autowired
    PersonService personService;
    @Autowired
    MedicalrecordService medicalrecordService;
    @Autowired
    AgeCalculatorService ageCalculatorService;
    public List<PersonInfoDTO> getAllPersonInfo()throws Exception{
        personInfoServiceLogger.debug("getAllPersonInfo");
        List<PersonInfoDTO>result=new ArrayList<>();
        List<Person>persons= (List<Person>) personService.getPersons();
        List<Medicalrecord>medicalrecords= (List<Medicalrecord>) medicalrecordService.getMedicalrecords();
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
