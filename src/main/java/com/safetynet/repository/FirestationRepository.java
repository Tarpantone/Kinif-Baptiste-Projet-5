package com.safetynet.repository;

import com.safetynet.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class FirestationRepository implements FirestationRepoInterface{
    List <Firestation> firestations;
    @Autowired
    SafetynetRepository safetynetRepository;
    @Override
    public List <Firestation> getFirestationsFromSafetynet(){
        safetynetRepository.dataGetter();
        return safetynetRepository.getSafetynet().getFirestations();
    }
    @Override
    public boolean deleteFirestation(String address){
        return this.firestations.removeIf(x->x.getAddress().equals(address));
    }
    @Override
    public boolean addFirestation(String address,int caserneID){
        return firestations.add(new Firestation(address,caserneID));
    }
    @Override
    public void updateFirestation(String address,int caserneID){
        this.firestations.stream().filter(x->x.getAddress().equals(address)).forEach(x->x.setStation(caserneID));
    }
    @Override
    public Firestation getFirestation(String address,int caserneID){
        return this.firestations.stream().filter(f->f.getAddress().equals(address)&&f.getStation()==caserneID).findAny().orElseGet(null);
    }
}
