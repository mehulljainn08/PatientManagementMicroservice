package com.example.PatientManagemmentMicroservice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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


    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Case> caseRecord;
}
