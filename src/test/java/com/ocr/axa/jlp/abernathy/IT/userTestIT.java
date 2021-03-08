package com.ocr.axa.jlp.abernathy.IT;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class userTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUserTest() throws Exception {

        // GIVEN

        String questionBody = "{"+
                "\"username\": \"user2\","+
                "\"password\": \"password\","+
                "\"pseudo\": \"user1\""+
                "}";

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/user/add").content(questionBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void updateUserTest() throws Exception {

        // GIVEN

        String questionBody = "{"+
                "\"id\":0,"+
                "\"userName\": \"user1\","+
                "\"password\": \"password\","+
                "\"pseudo\": \"user1\""+
                "}";

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/user/update").content(questionBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void deleteUserTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/user/delete/id?id=0").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

    }

}
