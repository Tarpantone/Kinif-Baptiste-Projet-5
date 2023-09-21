package com.safetynet.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloodDTO {
    private int caserneID;
    private List<HouseholdDTO> households;
}
