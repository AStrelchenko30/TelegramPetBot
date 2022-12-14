package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
//    Optional<Cat> createCat(Cat cat);
//    Cat updateCat(Cat catNew);
    Optional<Cat> findById(Long id);

    void deleteById(Long id);

}
