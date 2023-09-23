package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.FireDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FireService implements FireInterface{
    private HouseholdService householdService;
    public FireDTO getHouseholdAndItsFirestation(String address, List<Firestation>firestations, List<Person>persons, List<Medicalrecord>medicalrecords)throws Exception {
        int caserneID=firestations.stream().filter(f->f.getAddress().equals(address)).findAny().orElse(null).getStation();
        return new FireDTO(caserneID,this.householdService.getHouseholdAtAddress(address,persons,medicalrecords));
    }
}
