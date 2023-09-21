package com.safetynet.service;

import com.safetynet.model.DTO.CommunityEmailDTO;
import com.safetynet.model.Person;

import java.util.List;

public class CommunityEmailService implements CommunityEmailInterface{
    @Override
    public CommunityEmailDTO getCommunityEmails(List<Person> persons) {
        List<String>emails=null;
        persons.stream().forEach(p->emails.add(p.getEmail()));
        return  new CommunityEmailDTO(emails);
    }
}
