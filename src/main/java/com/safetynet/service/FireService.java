package com.safetynet.service;

import com.safetynet.model.DTO.FireDTO;
import com.safetynet.model.DTO.HouseholdDTO;
import com.safetynet.model.DTO.HouseholdMemberDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FireService implements FireInterface{
    public FireDTO getHouseholdAndItsFirestation(String address, List<Firestation>firestations, List<Person>persons, List<Medicalrecord>medicalrecords)throws Exception {
        int caserneID=firestations.stream().filter(f->f.getAddress().equals(address)).findAny().orElse(null).getStation();
        return new FireDTO(caserneID,HouseholdService.getHouseholdAtAddress(address,persons,medicalrecords));
    }
}
