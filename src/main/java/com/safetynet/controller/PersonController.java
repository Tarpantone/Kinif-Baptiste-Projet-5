package com.safetynet.controller;

import com.safetynet.model.Person;
import com.safetynet.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    private static final Logger personControllerLogger= LoggerFactory.getLogger(PersonController.class);
    @Autowired
    PersonService personService;

    @GetMapping("/api/person/all")
    public ResponseEntity<Iterable<Person>>getPersons(){
        List<Person> result= (List<Person>) personService.getPersons();
        if(result!=null){
            personControllerLogger.info("Request completed succesfully");
            return ResponseEntity.ok(result);
        }else{
            personControllerLogger.error("Impossible d'extraire la liste des personnes");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/api/person")
    public ResponseEntity<Optional<Person>>getPerson(@RequestParam String firstname, @RequestParam String lastname){
        Optional<Person> result= personService.getPerson(firstname,lastname);
        if (result!=null){
            personControllerLogger.info("Personne trouvée:"+result);
            return ResponseEntity.ok(result);
        }else{
            personControllerLogger.error("Impossible de trouver la personne demandée.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/api/person")
    public ResponseEntity<Boolean> deletePerson(@RequestParam String firstname,@RequestParam String lastname){
        Boolean result= personService.deletePerson(firstname,lastname);
        if(result=true){
            personControllerLogger.info("Personne supprimée");
            return ResponseEntity.ok(result);
        }else{
            personControllerLogger.error("Personne introuvable");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/api/person")
    public ResponseEntity<Boolean> addPerson(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam int zip,
            @RequestParam String phone,
            @RequestParam String email
    ){
        Boolean result= personService.addPerson(firstname,lastname,address,city,zip,phone,email);
        if (result=true){
            personControllerLogger.info("Personne ajoutée:");
            return ResponseEntity.ok(result);
        }else {
            personControllerLogger.error(("Impossible d'ajouter cette personne"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PatchMapping("/api/person")
    public ResponseEntity<Person> updatePerson(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam int zip,
            @RequestParam String phone,
            @RequestParam String email
    ){
        Person result= personService.updatePerson(firstname,lastname,address,city,zip,phone,email);
        if(result!=null){
            personControllerLogger.info("Personne mise à jour:"+result);
            return ResponseEntity.ok(result);
        }else {
            personControllerLogger.error("Personne pas trouvée");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
