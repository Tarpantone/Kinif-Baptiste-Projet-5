package com.safetynet.repository;

import com.safetynet.main.SafetyNetApplication;
import com.safetynet.model.Safetynet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SafetyNetApplication.class)
public class JsonDataGetterTest {
    File file=new File("src/main/resources/data.json");
    JsonDataGetter fileDataGetter=new JsonDataGetter();
    @Test
    void contextLoads() {}

    @Test
    public void dataGetterTest (){
        fileDataGetter.dataGetter(file);
        Safetynet data=fileDataGetter.getSafetynet();
        assertTrue(data!=null);
    }
}
