package com.safetynet.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfoDTO {
    private String lastname;
    private String address;
    int age;
    private String email;
    private List<String>medications;
    private List<String>allergies;
}
