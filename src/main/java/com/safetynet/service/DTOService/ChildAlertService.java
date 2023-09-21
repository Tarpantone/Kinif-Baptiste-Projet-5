package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.ChildDTO;
import com.safetynet.model.DTO.ChildAlertDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.AgeCalculatorService;


import java.util.List;
import java.util.stream.Collectors;

public class ChildAlertService implements ChildAlertInetrface {

    public List<Person> getHousehold(List<Person> persons, List<Medicalrecord>medicalrecords, String address)throws Exception{
        List<Person>household=persons.stream().filter(
                p->p.getAddress().equals(address) &&medicalrecords.stream().anyMatch(m-> {
                    try {
                        return m.getFirstName().equals(p.getFirstName())
                                &&m.getLastName().equals(p.getLastName())
                                &&AgeCalculatorService.calculateAgeOfAPerson(m.getBirthdate())>18;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
        ).collect(Collectors.toList());
        return household;
    }

    public ChildAlertDTO getChildWithHousehold(List<ChildDTO>childs, List<Person>household)throws Exception{
        return new ChildAlertDTO(childs,household);
    }
}
