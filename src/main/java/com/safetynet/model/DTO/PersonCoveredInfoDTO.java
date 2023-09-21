package com.safetynet.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonCoveredInfoDTO {
    private String firstname;
    private String lastname;
    private String address;
    private String phoneNumber;
}
