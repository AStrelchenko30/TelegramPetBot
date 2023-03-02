package com.example.telegrampetbot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CountDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long chatId;

    public Integer getConditionForReport() {
        return conditionForReport;
    }

    public void setConditionForReport(Integer conditionForReport) {
        this.conditionForReport = conditionForReport;
    }

    private Integer conditionForReport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChat_id(Long chatId) {
        this.chatId = chatId;
    }

    public CountDog(Integer id, Long chatId, Integer conditionForReport) {
        this.chatId = chatId;
        this.id = id;
        this.conditionForReport=conditionForReport;
    }


    public CountDog() {
    }
}
