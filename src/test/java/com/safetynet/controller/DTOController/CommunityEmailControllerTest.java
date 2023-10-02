package com.safetynet.controller.DTOController;

import com.safetynet.SafetyNetApplication;
import com.safetynet.service.DTOService.CommunityEmailService;
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
class CommunityEmailControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CommunityEmailService communityEmailService;

    @Test
    void getCommunityEmail() throws Exception {
        mockMvc.perform(get("http://localhost:8080/communityEmail?city=Culver"))
                .andExpect(status().isOk());
    }
}