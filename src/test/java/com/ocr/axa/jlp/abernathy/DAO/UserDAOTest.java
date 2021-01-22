package com.ocr.axa.jlp.abernathy.DAO;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import com.ocr.axa.jlp.abernathy.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class UserDAOTest {
    @Autowired
    UserDAO userDAO;

    @Test
    void saveOneUser() {
        // GIVEN
        User userTest = new User();
        userTest.setUserName("usernametest");
        userTest.setPassword("password");
        userTest.setPseudo("pseudotest");

        // WHEN
        User userAdded = userDAO.save(userTest);

        // THEN
        assertThat(userAdded.getPseudo()).isEqualTo(userTest.getPseudo());
    }
}
