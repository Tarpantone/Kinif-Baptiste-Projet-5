package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.HouseholdDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface HouseholdInterface {
    HouseholdDTO getHouseholdAtAddress(String address, List<Person> persons, List<Medicalrecord>medicalrecords)throws Exception;
}
