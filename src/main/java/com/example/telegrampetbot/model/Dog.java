package com.example.telegrampetbot.model;

import javax.persistence.*;

/**
 * Модель Dog в БД
 */
@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Dog(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="owner", insertable = false, updatable = false)
    private Client owner;

    public Dog() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client client) {
        this.owner = client;
    }

}
