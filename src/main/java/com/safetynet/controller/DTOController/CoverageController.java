package com.safetynet.controller.DTOController;

import com.safetynet.model.DTO.FireDTO;
import com.safetynet.model.DTO.FloodDTO;
import com.safetynet.model.DTO.PersonsCoveredByAFirestationDTO;
import com.safetynet.service.DTOService.FireService;
import com.safetynet.service.DTOService.FloodService;
import com.safetynet.service.DTOService.PersonsCoveredByAFirestationService;
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
public class CoverageController {
    private static final Logger coverageControllerLogger= LoggerFactory.getLogger("coverage");
    @Autowired
    PersonsCoveredByAFirestationService personsCoveredByAFirestationService;
    @Autowired
    FireService fireService;
    @Autowired
    FloodService floodService;
    @GetMapping("/firestation")
    public ResponseEntity<PersonsCoveredByAFirestationDTO> getPersonsCoveredByAFirestation(@RequestParam int caserneID) throws Exception {
        PersonsCoveredByAFirestationDTO result=personsCoveredByAFirestationService.getPersonsCoveredByAFirestation(caserneID);
        if (result!=null){
            coverageControllerLogger.info("Voici la liste des personnes couvertes par la station:"+caserneID);
            return ResponseEntity.ok(result);
        }else{
            coverageControllerLogger.error(("Impossible de générer la liste des Personnes couvertes par la station:"+caserneID));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/fire")
    public ResponseEntity<FireDTO> getHouseholdAndItsFirestation(@RequestParam String address) throws Exception{
        FireDTO result=fireService.getHouseholdAndItsFirestation(address);
        if (result!=null){
            coverageControllerLogger.info("Voici la liste des personnes vivant à:"+address+"ainsi que la caserne qui les couvre.");
            return ResponseEntity.ok(result);
        }else{
            coverageControllerLogger.error("Impossible d'obtenir la liste des personnes vivant à:"+address+"ainsi que la caserne qui les couvre.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/flood/station")
    public ResponseEntity<FloodDTO> getAllHouseholdCoveredByAFirestation(@RequestParam int caserneID)throws Exception{
        FloodDTO result=floodService.getAllHouseholdCoveredByAFirestation(caserneID);
        if (result!=null){
            coverageControllerLogger.info("Voici la liste de tous les foyers déservis par la station:"+caserneID);
            return ResponseEntity.ok(result);
        }else {
            coverageControllerLogger.error("Impossible d'obtenir la liste de tous les foyers déservis par la station:"+caserneID);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
