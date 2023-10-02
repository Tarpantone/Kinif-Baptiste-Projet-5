package com.safetynet.controller.DTOController;

import com.safetynet.model.DTO.PersonInfoDTO;
import com.safetynet.service.DTOService.PersonInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonInfoController {
    private static final Logger personInfoControllerLogger= LoggerFactory.getLogger("personInfo");
    @Autowired
    PersonInfoService personInfoService;
    @GetMapping("/personInfo")
    public ResponseEntity<List<PersonInfoDTO>> getAllPersonInfo()throws Exception{
        List<PersonInfoDTO>result=personInfoService.getAllPersonInfo();
        if(result!=null){
            personInfoControllerLogger.info("Voici la liste des infos de toutes les personnes");
            return ResponseEntity.ok(result);
        }else{
            personInfoControllerLogger.error("Impossible d'obtenir la liste des infos de toutes les personnes.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
