package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.FireDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface FireInterface {
    FireDTO getHouseholdAndItsFirestation(String address)throws Exception;
}
