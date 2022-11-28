package com.example.telegrampetbot.model;

import javax.persistence.*;

@Entity
public class Report {
    @Id
    @GeneratedValue
    private Long id;
    private Long ClientId;
    private String condition;
    private String ration;
    private String changes;

    private String filePath;
    private Long fileSize;
    private String mediaType;
    private byte[] data;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ClientId", referencedColumnName = "id")
    private Client client;


    public Report(){
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return ClientId;
    }
    public void setClientId(Long clientId) {
        ClientId = clientId;
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
