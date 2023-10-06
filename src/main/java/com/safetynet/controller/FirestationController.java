package com.safetynet.controller;

import com.safetynet.model.Firestation;
import com.safetynet.service.FirestationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class FirestationController {
    private static final Logger firestationControllerLogger= LoggerFactory.getLogger(FirestationController.class);
    @Autowired
    FirestationService firestationService;

    @GetMapping
    @RequestMapping("/api/firestation/all")
    public ResponseEntity<Iterable<Firestation>>getFirestations(){
        List<Firestation>result= (List<Firestation>) firestationService.getFirestations();
        if(result.size()>0){
            firestationControllerLogger.info("Request completed succesfully");
            return ResponseEntity.ok(result);
        }else{
            firestationControllerLogger.error("Impossible d'extraire la liste des firestations");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/api/firestation")
    public ResponseEntity<Optional<Firestation>>getFirestation(@RequestParam String address){
        Optional<Firestation>result= firestationService.getFirestation(address);
        if (result.get().getAddress().equals(address)){
            firestationControllerLogger.info("Firestation trouvée:"+result);
            return ResponseEntity.ok(result);
        }else{
            firestationControllerLogger.error("Impossible de trouver la firestation demandée.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/api/firestation")
    public ResponseEntity<Boolean> deleteFirestation(@RequestParam String address){
        Boolean result= firestationService.deleteFirestation(address);
        if(result==true){
            firestationControllerLogger.info("Firestation supprimée");
            return ResponseEntity.ok(result);
        }else{
            firestationControllerLogger.error("Firestation introuvable");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/api/firestation")
    public ResponseEntity<Boolean> addFirestation(@RequestParam String address,@RequestParam int caserneID){
        Boolean result= firestationService.addFirestation(address,caserneID);
        if (result==true){
            firestationControllerLogger.info("Firestation ajouté:");
            return ResponseEntity.ok(result);
        }else {
            firestationControllerLogger.error(("Impossible d'ajouter cette firestation"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PatchMapping("/api/firestation")
    public ResponseEntity<Firestation> updateFirstation(@RequestParam String address,@RequestParam int caserneID){
       Firestation result= firestationService.updateFirestation(address,caserneID);
        if(result.getAddress().equals(address)){
            firestationControllerLogger.info("Firestation mise à jour:"+result);
            return ResponseEntity.ok(result);
        }else {
            firestationControllerLogger.error("Firestation pas trouvée");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
