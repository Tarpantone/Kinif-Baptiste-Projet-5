package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.PersonInfoDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface PersonInfoInterface {
    List<PersonInfoDTO> getAllPersonInfo()throws Exception;
}
