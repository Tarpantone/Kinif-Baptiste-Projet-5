package com.safetynet.service;

import com.safetynet.model.DTO.ChildDTO;
import com.safetynet.model.DTO.ChildAlertDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface ChildAlertInetrface {
    public List<ChildDTO> getChilds(List<Person> persons, List<Medicalrecord>medicalrecords, String address)throws Exception;
    public List<Person> getHousehold(List<Person> persons, List<Medicalrecord>medicalrecords, String address)throws Exception;
    public ChildAlertDTO getChildWithHousehold(List<ChildDTO>childs, List<Person>household)throws Exception;
}
