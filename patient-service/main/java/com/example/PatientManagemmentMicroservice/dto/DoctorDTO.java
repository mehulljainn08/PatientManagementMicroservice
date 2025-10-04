package com.example.PatientManagemmentMicroservice.dto;

import lombok.Data;

import java.util.UUID;



@Data
public class DoctorDTO {
    private UUID id;
    private String name;
    private String specialization;
    private String phoneNumber;
}
