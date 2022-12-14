package com.example.telegrampetbot.model;

import javax.persistence.*;

/**
 * модель отчетов cat в БД
 */
@Entity
public class CatReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String condition;
    private String ration;
    private String changes;

    @OneToOne
    @JoinColumn(name = "catPhoto")
    private CatPhoto catPhoto;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id" ,referencedColumnName = "id")
    private Client client;

    public CatReport(Long id, String condition, String ration, String changes) {
        this.id = id;
        this.condition = condition;
        this.ration = ration;
        this.changes = changes;
    }

    public CatReport(){
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

    public CatPhoto getCatPhoto() {
        return catPhoto;
    }

    public void setCatPhoto(CatPhoto catPhoto) {
        this.catPhoto = catPhoto;
    }
}
