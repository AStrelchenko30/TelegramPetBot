package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {


}
