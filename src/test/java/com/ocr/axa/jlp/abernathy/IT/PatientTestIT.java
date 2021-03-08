package com.ocr.axa.jlp.abernathy.IT;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import com.ocr.axa.jlp.abernathy.web.exceptions.ControllerException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WithMockUser(roles = "USER")
public class PatientTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createPatientTest() throws Exception {

        // GIVEN

        String questionBody = "{"+
                "\"firstname\": \"patient\","+
                "\"lastname\": \"test\","+
                "\"birthdate\": \"2000-10-30\""+
                "}";

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/patient/add").content(questionBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void updatePatientTest() throws Exception {

        // GIVEN

        String questionBody = "{"+
                "\"id\":0," +
                "\"firstname\": \"patient\","+
                "\"lastname\": \"test\","+
                "\"birthdate\": \"2000-10-30\""+
                "}";

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/patient/update").content(questionBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void createPatientWithErrorTest()  {

        // GIVEN

        String questionBody = "{"+
                "\"firstname\": \"\","+
                "\"lastname\": \"test\","+
                "\"birthdate\": \"2000-10-30\""+
                "}";

        // WHEN
        // THEN
        try {
            this.mockMvc
                    .perform(post("/patient/add").content(questionBody).contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print());
        } catch (Exception e) {
            assertThat(e).hasMessage("Request processing failed; nested exception is com.ocr.axa.jlp.abernathy.web.exceptions.ControllerException: ERROR : patientName is required");
        }


    }

    @Test
    public void deletePatientTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/patient/delete/id?id=0").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

    }
}
