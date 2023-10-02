package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PhoneAlertDTO;
import com.safetynet.model.Firestation;
import com.safetynet.model.Person;
import com.safetynet.service.FirestationService;
import com.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PhoneAlertService implements PhoneAlertInterface{
    @Autowired
    PersonService personService;
    @Autowired
    FirestationService firestationService;
    public PhoneAlertDTO getListedPhoneNumberCoveredByStation(int caserneID){
        List<Firestation>firestations= (List<Firestation>) firestationService.getFirestations();
        List<Person>persons= (List<Person>) personService.getPersons();
        List<String>phoneNumber=new ArrayList<>();
        firestations.stream().filter(f->f.getStation()==caserneID).forEach(f->
            persons.stream().filter(p->p.getAddress().equals(f.getAddress())).forEach(p->phoneNumber.add(p.getPhone()))
        );
        return new PhoneAlertDTO(caserneID,phoneNumber);
    }
}
