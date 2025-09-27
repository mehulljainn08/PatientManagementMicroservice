package com.example.PatientManagemmentMicroservice.dto;

import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class DoctorWithCasesDTO {
    private UUID id;
    private String name;
    private String specialization;
    private String phoneNumber;
    private List<CaseSummaryDTO> cases; // The list of simple cases
}