package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.AdultDTO;
import com.safetynet.model.DTO.ChildDTO;
import com.safetynet.model.DTO.ChildAlertDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import com.safetynet.service.MedicalrecordService;
import com.safetynet.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ChildAlertService implements ChildAlertInetrface {
    private static final Logger childAlertServiceLogger= LoggerFactory.getLogger(ChildAlertService.class);
    @Autowired
    private ChildService childService;
    @Autowired
    private AdultService adultService;
    @Autowired
    PersonService personService;
    @Autowired
    MedicalrecordService medicalrecordService;
    public ChildAlertDTO getChildWithHousehold(String address)throws Exception{
        childAlertServiceLogger.debug("getChildWithHousehold");
        ChildAlertDTO result=null;
        List<Person>persons= (List<Person>) personService.getPersons();
        List<Medicalrecord>medicalrecords= (List<Medicalrecord>) medicalrecordService.getMedicalrecords();
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
