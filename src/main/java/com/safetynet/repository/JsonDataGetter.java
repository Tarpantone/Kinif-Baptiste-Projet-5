package com.safetynet.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.model.Safetynet;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository

public class JsonDataGetter implements FileDataGetter{

    private Safetynet safetynet;

    public JsonDataGetter(){}

    @Override
    public void dataGetter(File file) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            this.safetynet=objectMapper.readValue(file, Safetynet.class);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Safetynet getSafetynet(){
        return this.safetynet;
    }
}
