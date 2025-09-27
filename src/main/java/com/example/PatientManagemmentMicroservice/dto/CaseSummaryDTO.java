package com.example.PatientManagemmentMicroservice.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class CaseSummaryDTO {
    private UUID id;
    private String description;
    private String status;
    private UUID patientId;
}