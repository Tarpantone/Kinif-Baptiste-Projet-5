package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.CommunityEmailDTO;
import com.safetynet.model.Person;
import com.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommunityEmailService implements CommunityEmailInterface{

    @Autowired
    PersonService personService;
    @Override
    public CommunityEmailDTO getCommunityEmails(String city) {
        List<String>emails=new ArrayList<>();
        List<Person>persons= (List<Person>) personService.getPersons();
        persons.stream().filter(p->p.getCity().equals(city)).forEach(p->emails.add(p.getEmail()));
        return  new CommunityEmailDTO(emails);
    }
}
