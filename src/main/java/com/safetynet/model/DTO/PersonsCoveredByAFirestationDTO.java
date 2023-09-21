package com.safetynet.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonsCoveredByAFirestationDTO {
    int caserneID;
    List<PersonCoveredInfoDTO>personsCovered;
    int childCount;
    int adultCount;
}
