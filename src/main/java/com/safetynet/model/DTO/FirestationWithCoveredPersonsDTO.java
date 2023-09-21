package com.safetynet.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirestationWithCoveredPersonsDTO {
    private int caserneID;
    private String firstname;
    private String lastname;
    private String address;
    private String phoneNumber;
    private int adultCount;
    private int childCount;
}
