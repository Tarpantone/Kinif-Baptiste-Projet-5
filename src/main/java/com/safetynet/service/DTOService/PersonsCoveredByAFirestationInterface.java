package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PersonsCoveredByAFirestationDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface PersonsCoveredByAFirestationInterface {
    PersonsCoveredByAFirestationDTO getPersonsCoveredByAFirestation(int caserneID, List<Person> persons, List<Medicalrecord>medicalrecords, List<Firestation>firestations)throws Exception;
}
