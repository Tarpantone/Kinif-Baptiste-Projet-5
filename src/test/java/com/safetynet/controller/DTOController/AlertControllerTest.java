package com.safetynet.controller.DTOController;

import com.safetynet.SafetyNetApplication;
import com.safetynet.model.DTO.AdultDTO;
import com.safetynet.model.DTO.ChildAlertDTO;
import com.safetynet.model.DTO.ChildDTO;
import com.safetynet.model.DTO.PhoneAlertDTO;
import com.safetynet.service.DTOService.ChildAlertService;
import com.safetynet.service.DTOService.PhoneAlertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(classes = SafetyNetApplication.class)
@AutoConfigureMockMvc
class AlertControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PhoneAlertService phoneAlertService;
    @MockBean
    ChildAlertService childAlertService;

    private ChildAlertDTO childAlertDTO=new ChildAlertDTO(new ArrayList<>(),new ArrayList<>());
    private PhoneAlertDTO phoneAlertDTO=new PhoneAlertDTO(2,new ArrayList<>());
    @Test
    void getChildWithHouseholdWhereStatusIsOk() throws Exception {
        childAlertDTO.getChilds().add(new ChildDTO());
        childAlertDTO.getAdults().add(new AdultDTO());
        when(childAlertService.getChildWithHousehold(anyString())).thenReturn(childAlertDTO);
        mockMvc.perform(get("http://localhost:8080/childAlert?address=1509 Culver St"))
                .andExpect(status().isOk());
    }
    @Test
    void getChildWithHouseholdWhereStatusIsNOT_FOUND()throws Exception{
        when(childAlertService.getChildWithHousehold(anyString())).thenReturn(new ChildAlertDTO(new ArrayList<>(),new ArrayList<>()));
        mockMvc.perform(get("http://localhost:8080/childAlert?address=1509 Culver St"))
                .andExpect(status().isNotFound());
    }
    @Test
    void getListedPhoneNumberCoveredByStationWhereStatusIsOk() throws Exception {
        phoneAlertDTO.getPhoneNumbers().add("boopboop");
        when(phoneAlertService.getListedPhoneNumberCoveredByStation(anyInt())).thenReturn(phoneAlertDTO);
        mockMvc.perform(get("http://localhost:8080/phoneAlert?caserneID=1"))
                .andExpect(status().isOk());
    }
    @Test
    void getListedPhoneNumberCoveredByStationWhereStatusIsNOT_FOUND() throws Exception {
        when(phoneAlertService.getListedPhoneNumberCoveredByStation(anyInt())).thenReturn(new PhoneAlertDTO(1,new ArrayList<>()));
        mockMvc.perform(get("http://localhost:8080/phoneAlert?caserneID=1"))
                .andExpect(status().isNotFound());
    }
}