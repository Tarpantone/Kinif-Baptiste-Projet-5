package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.ChildDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface ChildInterface {
    List<ChildDTO> getChilds(List<Person> persons, List<Medicalrecord>medicalrecords, String address) throws Exception;
}
