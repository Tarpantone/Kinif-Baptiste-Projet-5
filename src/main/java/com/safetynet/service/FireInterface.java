package com.safetynet.service;

import com.safetynet.model.DTO.FireDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface FireInterface {
    FireDTO getHouseholdAndItsFirestation(String address, List<Firestation> firestations, List<Person>persons, List<Medicalrecord>medicalrecords)throws Exception;
}
