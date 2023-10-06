package com.safetynet.controller.DTOController;

import com.safetynet.model.DTO.ChildAlertDTO;

import com.safetynet.model.DTO.PhoneAlertDTO;
import com.safetynet.service.DTOService.ChildAlertService;
import com.safetynet.service.DTOService.PhoneAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlertController {
    private static final Logger alerControllerLogger= LoggerFactory.getLogger("alert");

    @Autowired
    ChildAlertService childAlertService;
    @Autowired
    PhoneAlertService phoneAlertService;

   @GetMapping("/childAlert")
    public ResponseEntity<ChildAlertDTO>getChildWithHousehold(@RequestParam String address)throws Exception{
        ChildAlertDTO result=childAlertService.getChildWithHousehold(address);
       System.out.println(result);
        if(result.getChilds().size()>0|result.getAdults().size()>0){
            alerControllerLogger.info("Voici la liste des enfants habitant à l'adresse="+address);
            return ResponseEntity.ok(result);
        }else {
            alerControllerLogger.error("Impossible d'obtenir la liste des enfants habitant à l'adresse="+address);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<PhoneAlertDTO> getListedPhoneNumberCoveredByStation(@RequestParam int caserneID){
       PhoneAlertDTO result=phoneAlertService.getListedPhoneNumberCoveredByStation(caserneID);
       if (result.getPhoneNumbers().size()>0){
            alerControllerLogger.info("Voici la liste des numéros de téléphonnes couverte par la station:"+caserneID);
            return ResponseEntity.ok(result);
       }else{
           alerControllerLogger.error("Impossible d'obtenir la liste des numéros de téléphonnes couverte par la station:"+caserneID);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }
}
