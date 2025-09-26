package com.example.PatientManagemmentMicroservice.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotNull
    private String name;

    @NotNull
    private String specialization;

    @NotNull
    private String contactNumber;

    @OneToMany(mappedBy = "assignedDoctor")
    private List<Case> cases;
    
}
