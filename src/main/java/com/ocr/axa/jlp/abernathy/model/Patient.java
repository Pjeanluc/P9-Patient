package com.ocr.axa.jlp.abernathy.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(length=100)
    @NotNull
    private String firstname;

    @Column(length=100)
    @NotNull
    private String lastname;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    @Column(length = 1)
    private String genre;

    @Column(length=100)
    @NotNull
    private String address;

    @Column(length=100)
    @NotNull
    private String phoneNumber;

    public Patient() {
        super();
    }

    public Patient(Long id, String firstname, String lastname, LocalDate birthDate, String genre, String address, String phoneNumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.genre = genre;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthDate() { return birthDate; }

    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
