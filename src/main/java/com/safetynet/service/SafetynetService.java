package com.safetynet.service;

import com.safetynet.repository.SafetynetRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SafetynetService {
    @Autowired
    private SafetynetRepository safetynetRepository;




}
