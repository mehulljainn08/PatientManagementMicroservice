package com.example.PatientManagemmentMicroservice.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="case_record")
public class Case {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    

    @NotNull
    private String description;

    
    public enum Status { OPEN, IN_PROGRESS, CLOSED }

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;


    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor assignedDoctor;
}
