package com.safetynet.service;

import com.safetynet.model.Firestation;
import com.safetynet.repository.FirestationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class FirestationService implements FirestationSerInterface{
    private static final Logger firestationServiceLogger= LoggerFactory.getLogger(FirestationService.class);
    @Autowired
    private FirestationRepository firestationRepository;
    public Optional<Firestation>getFirestation(String address){
        firestationServiceLogger.debug("getFirestation");
        return Optional.ofNullable(this.firestationRepository.getFirestation(address));
    }
    public Iterable<Firestation>getFirestations(){
        firestationServiceLogger.debug("getFirestations");
        return firestationRepository.getFirestations();
    }
    public boolean deleteFirestation(String address){
        firestationServiceLogger.debug("deleteFirestation");
        return firestationRepository.deleteFirestation(address);
    }
    public boolean addFirestation(String address,int caserneID){
        firestationServiceLogger.debug("addFirestation");
        return firestationRepository.addFirestation(address,caserneID);
    }
    public Firestation updateFirestation(String address, int caserneID){
        firestationServiceLogger.debug("updateFirestation");
        return firestationRepository.updateFirestation(address,caserneID);
    }
}
