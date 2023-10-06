package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.HouseholdDTO;
import com.safetynet.model.DTO.HouseholdMemberDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.AgeCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class HouseholdService implements HouseholdInterface{
    private static final Logger householdServiceLogger= LoggerFactory.getLogger(HouseholdService.class);
    @Autowired
    AgeCalculatorService ageCalculatorService;
    public HouseholdDTO getHouseholdAtAddress(String address, List<Person>persons, List<Medicalrecord>medicalrecords)throws Exception{
        householdServiceLogger.debug("getHouseholdAtAddress");
        HouseholdDTO household = new HouseholdDTO(address,new ArrayList<>());
        persons.stream().filter(p->p.getAddress().equals(address)).forEach(p->{
            medicalrecords.stream().filter(m->m.getFirstName().equals(p.getFirstName())&&m.getLastName().equals(p.getLastName())).forEach(m->{
                try {
                    int age= ageCalculatorService.calculateAgeOfAPerson(m.getBirthdate());
                    household.getHouseholdMembers().add(new HouseholdMemberDTO(p.getLastName(),p.getPhone(),age,m.getMedications(),m.getAllergies()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
        return household;
    }
}
