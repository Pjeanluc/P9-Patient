package com.ocr.axa.jlp.abernathy.service;

import com.ocr.axa.jlp.abernathy.DAO.PatientDAO;
import com.ocr.axa.jlp.abernathy.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private static final Logger logger = LogManager.getLogger("PatientService");

    @Autowired
    PatientDAO patientDAO;

    @Override
    public Patient create(Patient patient) {

        if (!patientDAO.existsByFirstname(patient.getFirstname())) {
            
           patientDAO.save(patient);

            return patient;
        } else {
            logger.error("PatientName already exist");
            return null;
        }
    }

    @Override
    public List<Patient> findAll() {

        return patientDAO.findAll();
    }


    @Override
    public Patient findPatient(Patient patient) {
        return patientDAO.findByFirstname(patient.getFirstname());
    }

}
