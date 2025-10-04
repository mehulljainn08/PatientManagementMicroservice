package com.example.PatientManagemmentMicroservice.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class CaseDTO {
    private UUID id;
    private String description;
    private String status;
    private UUID patientId;       
    private UUID doctorId;     
}