package com.example.PatientManagemmentMicroservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PatientManagemmentMicroservice.dto.CaseDTO;
import com.example.PatientManagemmentMicroservice.mapper.EntityMapper;
import com.example.PatientManagemmentMicroservice.model.Case;
import com.example.PatientManagemmentMicroservice.model.Doctor;
import com.example.PatientManagemmentMicroservice.model.Patient;
import com.example.PatientManagemmentMicroservice.repository.CaseRepository;
import com.example.PatientManagemmentMicroservice.repository.DoctorRepository;
import com.example.PatientManagemmentMicroservice.repository.PatientRepository;

@Service
public class CaseService {
    
    @Autowired
    public CaseRepository caseRepository;

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public DoctorRepository doctorRepository;

    public List<CaseDTO> getCasesByPatientId(UUID PatientId){
        List<Case> cases=caseRepository.findByPatientId(PatientId);
        return cases.stream()
                .map(EntityMapper::toCaseDTO)
                .collect(Collectors.toList());
    }

    public boolean addCase(CaseDTO dto){
        
        Optional<Patient> optionalPatient = patientRepository.findById(dto.getPatientId());
        Optional<Doctor> optionalDoctor = doctorRepository.findById(dto.getDoctorId());

        if (optionalPatient.isPresent() && optionalDoctor.isPresent()) {
        
            Patient patient = optionalPatient.get();
            Doctor doctor = optionalDoctor.get();
        
            Case caseToSave = EntityMapper.toCaseEntity(dto, patient, doctor);
            caseRepository.save(caseToSave);
            return true;
        }
        return false;
    }
}
