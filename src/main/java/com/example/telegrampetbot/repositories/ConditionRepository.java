package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.Client;
import com.example.telegrampetbot.model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Integer>{
   Condition findByChatId(Long chatId);
   Condition findByConditionForReport(Integer conditionForReport);
}
