package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.CatPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * Repository for working with photo storage
 */
@Repository
public interface CatPhotoRepository extends JpaRepository<CatPhoto, Long> {
    /**
     * Photo search method by pet id
     * @param catId
     * @return object of class Photo
     */

    Optional<CatPhoto> findByCatId(Long catId);
}
