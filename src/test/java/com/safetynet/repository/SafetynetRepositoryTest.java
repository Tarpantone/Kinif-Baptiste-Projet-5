package com.safetynet.repository;

import com.safetynet.main.SafetyNetApplication;
import com.safetynet.model.Safetynet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = SafetyNetApplication.class)
public class SafetynetRepositoryTest {
    SafetynetRepository safetynetRepository =new SafetynetRepository();
    @Test
    void contextLoads() {}

    @Test
    public void dataGetterTest (){
        safetynetRepository.dataGetter();
        Safetynet data= safetynetRepository.getSafetynet();
        assertNotNull(data);
    }
}
