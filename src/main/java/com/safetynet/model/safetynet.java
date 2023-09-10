package com.safetynet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class safetynet {
    private List <Person> persons;
    private List <medicalrecord> medicalrecords;
    private List <firestation> firestations;
}
