package com.example.telegrampetbot.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Class for creating an entity - a Dog Photo object (dog images)
 */
@Entity
public class DogPhoto {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;

    @OneToOne
    private Dog dog;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getMediaType() {
        return mediaType;
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
