package com.safetynet.service;

import com.safetynet.model.DTO.FireDTO;
import com.safetynet.model.DTO.HouseholdDTO;
import com.safetynet.model.DTO.HouseholdMemberDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HouseholdService implements HouseholdInterface{
    public static HouseholdDTO getHouseholdAtAddress(String address, List<Person>persons, List<Medicalrecord>medicalrecords)throws Exception{
        HouseholdDTO household = new HouseholdDTO();
        household.setAddress(address);
        persons.stream().filter(p->p.getAddress().equals(address)).forEach(p->{
            medicalrecords.stream().filter(m->m.getFirstName().equals(p.getFirstName())&&m.getLastName().equals(p.getLastName())).forEach(m->{
                try {
                    int age=AgeCalculatorService.calculateAgeOfAPerson(m.getBirthdate());
                    household.getHouseholdMembers().add(new HouseholdMemberDTO(p.getLastName(),p.getPhone(),age,m.getMedications(),m.getAllergies()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
        return household;
    }
}
