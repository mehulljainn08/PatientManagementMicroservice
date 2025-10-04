package com.example.PatientManagemmentMicroservice.mapper;



import com.example.PatientManagemmentMicroservice.dto.CaseDTO;
import com.example.PatientManagemmentMicroservice.dto.DoctorDTO;
import com.example.PatientManagemmentMicroservice.dto.PatientDTO;
import com.example.PatientManagemmentMicroservice.dto.PatientRequestDTO;
import com.example.PatientManagemmentMicroservice.model.Case;
import com.example.PatientManagemmentMicroservice.model.Doctor;
import com.example.PatientManagemmentMicroservice.model.Patient;


public class EntityMapper {
    
    

    public static PatientDTO toPatientDTO(Patient patient){
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setEmail(patient.getEmail());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setGender(patient.getGender().toString());
        dto.setPhoneNumber(patient.getPhoneNumber());
        return dto;
    }

    public static CaseDTO toCaseDTO(Case caseRecord){
        CaseDTO dto = new CaseDTO();
        dto.setId(caseRecord.getId());
        dto.setDescription(caseRecord.getDescription());
        dto.setStatus(caseRecord.getStatus().toString());
        
        
        if (caseRecord.getPatient() != null) {
            dto.setPatientId(caseRecord.getPatient().getId());
        }


        if (caseRecord.getAssignedDoctor() != null) {
            dto.setDoctorId(caseRecord.getAssignedDoctor().getId());
        }
        
        
        return dto;
    }

    public static DoctorDTO toDoctorDTO(Doctor doctor){
        DoctorDTO dto=new DoctorDTO();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setPhoneNumber(doctor.getContactNumber());
        dto.setSpecialization(doctor.getSpecialization());
        return dto;
    }
    public static Patient toPatientEntity(PatientDTO dto) {

        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setGender(Patient.Gender.valueOf(dto.getGender().toUpperCase()));
        patient.setPhoneNumber(dto.getPhoneNumber());
        return patient;
    } 
 


    public static Case toCaseEntity(CaseDTO dto, Patient patient, Doctor assignedDoctor) {
        Case caseEntity = new Case();
        caseEntity.setDescription(dto.getDescription());
        caseEntity.setStatus(Case.Status.valueOf(dto.getStatus().toUpperCase()));
        caseEntity.setPatient(patient);
        caseEntity.setAssignedDoctor(assignedDoctor);

        return caseEntity;
    }

    public static Doctor toDoctorEntity(DoctorDTO dto){
        Doctor doctor=new Doctor();
        doctor.setName(dto.getName());
        doctor.setContactNumber(dto.getPhoneNumber());
        doctor.setSpecialization(dto.getSpecialization());
        return doctor;
    }

    public static Patient reqPatient(PatientRequestDTO dto){
        Patient patient= new Patient();
        patient.setAddress(dto.getAddress());
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setGender(dto.getGender());
        return patient;
    }

}
