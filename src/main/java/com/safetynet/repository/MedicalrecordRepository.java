package com.safetynet.repository;

import com.safetynet.model.Medicalrecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalrecordRepository {
    List <Medicalrecord> medicalrecords;
    @Autowired
    JsonDataGetter jsonDataGetter;

    public List<Medicalrecord> getMedicalrecordsFromSafetynet(){
        jsonDataGetter.dataGetter();
        return jsonDataGetter.getSafetynet().getMedicalrecords();
    }

    public boolean deleteMedicalrecords(String firstname,String lastname){
        boolean result=false;
        for (Medicalrecord mr:medicalrecords){
            if (mr.getFirstName().equals(firstname)&&mr.getLastName().equals(lastname)){
                result=medicalrecords.remove(mr);
                break;
            }
        }
        return result;
    }

    public boolean addMedicalrecords(String firstname, String lastname, String birthdate,List<String>medications,List<String>allergies){
        return medicalrecords.add(new Medicalrecord(firstname,lastname,birthdate,medications,allergies));
    }

    public boolean updateMedicalrecords(String firstname, String lastname, String birthdate,List<String>medications,List<String>allergies){
        boolean result=false;
        for(Medicalrecord mr:medicalrecords){
            if(mr.getFirstName().equals(firstname)&&mr.getLastName().equals(lastname)){
                mr.setFirstName(firstname);
                mr.setLastName(lastname);
                mr.setBirthdate(birthdate);
                mr.setMedications(medications);
                mr.setAllergies(allergies);
                result=true;
                break;
            }
        }
        return result;
    }
}
