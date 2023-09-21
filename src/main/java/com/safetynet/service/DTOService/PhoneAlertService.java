package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PhoneAlertDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Person;

import java.util.List;

public class PhoneAlertService implements PhoneAlertInterface{
    public PhoneAlertDTO getListedPhoneNumberCoveredByStation(List<Person> persons, List<Firestation>firestations, int caserneID){
        List<String>phoneNumber=null;
        firestations.stream().filter(f->f.getStation()==caserneID).forEach(f->
            persons.stream().filter(p->p.getAddress().equals(f.getAddress())).forEach(p->phoneNumber.add(p.getPhone()))
        );
        return new PhoneAlertDTO(caserneID,phoneNumber);
    }
}
