package com.safetynet.controller.DTOController;

import com.safetynet.controller.PersonController;
import com.safetynet.model.DTO.CommunityEmailDTO;
import com.safetynet.service.DTOService.CommunityEmailService;
import com.safetynet.service.PersonService;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class CommunityEmailController {
    private static final Logger communityEmailLogger= LoggerFactory.getLogger("communityEmail");

    @Autowired
    CommunityEmailService communityEmailService;
    @Autowired
    PersonService personService;

    @GetMapping("/communityEmail")
    public ResponseEntity<CommunityEmailDTO> getCommunityEmail(@RequestParam@NotBlank String city){
        CommunityEmailDTO result= communityEmailService.getCommunityEmails(city);
        if (result.getEmails().size()>0){
            communityEmailLogger.info("Voici la liste des emails");
            return ResponseEntity.ok(result);
        }else{
            communityEmailLogger.error("Impossible d'obtenir la liste des emails");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
