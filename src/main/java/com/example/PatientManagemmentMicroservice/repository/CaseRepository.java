package com.example.PatientManagemmentMicroservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PatientManagemmentMicroservice.model.Case;


public interface CaseRepository extends JpaRepository<Case,UUID>{
    List<Case> findByPatientId(UUID patientId);
    List<Case> findByPatientIdAndStatusNot(UUID patientId, Case.Status status);
}
