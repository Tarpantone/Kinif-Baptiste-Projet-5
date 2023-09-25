package com.safetynet.service;

import com.safetynet.model.Medicalrecord;

import java.util.List;
import java.util.Optional;

public interface MedicalrecordSerInterface {
    Optional<Medicalrecord> getMedicalrecord(String firstname, String lastname);
    Iterable<Medicalrecord>getMedicalrecords();
    boolean addMedicalrecord(String firstname, String lastname, String birthdate, List<String> medications, List<String>allergies);
    boolean deleteMedicalrecord(String firstname,String lastname);
    Medicalrecord updateMedicalrecord(String firstname, String lastname, String birthdate, List<String>medications, List<String>allergies);
}
