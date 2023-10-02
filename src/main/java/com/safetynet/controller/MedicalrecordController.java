package com.safetynet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.safetynet.model.Medicalrecord;
import com.safetynet.service.MedicalrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class MedicalrecordController {
    private static final Logger medicalrecordControllerlogger =LoggerFactory.getLogger(MedicalrecordController.class);
    @Autowired
    MedicalrecordService medicalrecordService;

    public MedicalrecordController (MedicalrecordService medicalrecordService){
        this.medicalrecordService=medicalrecordService;
    }

    @GetMapping("api/medicalrecord/all")
    public ResponseEntity<Iterable<Medicalrecord>>getMedicalrecords(){
        List<Medicalrecord>result= (List<Medicalrecord>) medicalrecordService.getMedicalrecords();
        if(result!=null){
            medicalrecordControllerlogger.info("Request completed succesfully");
            return ResponseEntity.ok(result);
        }else{
            medicalrecordControllerlogger.error("Impossible d'extraire la liste des medicalrecords");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("api/medicalrecord")
    public ResponseEntity<Optional<Medicalrecord>>getMedicalrecord(@RequestParam String firstname,@RequestParam String lastname){
        Optional<Medicalrecord> result=medicalrecordService.getMedicalrecord(firstname,lastname);
        if (result!=null){
            medicalrecordControllerlogger.info("Medicalrecord trouvé:"+result);
            return ResponseEntity.ok(result);
        }else{
            medicalrecordControllerlogger.error("Impossible de trouver le Medicalrecord demandé.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("api/medicalrecord")
    public ResponseEntity<Boolean> addMedicalrecord(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String birthdate,
            @RequestParam List<String> medications,
            @RequestParam List<String> allergies
    ){
        Boolean result = medicalrecordService.addMedicalrecord(firstname,lastname,birthdate,medications,allergies);

        if (result=true){
            medicalrecordControllerlogger.info("Medicalrecord ajouté:");
            return ResponseEntity.ok(result);
        }else {
            medicalrecordControllerlogger.error(("Impossible d'ajouter ce medicalrecord"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("api/medicalrecord")
    public ResponseEntity<Boolean> deleteMedicalrecord(@RequestParam String firstname, @RequestParam String lastname){
        Boolean result= medicalrecordService.deleteMedicalrecord(firstname,lastname);
        if(result=true){
            medicalrecordControllerlogger.info("Medicalreord supprimé");
            return ResponseEntity.ok(result);
        }else{
            medicalrecordControllerlogger.error("Medicalrecord introuvable");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PatchMapping("api/medicalrecord")
    public ResponseEntity<Medicalrecord> updateMedicalrecord(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String birthdate,
            @RequestParam List<String> medications,
            @RequestParam List<String> allergies
    ){
        Medicalrecord result= medicalrecordService.updateMedicalrecord(firstname,lastname,birthdate,medications,allergies);
        if(result!=null){
            medicalrecordControllerlogger.info("Medicalrecord mis à jour:"+result);
            return ResponseEntity.ok(result);
        }else {
            medicalrecordControllerlogger.error("Medicalrecord pas trouvé");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
