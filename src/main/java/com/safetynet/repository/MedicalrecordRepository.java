package com.safetynet.repository;

import com.safetynet.model.Medicalrecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalrecordRepository {
    List <Medicalrecord> medicalrecords;
    JsonDataGetter jsonDataGetter;
}
