package com.example.PatientManagemmentMicroservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class PatientRequestDTO {
    @NotBlank
    private String name;

    @Email(message="Enter Valid Email")
    private String email;

    
    private String address;

    
    private String phoneNumber;
}
