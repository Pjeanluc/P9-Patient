package com.ocr.axa.jlp.abernathy.DAO;

import com.ocr.axa.jlp.abernathy.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientDAO extends JpaRepository<Patient, Long>{


    public boolean existsByFirstnameAndLastnameAndBirthDateAndGenre(String firstname,
                                                                    String lastname,
                                                                    LocalDate birthDate,
                                                                    String genre);

    List<Patient> findByLastname(String lastname);
}
