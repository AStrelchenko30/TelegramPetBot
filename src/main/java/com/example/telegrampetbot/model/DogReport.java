package com.example.telegrampetbot.model;

import javax.persistence.*;

/**
 * модель отчетов dog в БД
 */
@Entity
public class DogReport {
    @Id
    @GeneratedValue
    private Long id;
    private String condition;
    private String ration;
    private String changes;

    @OneToOne
    @JoinColumn(name = "dog_photo_id")
    private DogPhoto dogPhoto;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id" ,referencedColumnName = "id")
    private Client client;

    public DogReport(){
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        client = client;
    }

    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRation() {
        return ration;
    }
    public void setRation(String ration) {
        this.ration = ration;
    }

    public String getChanges() {
        return changes;
    }
    public void setChanges(String changes) {
        this.changes = changes;
    }

    public DogPhoto getDogPhoto() {
        return dogPhoto;
    }

    public void setDogPhoto(DogPhoto dogPhoto) {
        this.dogPhoto = dogPhoto;
    }
}
