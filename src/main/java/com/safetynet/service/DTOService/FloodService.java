package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.FloodDTO;
import com.safetynet.model.DTO.HouseholdDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public class FloodService implements Floodinterface {
    @Override
    public FloodDTO getAllHouseholdCoveredByAFirestation(int caserneID, List<Firestation>firestations, List<Person>persons, List<Medicalrecord>medicalrecords)throws Exception{
        List<HouseholdDTO>household=null;
        firestations.stream().filter(f->f.getStation()==caserneID).forEach(f->{
            try {
                household.add(HouseholdService.getHouseholdAtAddress(f.getAddress(),persons,medicalrecords));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return new FloodDTO(caserneID, household);
    }
}
