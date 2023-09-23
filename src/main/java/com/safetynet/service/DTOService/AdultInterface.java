package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.AdultDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface AdultInterface {
    public List<AdultDTO> getAdultsAtThisAddress(List<Person> persons, List<Medicalrecord>medicalrecords, String address)throws Exception;
}
