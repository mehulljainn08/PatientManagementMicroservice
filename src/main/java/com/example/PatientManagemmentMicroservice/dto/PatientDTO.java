package com.example.PatientManagemmentMicroservice.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class PatientDTO {
    
    private UUID Id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
    private String phoneNumber;
}
