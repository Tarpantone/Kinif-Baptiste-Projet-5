package com.safetynet.model.DTO;

import com.safetynet.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildAlertDTO {
    private List<ChildDTO>childs;
    private List<Person> houseHold;
}
