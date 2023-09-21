package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PersonsCoveredByAFirestationDTO;
import com.safetynet.model.DTO.PersonCoveredInfoDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.AgeCalculatorService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PersonsCoveredByAFirestationService implements PersonsCoveredByAFirestationInterface {
    public PersonsCoveredByAFirestationDTO getPersonsCoveredByAFirestation(int caserneID, List<Person>persons, List<Medicalrecord>medicalrecords, List<Firestation>firestations)throws Exception{
        AtomicInteger childCount=new AtomicInteger(0);
        AtomicInteger adultCount=new AtomicInteger(0);
        List<PersonCoveredInfoDTO>personsCovered=null;
        firestations.stream().filter(f->f.getStation()==caserneID).forEach(f->{
                persons.stream().filter(p->p.getAddress().equals(f.getAddress())).forEach(p->{
                    try {
                        int age= AgeCalculatorService.calculateAgeOfAPerson((medicalrecords.stream().filter(m->m.getFirstName().equals(p.getFirstName())&&m.getLastName().equals(p.getLastName())).findAny().orElse(null)).getBirthdate());
                        if (age<=18){childCount.getAndIncrement();}else{adultCount.getAndIncrement();}
                        personsCovered.add(new PersonCoveredInfoDTO(p.getFirstName(),p.getLastName(),p.getAddress(),p.getPhone()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        });
        return new PersonsCoveredByAFirestationDTO(caserneID,personsCovered,childCount.intValue(),adultCount.intValue());
    }
}
