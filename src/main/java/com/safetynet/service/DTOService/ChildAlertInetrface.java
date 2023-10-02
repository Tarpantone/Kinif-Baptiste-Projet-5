package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.ChildDTO;
import com.safetynet.model.DTO.ChildAlertDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;

import java.util.List;

public interface ChildAlertInetrface {
    public ChildAlertDTO getChildWithHousehold(String address)throws Exception;
}
