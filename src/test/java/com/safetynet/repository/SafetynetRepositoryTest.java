package com.safetynet.repository;

import com.safetynet.main.SafetyNetApplication;
import com.safetynet.model.Safetynet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = SafetyNetApplication.class)
public class JsonDataGetterTest {
    JsonDataGetter jsonDataGetter=new JsonDataGetter();
    @Test
    void contextLoads() {}

    @Test
    public void dataGetterTest (){
        jsonDataGetter.setFile(new File("src/main/resources/data.json"));
        jsonDataGetter.dataGetter();
        Safetynet data=jsonDataGetter.getSafetynet();
        assertNotNull(data);
    }
}
