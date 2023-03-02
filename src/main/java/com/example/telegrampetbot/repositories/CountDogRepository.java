package com.example.telegrampetbot.repositories;
import com.example.telegrampetbot.model.CountDog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountDogRepository extends JpaRepository<CountDog, Integer>{
   CountDog findByChatId(Long chatId);
   CountDog findByConditionForReport(Integer conditionForReport);
}
