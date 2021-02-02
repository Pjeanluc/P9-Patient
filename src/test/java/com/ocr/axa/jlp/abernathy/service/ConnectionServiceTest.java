package com.ocr.axa.jlp.abernathy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.ocr.axa.jlp.abernathy.DAO.UserDAO;
import com.ocr.axa.jlp.abernathy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ConnectionServiceTest {

    @MockBean
    UserDAO userDAOMock;

    @Autowired
    ConnectionService connectionServiceTest;

    @Autowired
    PasswordEncoder passwordEncoder;

    String userNameConst = "firstnametest";
    String passwordConst = "password";
    String pseudoConst = "pseudotest";

    @Test
    void connectUserTest() {
        // GIVEN
        User userToConnectTest = new User();
        userToConnectTest.setUsername("username");
        userToConnectTest.setPassword("password");
        User userTest = new User(userNameConst, "", pseudoConst);
        userTest.setPassword(passwordEncoder.encode("password"));
        Mockito.when(userDAOMock.findByUsername(any(String.class))).thenReturn(userTest);

        // WHEN
        // THEN
        assertThat(connectionServiceTest.connectUser(userToConnectTest)).isEqualTo(true);
    }

    @Test
    void connectUserFailedTest() {
        // GIVEN
        User userToConnectTest = new User();
        userToConnectTest.setUsername("username");
        userToConnectTest.setPassword("notthepassword");
        User userTest = new User(userNameConst, "", pseudoConst);
        userTest.setPassword(passwordEncoder.encode("password"));
        Mockito.when(userDAOMock.findByUsername(any(String.class))).thenReturn(userTest);

        // WHEN
        // THEN
        assertThat(connectionServiceTest.connectUser(userToConnectTest)).isEqualTo(false);
    }
}
