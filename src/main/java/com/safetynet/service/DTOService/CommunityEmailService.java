package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.CommunityEmailDTO;
import com.safetynet.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommunityEmailService implements CommunityEmailInterface{
    @Override
    public CommunityEmailDTO getCommunityEmails(List<Person> persons) {
        List<String>emails=new ArrayList<>();
        persons.stream().forEach(p->emails.add(p.getEmail()));
        return  new CommunityEmailDTO(emails);
    }
}
