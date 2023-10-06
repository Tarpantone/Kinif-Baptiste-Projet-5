package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.FireDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.FirestationService;
import com.safetynet.service.MedicalrecordService;
import com.safetynet.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FireService implements FireInterface{
    private static final Logger fireServiceLogger= LoggerFactory.getLogger(FloodService.class);
    @Autowired
    HouseholdService householdService;
    @Autowired
    PersonService personService;
    @Autowired
    FirestationService firestationService;
    @Autowired
    MedicalrecordService medicalrecordService;
    public FireDTO getHouseholdAndItsFirestation(String address)throws Exception {
        fireServiceLogger.debug("getHouseholdAndItsFirestation");
        List<Person>persons= (List<Person>) personService.getPersons();
        List<Medicalrecord>medicalrecords= (List<Medicalrecord>) medicalrecordService.getMedicalrecords();
        List<Firestation>firestations= (List<Firestation>) firestationService.getFirestations();
        int caserneID=firestations.stream().filter(f->f.getAddress().equals(address)).findAny().orElse(null).getStation();
        return new FireDTO(caserneID,this.householdService.getHouseholdAtAddress(address,persons,medicalrecords));
    }
}
