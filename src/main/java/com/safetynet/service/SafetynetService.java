package com.safetynet.service;

import com.safetynet.repository.JsonDataGetter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SafetynetService {
    @Autowired
    private JsonDataGetter jsonDataGetter;




}
