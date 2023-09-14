package com.safetynet.repository;

import com.safetynet.model.Safetynet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JsonDataGetterTest {
    File file=new File("srv/main/resources/data.json");
    @Autowired
    JsonDataGetter fileDataGetter;
    @Test
    void contextLoads() {}

    @Test
    public void dataGetterTest (){
        fileDataGetter.dataGetter(file);
        Safetynet data=fileDataGetter.getSafetynet();

        assertTrue(data!=null);
    }
}
