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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class UserServiceTest {

    @MockBean
    UserDAO userDAOMock;

    @Autowired
    UserService userServiceTest;

    String userNameConst = "firstname";
    String passwordConst = "password";
    String pseudoConst = "pseudotest";

    @Test
    void createUserTest() {
        // GIVEN
        User userTest = new User(userNameConst, passwordConst, pseudoConst);

        // WHEN
        User userCreatedTest = userServiceTest.create(userTest);

        // THEN
        assertThat(userCreatedTest.getUserName()).isEqualTo(userNameConst);
        assertThat(userCreatedTest.getPseudo()).isEqualTo(pseudoConst);

    }

}
