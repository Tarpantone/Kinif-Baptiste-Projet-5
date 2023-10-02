package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.ChildDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.AgeCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ChildService implements ChildInterface{
    @Autowired
    AgeCalculatorService ageCalculatorService;
    @Override
    public List<ChildDTO> getChildsAtThisAddress(List<Person> persons, List<Medicalrecord> medicalrecords, String address) throws Exception{
        List<ChildDTO>childs=new ArrayList<>();
        persons.stream().filter(
                p->p.getAddress().equals(address) &&medicalrecords.stream().anyMatch(m-> {
                    try {
                        return m.getFirstName().equals(p.getFirstName())
                                &&m.getLastName().equals(p.getLastName())
                                && this.ageCalculatorService.calculateAgeOfAPerson(m.getBirthdate())<=18;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
        ).forEach(p->{
            try {
                int age=this.ageCalculatorService.calculateAgeOfAPerson((medicalrecords.stream().filter(m->m.getFirstName().equals(p.getFirstName())&&m.getLastName().equals(p.getLastName())).findAny().orElse(null)).getBirthdate());
                childs.add(new ChildDTO(p.getFirstName(),p.getLastName(),age));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return childs;
    }
}
