package com.ocr.axa.jlp.abernathy.web.controller;

import com.ocr.axa.jlp.abernathy.model.Patient;
import com.ocr.axa.jlp.abernathy.service.PatientService;
import com.ocr.axa.jlp.abernathy.web.exceptions.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
     * @param id (id of the patient)
     * @return information for the patient
     */
    @GetMapping (path = "/id")
    @ResponseBody
    public Patient getpatient(@RequestParam long id) throws ControllerException {
        Optional<Patient> patientFound = patientService.findPatient(id);
        if (!patientFound.isPresent()){
            logger.error("patient not found");
            throw new ControllerException(("Patient not found"));
        }
        else {
            logger.info(" get patient : OK");
            return patientFound.get();
        }
    }

    /**
     *
     * @param lastname (lastname of the family)
     * @return informations for all patient with same lastname
     */
    @GetMapping (path = "/family")
    @ResponseBody
    public List<Patient> getPatientsSameFamily(@RequestParam String lastname) throws ControllerException {
        List<Patient> patients = patientService.findPatientsByFamily(lastname);
        if (patients.isEmpty()){
            logger.error("patient not found");
            throw new ControllerException(("Patient not found"));
        }
        else {
            logger.info(" get patient by family : OK");
            return patients;
        }
    }

    /**
     * 
     * @param patient (patientName, password required, pseudo)
     * @return patient created
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public ResponseEntity<Patient> createpatient(@RequestBody Patient patient)  {


        if (patient.getFirstname().isEmpty()) {
            logger.error("inscriptionPatient : KO");
            throw new ControllerException("patientName is required");
        }

        Patient patientAdded = patientService.create(patient);

        if (patientAdded == null) {
            logger.error("inscriptionPatient : KO");
            throw new ControllerException("patientName already exist");
        } else {
            logger.info("Add patient OK " + patient.toString());
            return new ResponseEntity(patientAdded, HttpStatus.OK);
        }
    }

        @PostMapping("/update")
        public Patient updatePatient(@RequestBody Patient patient) throws ControllerException {
            Optional<Patient> patientToFind = patientService.findPatient(patient.getId());
            if (!patientToFind.isPresent()){
                logger.error("patient not found");
                throw new ControllerException(("Patient not found"));
            }

           return patientService.savePatient(patient);

        }

    @PostMapping("/delete/id")
    public Patient deletePatient(@RequestParam long id) throws ControllerException {
        Optional<Patient> patientToFind = patientService.findPatient(id);
        if (!patientToFind.isPresent()){
            logger.error("patient not found");
            throw new ControllerException(("Patient not found"));
        }

        return patientService.deletePatient(patientToFind.get());

    }



}
