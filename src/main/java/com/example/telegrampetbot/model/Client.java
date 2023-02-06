package com.example.telegrampetbot.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * модель Клиента в БД
 */
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp timeRegistered;
    private Long chatId;
    private String name;
    private String surname;
    private String phone;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Collection<Cat> cats;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Collection<Dog> dogs;

    public Client(Integer id, Long chatId, String phone, String name, String surname, Timestamp timeRegistered) {
        this.chatId = chatId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.timeRegistered = timeRegistered;
        this.id = id;
    }

    public Client() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String mail) {
        this.phone = mail;
    }

    public Timestamp getTimeRegistered() {
        return timeRegistered;
    }

    public void setTimeRegistered(Timestamp timeRegistered) {
        this.timeRegistered = timeRegistered;
    }
}
