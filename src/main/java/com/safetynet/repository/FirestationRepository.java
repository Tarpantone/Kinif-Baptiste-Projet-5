package com.safetynet.repository;

import com.safetynet.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationRepository implements FirestationRepoInterface{
    List <Firestation> firestations;
    @Autowired
    SafetynetRepository safetynetRepository;

    public FirestationRepository(){
        this.safetynetRepository=new SafetynetRepository();
        this.firestations=safetynetRepository.getSafetynet().getFirestations();
    }
    @Override
    public List <Firestation> getFirestations(){
       return firestations;
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
    public Firestation updateFirestation(String address, int caserneID){
        Firestation firestation=new Firestation(address,caserneID);
        this.firestations.stream().filter(x->x.getAddress().equals(address)).forEach(x->x.setStation(caserneID));
        return firestation;
    }
    @Override
    public Firestation getFirestation(String address){
        return this.firestations.stream().filter(f->f.getAddress().equals(address)).findAny().orElseGet(null);
    }
}
