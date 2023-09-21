package com.safetynet.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdMemberDTO {
    String lastname;
    String phoneNumber;
    int age;
    List<String>medications;
    List<String>allergies;
}
