package com.safetynet.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirestationWithCoveredHouseholdsDTO {
    private List<HouseholdWithFirestationDTO> households;
    private int caserneID;
}
