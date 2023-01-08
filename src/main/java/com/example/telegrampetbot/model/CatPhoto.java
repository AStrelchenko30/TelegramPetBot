package com.example.telegrampetbot.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Class for creating an entity - a Cat Photo object (cat images)
 */
@Entity
public class CatPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;

    @OneToOne
    private Cat cat;

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Cat getCat() {
        return cat;
    }

}
