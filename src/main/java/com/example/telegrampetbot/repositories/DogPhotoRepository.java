package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.DogPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for working with photo storage
 */
@Repository
public interface DogPhotoRepository extends JpaRepository<DogPhoto, Long> {
    /**
     * Photo search method by pet id
     * @param dogId
     * @return object of class Photo
     */
    Optional<DogPhoto> findByDogId(Long dogId);
}
