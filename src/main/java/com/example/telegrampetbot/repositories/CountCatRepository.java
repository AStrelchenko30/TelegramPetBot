package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.CountCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountCatRepository extends JpaRepository<CountCat, Integer> {
    CountCat findByChatId(Long chatId);
    CountCat findByConditionForReport(Integer conditionForReport);
}
