package com.safetynet.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.model.Safetynet;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
@Data
public class JsonDataGetter implements FileDataGetter{

    final File file=new File("src/main/resources/data.json");
    Safetynet safetynet;

    @Override
    public void dataGetter() {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            this.safetynet=objectMapper.readValue(this.file, Safetynet.class);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}