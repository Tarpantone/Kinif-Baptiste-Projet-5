package com.safetynet.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class sMedicalrecord {
    private String firstName;
    private String lastName;
    @Size(min=10,max=10)
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;
}
