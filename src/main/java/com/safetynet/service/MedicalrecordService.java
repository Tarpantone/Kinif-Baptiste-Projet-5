package com.safetynet.service;

import com.safetynet.model.Medicalrecord;
import com.safetynet.repository.MedicalrecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicalrecordService implements MedicalrecordSerInterface{
    @Autowired
    MedicalrecordRepository medicalrecordRepository;
    public Optional<Medicalrecord>getMedicalrecord(String firstname,String lastname){
        return Optional.ofNullable(this.medicalrecordRepository.getMedicalrecord(firstname, lastname));
    }
    public Iterable<Medicalrecord>getMedicalrecords(){
        return this.medicalrecordRepository.getMedicalrecords();
    }
    public boolean addMedicalrecord(String firstname, String lastname, String birthdate, List<String> medications, List<String>allergies){
        return this.medicalrecordRepository.addMedicalrecords(firstname,lastname,birthdate,medications,allergies);
    }
    public boolean deleteMedicalrecord(String firstname,String lastname){
        return this.medicalrecordRepository.deleteMedicalrecords(firstname,lastname);
    }
    public Medicalrecord updateMedicalrecord(String firstname, String lastname, String birthdate, List<String>medications, List<String>allergies){
        Medicalrecord result=this.medicalrecordRepository.updateMedicalrecords(firstname,lastname,birthdate,medications,allergies);
        return result;
    }
}
