package com.example.telegrampetbot.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Class for creating an entity - a Dog Photo object (dog images)
 */
@Entity
public class DogPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;

    @OneToOne
    private Dog dog;

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Dog getDog() {
        return dog;
    }

}
