package com.safetynet.repository;

import com.safetynet.model.Firestation;
import com.safetynet.model.Medicalrecord;

import java.util.List;

public interface FirestationRepoInterface {
    public List <Firestation> getFirestationsFromSafetynet();
    public boolean deleteFirestation(String address);
    public boolean addFirestation(String address,int caserneID);
    public void updateFirestation(String address,int caserneID);
    Firestation getFirestation(String address,int caserneID);
}
