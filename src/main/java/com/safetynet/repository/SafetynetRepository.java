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
public class SafetynetRepository implements SafetynetRepoInterface {

    final File file=new File("src/main/resources/data.json");

    private Safetynet safetynet;

    public SafetynetRepository(){
            this.safetynet=this.dataGetter();
    }
    @Override
    public Safetynet dataGetter() {
        ObjectMapper objectMapper=new ObjectMapper();
        Safetynet result=null;
        try {
            result = objectMapper.readValue(this.file, Safetynet.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
}