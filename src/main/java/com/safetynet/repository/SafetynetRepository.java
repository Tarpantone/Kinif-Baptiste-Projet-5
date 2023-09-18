package com.safetynet.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.model.Safetynet;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
@Data
public class SafetynetRepository implements FileDataGetter{

    private File file;
    @Autowired
    private Safetynet safetynet;

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