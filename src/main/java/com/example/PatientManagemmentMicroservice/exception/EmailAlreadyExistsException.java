package com.example.PatientManagemmentMicroservice.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    
    public EmailAlreadyExistsException(String message){ super(message);}
}
