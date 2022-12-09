package com.example.telegrampetbot.model;

import javax.persistence.*;

/**
 * Модель Dog в БД
 */
@Entity
public class Dog {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Dog(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToOne
    @JoinColumn(name = "dog_photo_id")
    private DogPhoto dogPhoto;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="ownerId", insertable = false, updatable = false)
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
