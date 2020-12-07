package com.ocr.axa.jlp.abernathy.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(length=100)
    @NotNull
    private String userName;

    @Column(length=100)
    @NotNull
    private String password;

    @Column(length=100)
    @NotNull
    private String pseudo;

    public User() {
        super();
    }

    public User(@NotNull String userName, @NotNull String password, @NotNull String pseudo) {
        super();
        this.userName = userName;
        this.password = password;
        this.pseudo = pseudo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String firstname) {
        this.userName = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

}
