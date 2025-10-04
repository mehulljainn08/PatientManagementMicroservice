package com.example.PatientManagemmentMicroservice.model;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.PatientManagemmentMicroservice.dto.PatientDTO;
import com.example.PatientManagemmentMicroservice.repository.PatientRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    
    @NotNull
    private String name;

    @Email
    @Column(unique=true)
    private String email;
    
    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private String address;

    @NotNull
    private String phoneNumber;



    public enum Gender { MALE, FEMALE, OTHER }

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    

    public void partialUpdate(PatientDTO dto) {
        if (dto.getName() != null) {
            this.setName(dto.getName());
        }   
        if (dto.getEmail() != null) {
            this.setEmail(dto.getEmail());
        }
        if (dto.getPhoneNumber() != null) {
            this.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getDateOfBirth() != null) {
            this.setDateOfBirth(dto.getDateOfBirth());
        }
        if (dto.getGender() != null) {
            this.setGender(Patient.Gender.valueOf(dto.getGender().toUpperCase()));
        }
    }

    
    
}
