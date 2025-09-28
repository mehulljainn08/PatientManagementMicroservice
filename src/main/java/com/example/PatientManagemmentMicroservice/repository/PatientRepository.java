package com.example.PatientManagemmentMicroservice.repository;

import com.example.PatientManagemmentMicroservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID>,  QueryByExampleExecutor<Patient>{

	List<Patient> findByNameContainingIgnoreCase(String name);
    boolean existsByEmail(String email);
}
