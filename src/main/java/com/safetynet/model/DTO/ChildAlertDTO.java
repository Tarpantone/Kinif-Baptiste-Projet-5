package com.safetynet.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildAlertDTO {
    private List<ChildDTO>childs;
    private List<AdultDTO> adults;
}
