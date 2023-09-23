package com.safetynet.service.DTOService;

import com.safetynet.model.DTO.AdultDTO;
import com.safetynet.model.DTO.ChildAlertDTO;
import com.safetynet.model.DTO.ChildDTO;
import com.safetynet.model.Medicalrecord;
import com.safetynet.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChildAlertServiceTest {
    @Mock
    ChildService childService;
    @Mock
    AdultService adultService;

    @Autowired @InjectMocks
    ChildAlertService childAlertService;

    private static List<Person>persons;
    private static List<Medicalrecord>medicalrecords;
    private static String address;
    @BeforeAll
    public static void setUp(){
        persons=new ArrayList<>();
        medicalrecords=new ArrayList<>();
        address="address";
    }

    @Test
    public void getChildWithHouseholdWhereThereisChild() throws Exception {
        ChildDTO childDTO=new ChildDTO("A","B",3);
        List<ChildDTO>exist=new ArrayList<>();
        exist.add(childDTO);
        List<AdultDTO>adultDTOS=new ArrayList<>();
        when(childService.getChildsAtThisAddress(persons,medicalrecords,address)).thenReturn(exist);
        when(adultService.getAdultsAtThisAddress(persons,medicalrecords,address)).thenReturn(adultDTOS);
        ChildAlertDTO result=childAlertService.getChildWithHousehold(persons,medicalrecords,address);
        assertNotNull(result);
    }
    @Test
    public void getChildWithHouseholdWhereThereisNoChild() throws Exception {
        List<ChildDTO>exist=new ArrayList<>();
        when(childService.getChildsAtThisAddress(persons,medicalrecords,address)).thenReturn(exist);
        ChildAlertDTO result=childAlertService.getChildWithHousehold(persons,medicalrecords,address);
        assertNull(result);
    }
}
