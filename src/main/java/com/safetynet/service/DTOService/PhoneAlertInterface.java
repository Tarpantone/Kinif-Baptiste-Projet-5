package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PhoneAlertDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Person;

import java.util.List;

public interface PhoneAlertInterface {
    PhoneAlertDTO getListedPhoneNumberCoveredByStation(List<Person>persons, List<Firestation>firestations,int caserneID);
}
