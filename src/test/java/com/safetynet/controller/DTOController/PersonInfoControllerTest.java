package com.safetynet.controller.DTOController;

import com.safetynet.SafetyNetApplication;
import com.safetynet.model.DTO.PersonInfoDTO;
import com.safetynet.service.DTOService.PersonInfoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(classes = SafetyNetApplication.class)
@AutoConfigureMockMvc
class PersonInfoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PersonInfoService personInfoService;
    private static List<PersonInfoDTO>personInfoDTOS=new ArrayList<>();
    @BeforeAll
    static void setUp(){
        personInfoDTOS.add(new PersonInfoDTO());
    }
    @Test
    void getAllPersonInfoWhereStatusIsOk() throws Exception{
        when(personInfoService.getAllPersonInfo()).thenReturn(personInfoDTOS);
        mockMvc.perform(get("http://localhost:8080/personInfo"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPersonInfoWhereStatusIsINTERNAL_SERVER_ERROR()throws Exception{
        mockMvc.perform(get("http://localhost:8080/personInfo"))
                .andExpect(status().isInternalServerError());
    }
}