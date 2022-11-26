package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.Pet;
import com.example.telegrampetbot.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Optional<Photo> findByPetId(Long petId);
}
