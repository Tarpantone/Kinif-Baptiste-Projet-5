package com.safetynet.controller;

import com.safetynet.model.Firestation;
import com.safetynet.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FirestationController {
    @Autowired
    FirestationService firestationService;

    @GetMapping("/firestations")
    public Iterable<Firestation>getFirestations(){
        return firestationService.getFirestations();
    }
    @GetMapping(value="/Firestation/{address}")
    public Optional<Firestation>getFirestation(@PathVariable String address){
        return firestationService.getFirestation(address);
    }
    @DeleteMapping(value="firestation/{address}")
    public boolean deleteFirestation(@PathVariable String address){
        return firestationService.deleteFirestation(address);
    }

    @PostMapping("/firestation")
    public boolean addFirestation(@RequestParam String address,@RequestParam int caserneID){
        return firestationService.addFirestation(address,caserneID);
    }
    @PatchMapping("/firstation")
    public Firestation updateFirstation(@RequestParam String address,@RequestParam int caserneID){
        return firestationService.updateFirestation(address,caserneID);
    }

}
