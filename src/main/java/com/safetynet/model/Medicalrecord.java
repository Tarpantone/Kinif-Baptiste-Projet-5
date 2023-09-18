package com.safetynet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicalrecord {
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    public void setUpdate(String birthdate, List<String>medications,List<String>allergies){
        this.birthdate=birthdate;
        this.medications=medications;
        this.allergies=allergies;
    }
}
