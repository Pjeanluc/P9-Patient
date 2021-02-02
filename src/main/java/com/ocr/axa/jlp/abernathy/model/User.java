package com.ocr.axa.jlp.abernathy.model;

import com.sun.istack.NotNull;
import javax.validation.constraints.NotBlank;

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
    private Long id;
    @Column(length=100)
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Column(length=100)
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Column(length=100)
    @NotBlank(message = "Pseudo is mandatory")
    private String pseudo;
    @NotBlank(message = "Role is mandatory")
    private String role;

    public User() {
        super();
    }

    public User(@NotNull String username, @NotNull String password, @NotNull String pseudo) {
        super();
        this.username = username;
        this.password = password;
        this.pseudo = pseudo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

}
