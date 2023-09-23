package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.AdultDTO;
import com.safetynet.model.DTO.ChildDTO;
import com.safetynet.model.DTO.ChildAlertDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ChildAlertService implements ChildAlertInetrface {
    @Autowired
    private ChildService childService;
    @Autowired
    private AdultService adultService;
    public ChildAlertDTO getChildWithHousehold(List<Person> persons, List<Medicalrecord>medicalrecords, String address)throws Exception{
        ChildAlertDTO result=null;

        List<ChildDTO> childDTOS=this.childService.getChildsAtThisAddress(persons,medicalrecords,address);

        if(childDTOS.isEmpty()){
            return result;
        }else{
            List<AdultDTO>adultDTOS=this.adultService.getAdultsAtThisAddress(persons,medicalrecords,address);
            result=new ChildAlertDTO(childDTOS,adultDTOS);
            return result;
        }
    }
}
