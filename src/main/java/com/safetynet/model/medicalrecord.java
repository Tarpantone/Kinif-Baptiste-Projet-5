package com.safetynet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class medicalrecord {
    private String firstName;
    private String lastName;
    private Date birthdate;
    private List<String> medications;
    private List<String> allergies;
}
