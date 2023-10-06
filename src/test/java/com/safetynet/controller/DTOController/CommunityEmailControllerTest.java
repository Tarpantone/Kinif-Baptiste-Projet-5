package com.safetynet.controller.DTOController;

import com.safetynet.SafetyNetApplication;
import com.safetynet.model.DTO.CommunityEmailDTO;
import com.safetynet.service.DTOService.CommunityEmailService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SafetyNetApplication.class)
@AutoConfigureMockMvc
class CommunityEmailControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CommunityEmailService communityEmailService;
    private static CommunityEmailDTO communityEmailDTO=new CommunityEmailDTO(new ArrayList<>());
    @BeforeAll
    static void setUp(){
        communityEmailDTO.getEmails().add("email");
    }

    @Test
    void getCommunityEmailWhereStatusIsOk() throws Exception {
        when(communityEmailService.getCommunityEmails(anyString())).thenReturn(communityEmailDTO);
        mockMvc.perform(get("http://localhost:8080/communityEmail?city=Culver"))
                .andExpect(status().isOk());
    }
    @Test
    void getCommunityEmailWhereStatusIsINTERNAL_SERVER_ERROR()throws  Exception{
        when(communityEmailService.getCommunityEmails(anyString())).thenReturn(new CommunityEmailDTO(new ArrayList<>()));
        mockMvc.perform(get("http://localhost:8080/communityEmail?city=Culver"))
                .andExpect(status().isInternalServerError());
    }
}