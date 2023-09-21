package com.safetynet.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdWithFirestationDTO {
    private String lastname;
    private String phoneNumber;
    private int age;
    private List<String>medications;
    private List<String>allergies;
    private int caserneID;

}
