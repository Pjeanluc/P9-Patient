package com.ocr.axa.jlp.abernathy.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

        if (!userDAO.existsByUserName(user.getUserName())) {

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
    public User findUser(User user) {
        return userDAO.findByUserName(user.getUserName());
    }

}
