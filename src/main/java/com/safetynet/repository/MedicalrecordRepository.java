package com.safetynet.repository;

import com.safetynet.model.Medicalrecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalrecordRepository implements MedicalrecordRepoInterface {
    List <Medicalrecord> medicalrecords;
    @Autowired
    SafetynetRepository safetynetRepository;
    @Override
    public List<Medicalrecord> getMedicalrecordsFromSafetynet(){
        safetynetRepository.dataGetter();
        return safetynetRepository.getSafetynet().getMedicalrecords();
    }
    @Override
    public boolean deleteMedicalrecords(String firstname,String lastname){
        return this.medicalrecords.removeIf(x->x.getFirstName().equals(firstname)&&x.getLastName().equals(lastname));
    }
    @Override
    public boolean addMedicalrecords(String firstname, String lastname, String birthdate,List<String>medications,List<String>allergies){
        return medicalrecords.add(new Medicalrecord(firstname,lastname,birthdate,medications,allergies));
    }
    @Override
    public void updateMedicalrecords(String firstname, String lastname, String birthdate,List<String>medications,List<String>allergies){
        this.medicalrecords.stream().filter(x->x.getFirstName().equals(firstname)&&x.getLastName().equals(lastname)).forEach(x->x.setUpdate(birthdate,medications,allergies));
    }
}
