package com.example.PatientManagemmentMicroservice.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.PatientManagemmentMicroservice.dto.CaseDTO;
import com.example.PatientManagemmentMicroservice.dto.PatientDTO;
import com.example.PatientManagemmentMicroservice.dto.PatientRequestDTO;
import com.example.PatientManagemmentMicroservice.dto.PatientSearchDTO;
import com.example.PatientManagemmentMicroservice.exception.EmailAlreadyExistsException;
import com.example.PatientManagemmentMicroservice.mapper.EntityMapper;
import com.example.PatientManagemmentMicroservice.model.Case;
import com.example.PatientManagemmentMicroservice.model.Patient;
import com.example.PatientManagemmentMicroservice.repository.CaseRepository;
import com.example.PatientManagemmentMicroservice.repository.PatientRepository;

@Service
public class PatientService {
    
    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public CaseRepository caseRepository;
    
    public Page<PatientDTO> getAllPatients(Pageable pageable) {
    
        Page<Patient> patientPage = patientRepository.findAll(pageable);
    
    
        return patientPage.map(EntityMapper::toPatientDTO);
    }


    public List<CaseDTO> getCasesbyPatientId(UUID patientId){
        List<Case> cases=caseRepository.findByPatientId(patientId);

        return cases.stream().map(EntityMapper::toCaseDTO).collect(Collectors.toList());
        
    }

    public PatientDTO getPatientById(UUID patientId){
        Optional<Patient> optionalPatient=patientRepository.findById(patientId);

        return optionalPatient
                  .map(EntityMapper::toPatientDTO)
                  .orElse(null);
    }

    
    public List<CaseDTO> getActiveCasesbyPatientId(UUID patientId){
        List<Case> cases=caseRepository.findByPatientIdAndStatusNot(patientId,Case.Status.CLOSED);

        return cases.stream().map(EntityMapper::toCaseDTO).collect(Collectors.toList());
    }

    public List<PatientDTO> searchPatients(PatientSearchDTO searchCriteria) {
    
        Patient probe = new Patient();
        probe.setName(searchCriteria.getName());
        probe.setEmail(searchCriteria.getEmail());
        probe.setPhoneNumber(searchCriteria.getPhoneNumber());

    
        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

   
        Example<Patient> example = Example.of(probe, matcher);

    
        return patientRepository.findAll(example).stream()
            .map(EntityMapper::toPatientDTO)
            .collect(Collectors.toList());
    }
    
    public PatientDTO addPatient(PatientDTO patientDTO) {
        if(patientRepository.existsByEmail(patientDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email "+"already exists"+patientDTO.getEmail());
        }


        Patient patientToSave = EntityMapper.toPatientEntity(patientDTO);
        Patient savedPatient = patientRepository.save(patientToSave);
        return EntityMapper.toPatientDTO(savedPatient);
    }

    
   

    public PatientDTO updatePatient(UUID patientId, PatientDTO patientDetails) {
    
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

    
        if (optionalPatient.isPresent()) {
       
            Patient existingPatient = optionalPatient.get();

        
            existingPatient.setName(patientDetails.getName());
            existingPatient.setEmail(patientDetails.getEmail());
            existingPatient.setPhoneNumber(patientDetails.getPhoneNumber());
            existingPatient.setDateOfBirth(patientDetails.getDateOfBirth());
            existingPatient.setGender(Patient.Gender.valueOf(patientDetails.getGender().toUpperCase()));
        
        
            Patient updatedPatient = patientRepository.save(existingPatient);
            return EntityMapper.toPatientDTO(updatedPatient);
        } else {
        
            return null;
        }
    }

    public boolean deletePatient(UUID patientId) {
   
        if (patientRepository.existsById(patientId)) {

            patientRepository.deleteById(patientId);
            return true;
        } else {
            return false;
        }
    }

}
