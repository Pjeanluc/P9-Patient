package com.ocr.axa.jlp.abernathy.Controller;

import static com.ocr.axa.jlp.abernathy.Controller.UserControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocr.axa.jlp.abernathy.model.Patient;
import com.ocr.axa.jlp.abernathy.model.User;
import com.ocr.axa.jlp.abernathy.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WithMockUser(roles = "USER")
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

   @MockBean
    private Patient patientMock;

    String patientNameConst = "usernametest";

    @Test
    void getAllPatientControllerTest() throws Exception {

        // GIVEN
        List<Patient> patients = new ArrayList<>();
        LocalDate date = LocalDate.parse("2000-01-01");
        patientMock = new Patient(0L,patientNameConst, "",date,"","","" );
        patients.add(patientMock);
        Mockito.when(patientService.findAll()).thenReturn(patients);

        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/patient/all").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void getOnePatientControllerTest() throws Exception {

        // GIVEN
        LocalDate date =LocalDate.parse("2000-01-01");
        patientMock = new Patient(0L,patientNameConst, "",date,"","","" );
        Optional patientOptional = Optional.of(patientMock);

        Mockito.when(patientService.findPatient(any(Long.class))).thenReturn(patientOptional);

        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/patient/id?id=0")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstname").value(patientNameConst));
    }
    @Test
    void SavePatientControllerTest() throws Exception {

        // GIVEN
        String bithdate = "2000-01-01";
        LocalDate date = LocalDate.parse(bithdate);
        patientMock = new Patient(0L,patientNameConst, "",date,"","","" );

        Mockito.when(patientService.create(any(Patient.class))).thenReturn(patientMock);

        String questionBody = "{"+
                "\"firstname\": \"patient\","+
                "\"lastname\": \"test\","+
                "\"birthdate\": \"2000-10-30\""+
                "}";

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/patient/add")
                        .content(questionBody)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deletePatientControllerTest() throws Exception {

        // GIVEN
        LocalDate date = LocalDate.parse("2000-01-01");
        patientMock = new Patient(0L,patientNameConst, "",date,"","","" );
        Optional patientOptional = Optional.of(patientMock);

        Mockito.when(patientService.deletePatient(any(Patient.class))).thenReturn(patientMock);
        Mockito.when(patientService.findPatient(any(Long.class))).thenReturn(patientOptional);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/patient/delete/id?id=0")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstname").value(patientNameConst));
    }
}
