package com.safetynet.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdDTO {
    private String address;
    List<HouseholdMemberDTO>householdMembers;
}
