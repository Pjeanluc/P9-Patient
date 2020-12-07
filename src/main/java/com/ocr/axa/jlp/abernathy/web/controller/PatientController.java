package com.ocr.axa.jlp.abernathy.web.controller;

import com.ocr.axa.jlp.abernathy.model.Patient;
import com.ocr.axa.jlp.abernathy.service.ConnectionService;
import com.ocr.axa.jlp.abernathy.service.PatientService;
import com.ocr.axa.jlp.abernathy.web.exceptions.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/patient")
public class PatientController {
    private static final Logger logger = LogManager.getLogger("generalController");

    @Autowired
    PatientService patientService;
    

    /**
     * 
     * @return List of all Patient
     */
    
    @GetMapping(path = "/all")
    @ResponseBody
    public List<Patient> getAllPatients() {
        List<Patient> patientsFound = patientService.findAll();
        logger.info(" get all patient : OK");
        return patientsFound;
    }
    
    /**
     * 
     * @param patient (patientName)
     * @return information for the patient
     */
    @GetMapping
    @ResponseBody
    public Patient getpatient(@RequestBody Patient patient) {
        Patient patientFound = patientService.findPatient(patient);
        if (patientFound == null){
            logger.error("patient not found");
            throw new ControllerException(("Patient not found"));
        }
        else {
            logger.info(" get patient : OK");
            return patientFound;
        }
    }

    /**
     * 
     * @param patient (patientName, password required, pseudo)
     * @return patient created
     */
    @PostMapping("/patientInfo")
    public ResponseEntity<Patient> createpatient(@RequestBody Patient patient) {


        if (patient.getFirstname().isEmpty()) {
            logger.error("inscriptionPatient : KO");
            throw new ControllerException("patientName is required");
        }

        Patient patientAdded = patientService.create(patient);
        
        if (patientAdded == null) {
            logger.error("inscriptionPatient : KO");
            throw new ControllerException("patientName already exist");
        }
        else {
            logger.info("Add patient OK " + patient.toString());
            return new ResponseEntity(patientAdded, HttpStatus.OK);
        }
        
    }


}
