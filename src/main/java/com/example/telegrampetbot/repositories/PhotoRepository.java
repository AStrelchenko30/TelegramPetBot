package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for working with photo storage
 */
@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    /**
     * Photo search method by pet id
     * @param petId
     * @return object of class Photo
     */
    Optional<Photo> findByDogId(Long petId);
}
