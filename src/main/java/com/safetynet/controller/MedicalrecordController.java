package com.safetynet.controller;

import com.safetynet.model.Medicalrecord;
import com.safetynet.service.MedicalrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MedicalrecordController {
    @Autowired
    MedicalrecordService medicalrecordService;

    @GetMapping("/medicalrecords")
    public Iterable<Medicalrecord>getMedicalrecords(){
        return medicalrecordService.getMedicalrecords();
    }
    @GetMapping("/medicalrecord")
    public Optional<Medicalrecord>getMedicalrecord(@RequestParam String firstname,@RequestParam String lastname){
        return medicalrecordService.getMedicalrecord(firstname,lastname);
    }

    @PostMapping("/medicalrecord")
    public boolean addMedicalrecord(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String birthdate,
            @RequestParam List<String> medications,
            @RequestParam List<String> allergies
    ){
        return medicalrecordService.addMedicalrecord(firstname,lastname,birthdate,medications,allergies);
    }

    @DeleteMapping("/medicalrecord")
    public boolean deleteMedicalrecord(@RequestParam String firstname, @RequestParam String lastname){
        return medicalrecordService.deleteMedicalrecord(firstname,lastname);
    }
    @PatchMapping("/medicalrecord")
    public Medicalrecord updateMedicalrecord(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String birthdate,
            @RequestParam List<String> medications,
            @RequestParam List<String> allergies
    ){
        return medicalrecordService.updateMedicalrecord(firstname,lastname,birthdate,medications,allergies);
    }
}
