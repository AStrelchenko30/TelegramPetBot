package com.example.telegrampetbot.model;

import javax.persistence.*;

/**
 * Модель Cat в БД
 */
@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="owner", insertable = false, updatable = false)
    private Client owner;

    public Cat() {
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

//    public CatPhoto getCatPhoto() {
//        return catPhoto;
//    }
//
//    public void setCatPhoto(CatPhoto catPhoto) {
//        this.catPhoto = catPhoto;
//    }
}
