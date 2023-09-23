package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.FloodDTO;
import com.safetynet.model.DTO.HouseholdDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FloodService implements Floodinterface {
    @Autowired
    HouseholdService householdService;
    @Override
    public FloodDTO getAllHouseholdCoveredByAFirestation(int caserneID, List<Firestation>firestations, List<Person>persons, List<Medicalrecord>medicalrecords)throws Exception{
        List<HouseholdDTO>household=new ArrayList<>();
        firestations.stream().filter(f->f.getStation()==caserneID).forEach(f->{
            try {
                household.add(this.householdService.getHouseholdAtAddress(f.getAddress(),persons,medicalrecords));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return new FloodDTO(caserneID, household);
    }
}
