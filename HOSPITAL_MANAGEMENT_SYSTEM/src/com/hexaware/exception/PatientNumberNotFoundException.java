package com.hexaware.exception;

//Custom exception thrown when a patient number is not found.

@SuppressWarnings("serial")
public class PatientNumberNotFoundException extends Exception {

    public PatientNumberNotFoundException(String message) {
        super(message);
    }
}
