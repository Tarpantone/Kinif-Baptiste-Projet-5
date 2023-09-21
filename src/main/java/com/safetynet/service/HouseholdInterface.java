package com.safetynet.service;

import com.safetynet.model.DTO.HouseholdDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface HouseholdInterface {
    public static HouseholdDTO getHouseholdAtAddress(String address, List<Person> persons, List<Medicalrecord>medicalrecords)throws Exception{
        return null;
    }
}
