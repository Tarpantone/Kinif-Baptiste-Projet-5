package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.FloodDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface Floodinterface {
    public FloodDTO getAllHouseholdCoveredByAFirestation(int caserneID)throws Exception;
}
