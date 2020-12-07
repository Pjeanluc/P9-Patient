package com.ocr.axa.jlp.abernathy.service;

import com.ocr.axa.jlp.abernathy.model.Patient;

import java.util.List;

public interface PatientService {
    
    public Patient create(Patient patient);

    public List<Patient> findAll();

    public Patient findPatient(Patient patient);

}
