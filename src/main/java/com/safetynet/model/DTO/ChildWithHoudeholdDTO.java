package com.safetynet.model.DTO;

import com.safetynet.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildWithHoudeholdDTO {
    private String firstname;
    private String lastname;
    private int age;
    private List<Person> houseHold;
}
