package com.ocr.axa.jlp.abernathy.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ocr.axa.jlp.abernathy.model.Patient;

@Repository
public interface PatientDAO extends JpaRepository<Patient, Long>{

    public Patient findByFirstname(String patientFirstname);

    public boolean existsByFirstname(String patientFirstname);

}
