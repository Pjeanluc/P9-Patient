package com.ocr.axa.jlp.abernathy.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ocr.axa.jlp.abernathy.model.Patient;

import java.util.Date;

@Repository
public interface PatientDAO extends JpaRepository<Patient, Long>{


    public boolean existsByFirstnameAndLastnameAndBirthDateAndGenre(String firstname,
                                                                    String lastname,
                                                                    Date birthDate,
                                                                    String genre);
}
