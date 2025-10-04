package com.example.PatientManagemmentMicroservice.dto;


import java.time.LocalDate;

import com.example.PatientManagemmentMicroservice.model.Patient.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class PatientRequestDTO {
    @NotBlank
    private String name;

    @Email(message="Enter Valid Email")
    private String email;

    @NotBlank
    private String address;


    private LocalDate dateOfBirth;


    private Gender gender;

    @NotBlank
    private String phoneNumber;
}
