package com.safetynet.controller.DTOController;

import com.safetynet.SafetyNetApplication;
import com.safetynet.service.DTOService.ChildAlertService;
import com.safetynet.service.DTOService.PhoneAlertService;
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
class AlertControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PhoneAlertService phoneAlertService;
    @MockBean
    ChildAlertService childAlertService;
    @Test
    void getChildWithHousehold() throws Exception {
        mockMvc.perform(get("http://localhost:8080/childAlert?address=1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    void getListedPhoneNumberCoveredByStation() throws Exception {
        mockMvc.perform(get("http://localhost:8080/phoneAlert?caserneID=1"))
                .andExpect(status().isOk());
    }
}