package com.safetynet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*safetynet est le model de notre fichier json comprenant
une liste de person, une liste de medicalrecord et une liste de firestation*/
@Data
@NoArgsConstructor
public class Safetynet {
    private List <Person> persons;
    private List <Medicalrecord> medicalrecords;
    private List <Firestation> firestations;
}
