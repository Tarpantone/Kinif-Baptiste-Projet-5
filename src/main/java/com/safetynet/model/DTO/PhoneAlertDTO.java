package com.safetynet.model.DTO;

import com.safetynet.model.Firestation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneAlertDTO {
    private int caserneID;
    private List <String> phoneNumbers;
}
