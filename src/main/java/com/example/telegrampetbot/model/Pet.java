package com.example.telegrampetbot.model;

import javax.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long ownerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "ownerId")
    private Client client;

    public Pet(Long id, String name, Long ownerId, Client client){
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.client = client;
    }

    public Pet() {
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

    public Long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}
