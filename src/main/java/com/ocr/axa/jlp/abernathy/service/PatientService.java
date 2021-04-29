package com.ocr.axa.jlp.abernathy.service;

import com.ocr.axa.jlp.abernathy.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    
    public Patient create(Patient patient);

    public List<Patient> findAll();

    public Optional<Patient> findPatient(Long id);

    public Patient savePatient(Patient patient);

    public Patient deletePatient (Patient patient);

    List<Patient> findPatientsByFamily(String lastname);
}
