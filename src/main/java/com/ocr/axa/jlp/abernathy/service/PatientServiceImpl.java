package com.ocr.axa.jlp.abernathy.service;

import com.ocr.axa.jlp.abernathy.DAO.PatientDAO;
import com.ocr.axa.jlp.abernathy.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private static final Logger logger = LogManager.getLogger("PatientService");

    @Autowired
    PatientDAO patientDAO;

    @Override
    public Patient create(Patient patient) {

        if (!patientDAO.existsByFirstnameAndLastnameAndBirthDateAndGenre(patient.getFirstname(),
                patient.getLastname(),patient.getBirthDate(),patient.getGenre())) {
            
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
    public Optional<Patient> findPatient(Long id) {
        return patientDAO.findById(id);
    }

    @Override
    public Patient savePatient(Patient patient) {

        Optional<Patient> patientToFind = patientDAO.findById(patient.getId());

        if (!patientToFind.isPresent()) {
            logger.error("Patient not found");
            return null;
        }
        patientToFind.get().setLastname(patient.getLastname());
        patientToFind.get().setLastname(patient.getLastname());
        patientToFind.get().setAddress(patient.getAddress());
        patientToFind.get().setBirthDate(patient.getBirthDate());
        patientToFind.get().setGenre(patient.getGenre());
        patientToFind.get().setPhoneNumber(patient.getPhoneNumber());

        patientDAO.save(patientToFind.get());

        return patientToFind.get();

    }

    @Override
    public Patient deletePatient(Patient patient) {
        Optional<Patient> patientToFind = patientDAO.findById(patient.getId());

        if (!patientToFind.isPresent()) {
            logger.error("Patient not found");
            return null;
        }

        patientDAO.delete(patientToFind.get());

        return patientToFind.get();
    }

    @Override
    public List<Patient> findPatientsByFamily(String lastname) {

        return patientDAO.findByLastname(lastname);

    }


}
