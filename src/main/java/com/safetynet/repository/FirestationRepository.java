package com.safetynet.repository;

import com.safetynet.model.Firestation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationRepository {
    List<Firestation> firestations;
    JsonDataGetter jsonDataGetter;

    public List <Firestation> getFirestationsFromSafetynet(){
        jsonDataGetter.dataGetter();
        return jsonDataGetter.getSafetynet().getFirestations();
    }

    public List <Firestation> getAllFirestation(){
        return this.firestations;
    }

    public boolean deleteFirestation(String address, int caserneID){
        return firestations.remove(new Firestation(address,caserneID));
    }

    public boolean addFirestation(String address,int caserneID){
        return firestations.add(new Firestation(address,caserneID));
    }

    /*public boolean updateFirestation(String address,int caserneID){
        return ()
    }*/
}
