package com.safetynet.controller.DTOController;

import com.safetynet.SafetyNetApplication;
import com.safetynet.model.DTO.*;
import com.safetynet.service.DTOService.FireService;
import com.safetynet.service.DTOService.FloodService;
import com.safetynet.service.DTOService.PersonsCoveredByAFirestationService;
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
class CoverageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    FloodService floodService;
    @MockBean
    PersonsCoveredByAFirestationService personsCoveredByAFirestationService;
    @MockBean
    FireService fireService;

    private FloodDTO floodDTO=new FloodDTO(1,new ArrayList<>());
    private FireDTO fireDTO=new FireDTO(1,new HouseholdDTO("address",new ArrayList<>()));
    private PersonsCoveredByAFirestationDTO personsCoveredByAFirestationDTO=new PersonsCoveredByAFirestationDTO(1,new ArrayList<>(),2,3);

    @Test
    void getPersonsCoveredByAFirestationWhereStatusIsOk() throws Exception{
        personsCoveredByAFirestationDTO.getPersonsCovered().add(new PersonCoveredInfoDTO());
        when(personsCoveredByAFirestationService.getPersonsCoveredByAFirestation(anyInt())).thenReturn(personsCoveredByAFirestationDTO);
        mockMvc.perform(get("http://localhost:8080/firestation?caserneID=1"))
                .andExpect(status().isOk());
    }
    @Test
    void getPersonsCoveredByAFirestationWhereStatusIsNOT_FOUND() throws Exception{
        when(personsCoveredByAFirestationService.getPersonsCoveredByAFirestation(anyInt())).thenReturn(new PersonsCoveredByAFirestationDTO(1,new ArrayList<>(),1,1));
        mockMvc.perform(get("http://localhost:8080/firestation?caserneID=1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getHouseholdAndItsFirestationWhereStatusIsOK() throws Exception{
        fireDTO.getHousehold().getHouseholdMembers().add(new HouseholdMemberDTO());
        when(fireService.getHouseholdAndItsFirestation(anyString())).thenReturn(fireDTO);
        mockMvc.perform(get("http://localhost:8080/fire?address=1509 Culver St"))
                .andExpect(status().isOk());
    }
    @Test
    void getHouseholdAndItsFirestationWhereStatusIsNOT_FOUND() throws Exception{
        when(fireService.getHouseholdAndItsFirestation(anyString())).thenReturn(new FireDTO(1,new HouseholdDTO("address",new ArrayList<>())));
        mockMvc.perform(get("http://localhost:8080/fire?address=1509 Culver St"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllHouseholdCoveredByAFirestationWhereStatusIsOK() throws Exception{
        floodDTO.getHouseholds().add(new HouseholdDTO());
        when(floodService.getAllHouseholdCoveredByAFirestation(anyInt())).thenReturn(floodDTO);
        mockMvc.perform(get("http://localhost:8080/flood/station?caserneID=1"))
                .andExpect(status().isOk());
    }
    @Test
    void getAllHouseholdCoveredByAFirestationWhereStatusIsNOT_FOUND() throws Exception{
        when(floodService.getAllHouseholdCoveredByAFirestation(anyInt())).thenReturn(new FloodDTO(1,new ArrayList<>()));
        mockMvc.perform(get("http://localhost:8080/flood/station?caserneID=1"))
                .andExpect(status().isNotFound());
    }
}