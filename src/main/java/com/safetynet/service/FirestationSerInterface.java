package com.safetynet.service;

import com.safetynet.model.Firestation;

import java.util.Optional;

public interface FirestationSerInterface {
    Optional<Firestation> getFirestation(String address);
    Iterable<Firestation>getFirestations();
    boolean deleteFirestation(String address);
    boolean addFirestation(String address,int caserneID);
    Firestation updateFirestation(String address, int caserneID);
}
