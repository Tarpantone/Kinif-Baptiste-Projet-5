package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.AdultDTO;
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
public class AdultService implements AdultInterface {
    private static final Logger adultServiceLogger= LoggerFactory.getLogger(AdultService.class);
    @Autowired
    private  AgeCalculatorService ageCalculatorService;
    public List<AdultDTO> getAdultsAtThisAddress(List<Person> persons, List<Medicalrecord>medicalrecords, String address)throws Exception{
        adultServiceLogger.debug("getAdultsAtThisAddress");
        List<AdultDTO>adults= new ArrayList<>();
        persons.stream().filter(
                p->p.getAddress().equals(address) &&medicalrecords.stream().anyMatch(m-> {
                    try {
                        return m.getFirstName().equals(p.getFirstName())
                                &&m.getLastName().equals(p.getLastName())
                                &&this.ageCalculatorService.calculateAgeOfAPerson(m.getBirthdate())>18;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
        ).forEach(p->{
            adults.add(new AdultDTO(p.getFirstName(),p.getLastName()));
        });
        return adults;
    }
}
