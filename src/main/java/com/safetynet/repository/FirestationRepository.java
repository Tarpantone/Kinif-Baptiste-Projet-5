package com.safetynet.repository;

import com.safetynet.model.Firestation;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
public class FirestationRepository {
    List<Firestation> firestations;
    JsonDataGetter jsonDataGetter;

    public List <Firestation> getFirestationsFromSafetynet(){
        jsonDataGetter.dataGetter();
        return jsonDataGetter.getSafetynet().getFirestations();
    }
}
