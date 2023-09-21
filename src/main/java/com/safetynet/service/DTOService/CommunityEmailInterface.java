package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.CommunityEmailDTO;
import com.safetynet.model.Person;

import java.util.List;

public interface CommunityEmailInterface {
    CommunityEmailDTO getCommunityEmails(List<Person>persons);
}
