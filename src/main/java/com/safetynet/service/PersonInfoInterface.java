package com.safetynet.service;

import com.safetynet.model.DTO.PersonInfoDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface PersonInfoInterface {
    List<PersonInfoDTO> getAllPersonInfo(List<Person>persons, List<Medicalrecord>medicalrecords)throws Exception;
}
