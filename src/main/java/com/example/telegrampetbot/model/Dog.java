package com.example.telegrampetbot.model;

import javax.persistence.*;

/**
 * Модель Dog в БД
 */
@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Dog(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="owner", insertable = false, updatable = false)
    private Client owner;

    public Dog() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
