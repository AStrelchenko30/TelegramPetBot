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
    private Long ownerId;
    private String filePath;
    private Long fileSize;
    private String mediaType;

    private byte[] data;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="ownerId", insertable = false, updatable = false)
    private Client client;


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


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
