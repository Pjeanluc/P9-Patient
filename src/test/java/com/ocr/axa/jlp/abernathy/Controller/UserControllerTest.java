package com.ocr.axa.jlp.abernathy.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocr.axa.jlp.abernathy.web.exceptions.ControllerException;
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
import com.ocr.axa.jlp.abernathy.model.User;
import com.ocr.axa.jlp.abernathy.service.ConnectionService;
import com.ocr.axa.jlp.abernathy.service.UserService;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WithMockUser(roles = "USER")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ConnectionService connectionService;

    @MockBean
    private User userMock;

    String userNameConst = "usernametest";
    String passwordConst = "password";
    String pseudoConst = "pseudotest";

    @Test
    void getAllUserControllerTest() throws Exception {

        // GIVEN
        List<User> users = new ArrayList<>();
        userMock = new User(userNameConst, passwordConst, pseudoConst);
        users.add(userMock);
        Mockito.when(userService.findAll()).thenReturn(users);

        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/user/all").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getOneUserControllerTest() throws Exception {

        // GIVEN
        userMock = new User(userNameConst, passwordConst, pseudoConst);
        Optional userOptional = Optional.of(userMock);

        Mockito.when(userService.findUser(any(Long.class))).thenReturn(userOptional);

        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/user/id?id=0")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$..username").value(userNameConst));
    }

    @Test
    void SaveUserControllerTest() throws Exception {

        // GIVEN
        userMock = new User(userNameConst, passwordConst, pseudoConst);
        Mockito.when(userService.create(any(User.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/user/add")
                        .content(asJsonString(
                                new User(userNameConst, passwordConst, pseudoConst)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

   @Test
    void ConnectUserControllerTest() throws Exception {

        // GIVEN
        userMock = new User(userNameConst, passwordConst, pseudoConst);
        Mockito.when(connectionService.connectUser(any(User.class))).thenReturn(true);

        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/user/connect").content(asJsonString(new User("admin", passwordConst, "admin")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void ConnectUserNotFoundControllerTest()  {

        // GIVEN
        userMock = new User(userNameConst, passwordConst, pseudoConst);
        Mockito.when(connectionService.connectUser(any(User.class))).thenReturn(false);

        // WHEN
        // THEN
        try {
            this.mockMvc
                    .perform(get("/user/connect").content(asJsonString(new User("nonadmin", passwordConst, "admin")))
                            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        } catch (ControllerException e) {
            assertThat(e).hasMessage("ERROR :user/password not exist");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteUserControllerTest() throws Exception {

        // GIVEN

        userMock = new User(userNameConst, passwordConst, pseudoConst);
        Mockito.when(userService.deleteUser(any(User.class))).thenReturn(userMock);

        Optional userOptional = Optional.of(userMock);
        Mockito.when(userService.findUser(any(Long.class))).thenReturn(userOptional);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/user/delete/id?id=0")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$..username").value(userNameConst));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
