package com.safetynet.service;

import com.safetynet.model.Firestation;
import com.safetynet.repository.FirestationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FirestationService implements FirestationSerInterface{
    @Autowired
    private FirestationRepository firestationRepository;
    public Optional<Firestation>getFirestation(String address){
        return Optional.ofNullable(this.firestationRepository.getFirestation(address));
    }
    public Iterable<Firestation>getFirestations(){
        return firestationRepository.getFirestations();
    }
    public boolean deleteFirestation(String address){
        return firestationRepository.deleteFirestation(address);
    }
    public boolean addFirestation(String address,int caserneID){
        return firestationRepository.addFirestation(address,caserneID);
    }
    public Firestation updateFirestation(String address, int caserneID){
        return firestationRepository.updateFirestation(address,caserneID);
    }
}
