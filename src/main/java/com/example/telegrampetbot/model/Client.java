package com.example.telegrampetbot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    private Long passportNumber;
    private Long chatId;
    private String name;
    private String surname;
    private String mail;

    public Client(Long id, Long passportNumber, Long chatId, String name, String surname, String mail){
        this.id = id;
        this.passportNumber = passportNumber;
        this.chatId = chatId;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public Client() {
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(Long passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Long getChatId() {
        return chatId;
    }
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
}
