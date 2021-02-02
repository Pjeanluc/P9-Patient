package com.ocr.axa.jlp.abernathy.service;

import java.util.List;
import java.util.Optional;

import com.ocr.axa.jlp.abernathy.model.Patient;
import com.ocr.axa.jlp.abernathy.model.User;

public interface UserService {
    
    public User create(User user);

    public List<User> findAll();

    public Optional<User> findUser(Long id);

    public User saveUser(User user);

    public User deleteUser (User user);

    public User findByUsername(String username);
}
