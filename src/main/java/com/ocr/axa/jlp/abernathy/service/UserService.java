package com.ocr.axa.jlp.abernathy.service;

import java.util.List;

import com.ocr.axa.jlp.abernathy.model.User;

public interface UserService {
    
    public User create(User user);

    public List<User> findAll();

    public User findUser(User user);

}
