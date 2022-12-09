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

    @OneToOne
    @JoinColumn(name = "id")
    private DogPhoto dogPhoto;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="id", insertable = false, updatable = false)
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

    public DogPhoto getDogPhoto() {
        return dogPhoto;
    }

    public void setDogPhoto(DogPhoto dogPhoto) {
        this.dogPhoto = dogPhoto;
    }
}
