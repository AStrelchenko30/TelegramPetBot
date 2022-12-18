package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.Dog;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    Optional <Dog> findById(Long id);


}
