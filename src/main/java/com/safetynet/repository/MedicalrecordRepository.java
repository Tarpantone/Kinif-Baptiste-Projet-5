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

    public MedicalrecordRepository(){
        this.safetynetRepository=new SafetynetRepository();
        this.medicalrecords=safetynetRepository.getSafetynet().getMedicalrecords();
    }
    @Override
    public List<Medicalrecord> getMedicalrecords(){
        return medicalrecords;
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
    public Medicalrecord updateMedicalrecords(String firstname, String lastname, String birthdate, List<String>medications, List<String>allergies){
        Medicalrecord medicalrecord=new Medicalrecord();
        if(this.getMedicalrecord(firstname,lastname)!=null) {
            this.medicalrecords.stream().filter(x -> x.getFirstName().equals(firstname) && x.getLastName().equals(lastname)).forEach(x -> {
                x.setBirthdate(birthdate);
                x.setMedications(medications);
                x.setAllergies(allergies);
                medicalrecord.setAllergies(x.getAllergies());
                medicalrecord.setMedications(x.getMedications());
                medicalrecord.setBirthdate(x.getBirthdate());
                medicalrecord.setFirstName(x.getFirstName());
                medicalrecord.setLastName(x.getLastName());
            });
            return medicalrecord;
        }else {
            return null;
        }
    }
    @Override
    public Medicalrecord getMedicalrecord(String firstname,String lastname){
       return this.medicalrecords.stream().filter(m->m.getFirstName().equals(firstname)&&m.getLastName().equals(lastname)).findAny().orElseGet(null);
    }
}
