package com.ocr.axa.jlp.abernathy.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.ocr.axa.jlp.abernathy.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ocr.axa.jlp.abernathy.DAO.UserDAO;
import com.ocr.axa.jlp.abernathy.model.User;
import com.ocr.axa.jlp.abernathy.web.exceptions.ControllerException;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger("UserService");

    @Autowired
    UserDAO userDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {

        if (!userDAO.existsByUsername(user.getUsername())) {

           user.setPassword(passwordEncoder.encode(user.getPassword()));
           userDAO.save(user);

            return user;
        } else {
            logger.error("UserName already exist");
            return null;
        }
    }

    @Override
    public List<User> findAll() {

        return userDAO.findAll();
    }


    @Override
    public Optional<User> findUser(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public User saveUser(User user) {

        Optional<User> userToFind = userDAO.findById(user.getId());

        if (!userToFind.isPresent()) {
            logger.error("User not found");
            return null;
        }
        userToFind.get().setUsername(user.getUsername());
        userToFind.get().setPseudo(user.getPseudo());
        userToFind.get().setPassword(passwordEncoder.encode(user.getPassword()));
        userToFind.get().setRole(user.getRole());

        userDAO.save(userToFind.get());

        return userToFind.get();

    }

    @Override
    public User deleteUser(User user ) {
        Optional<User> userToFind = userDAO.findById(user.getId());

        if (!userToFind.isPresent()) {
            logger.error("user not found");
            return null;
        }

        userDAO.delete(userToFind.get());

        return userToFind.get();
    }

    @Override
    public User findByUsername(String username) {

        User userToFind = userDAO.findByUsername(username);

        if (userToFind == null){
            logger.error("user not found");
            return null;
        }

        return userToFind;
    }
}
