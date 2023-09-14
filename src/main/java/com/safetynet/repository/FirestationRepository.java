package com.safetynet.repository;

import com.safetynet.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class FirestationRepository {
    HashMap <String,Integer> firestations;
    @Autowired
    JsonDataGetter jsonDataGetter;

    public HashMap <String,Integer> getFirestationsFromSafetynet(){
        jsonDataGetter.dataGetter();
        List<Firestation> firestationList=jsonDataGetter.getSafetynet().getFirestations();
        HashMap<String,Integer>result=new HashMap<String,Integer>();
        for (Firestation fs:firestationList){
            result.put(fs.getAddress(),fs.getStation());
        }
        return  result;
    }

    public int deleteFirestation(String address){
        return firestations.remove(address);
    }

    public int addFirestation(String address,int caserneID){
        return firestations.put(address,caserneID);
    }

    public int updateFirestation(String address,int caserneID){
        return firestations.put(address,caserneID);
    }
}
