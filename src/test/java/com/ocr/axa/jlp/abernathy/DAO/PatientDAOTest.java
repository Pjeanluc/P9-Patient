package com.ocr.axa.jlp.abernathy.DAO;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;


import com.ocr.axa.jlp.abernathy.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class PatientDAOTest {

    @Autowired
    PatientDAO patientDAO;

    @Test
    void saveOnePatient() {
        // GIVEN
        Patient patientTest = new Patient();
        patientTest.setGenre("F");
        patientTest.setPhoneNumber("01");
        patientTest.setAddress("adresseTest");
        patientTest.setLastname("lastname");
        patientTest.setFirstname("firstname");

        // WHEN
        Patient patientAdded = patientDAO.save(patientTest);

        // THEN
        assertThat(patientAdded.getFirstname()).isEqualTo(patientTest.getFirstname());
    }
}
