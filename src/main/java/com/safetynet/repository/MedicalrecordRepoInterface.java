package com.safetynet.repository;

import com.safetynet.model.Medicalrecord;

import java.util.List;

public interface MedicalrecordRepoInterface {
    public List<Medicalrecord> getMedicalrecords();
    public boolean deleteMedicalrecords(String firstname,String lastname);
    public boolean addMedicalrecords(String firstname, String lastname, String birthdate,List<String>medications,List<String>allergies);
    public Medicalrecord updateMedicalrecords(String firstname, String lastname, String birthdate, List<String>medications, List<String>allergies);
    Medicalrecord getMedicalrecord(String firstname,String lastname);
}
