package com.example.PatientManagemmentMicroservice.controller;

import com.example.PatientManagemmentMicroservice.dto.CaseDTO;
import com.example.PatientManagemmentMicroservice.dto.PatientDTO;
import com.example.PatientManagemmentMicroservice.dto.PatientRequestDTO;
import com.example.PatientManagemmentMicroservice.dto.PatientSearchDTO;
import com.example.PatientManagemmentMicroservice.service.PatientService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    
    @GetMapping("all-patient-record")
    public Page<PatientDTO> getAllPatients(Pageable pageable) {
        return patientService.getAllPatients(pageable);
    }

    
    @PostMapping("/search")
    public List<PatientDTO> searchPatients(@RequestBody PatientSearchDTO searchCriteria) {
        return patientService.searchPatients(searchCriteria);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findPatientById(@PathVariable UUID id){
        PatientDTO patientDTO=patientService.getPatientById(id);
        
        if (patientDTO != null) {
            return ResponseEntity.ok(patientDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
   
    
    
    
    @GetMapping("/{id}/cases")
    public ResponseEntity<List<CaseDTO>> getCasesForPatient(@PathVariable UUID id) {
        
        List<CaseDTO> cases = patientService.getCasesbyPatientId(id);
        return ResponseEntity.ok(cases);
    }


    @PostMapping("/create-patient-record")
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientDTO patientToSave = patientService.addPatient(patientRequestDTO);
      
        return new ResponseEntity<>(patientToSave, HttpStatus.CREATED);
    }

    
    @PutMapping("/update-patient-record/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable UUID id, @RequestBody PatientDTO patientDetails) {
        PatientDTO updatedPatient = patientService.updatePatient(id, patientDetails);
        if (updatedPatient != null) {
            return ResponseEntity.ok(updatedPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/delete-patient-record/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable UUID id) {
        boolean deleted = patientService.deletePatient(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}