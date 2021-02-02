package com.ocr.axa.jlp.abernathy.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ocr.axa.jlp.abernathy.model.User;

@Repository
public interface UserDAO  extends JpaRepository<User, Long>{

    public User findByUsername(String username);

    public boolean existsByUsername(String username);

}
