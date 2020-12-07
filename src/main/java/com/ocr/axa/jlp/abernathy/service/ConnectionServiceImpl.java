package com.ocr.axa.jlp.abernathy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ocr.axa.jlp.abernathy.DAO.UserDAO;
import com.ocr.axa.jlp.abernathy.model.User;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 
     * @param user (email/password)
     * @return true if  matching, else false
     */
    @Override
    public boolean connectUser(User user) {
        User userToConnect = userDAO.findByUserName(user.getUserName());

        if (!(userToConnect == null)) {
            if (passwordEncoder.matches(user.getPassword(), userToConnect.getPassword())) {
                return true;
            }
        }
        return false;
    }

}