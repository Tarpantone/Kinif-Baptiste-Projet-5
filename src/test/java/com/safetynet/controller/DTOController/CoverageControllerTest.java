package com.safetynet.controller.DTOController;

import com.safetynet.SafetyNetApplication;
import com.safetynet.service.DTOService.FireService;
import com.safetynet.service.DTOService.FloodService;
import com.safetynet.service.DTOService.PersonsCoveredByAFirestationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    void getPersonsCoveredByAFirestation() throws Exception{
        mockMvc.perform(get("http://localhost:8080/firestation?caserneID=1"))
                .andExpect(status().isOk());
    }

    @Test
    void getHouseholdAndItsFirestation() throws Exception{
        mockMvc.perform(get("http://localhost:8080/fire?address=1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllHouseholdCoveredByAFirestation() throws Exception{
        mockMvc.perform(get("http://localhost:8080/flood/station?caserneID=1"))
                .andExpect(status().isOk());
    }
}