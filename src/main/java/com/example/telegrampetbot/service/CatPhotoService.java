package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.PhotoNotFoundException;
import com.example.telegrampetbot.model.Cat;
import com.example.telegrampetbot.model.CatPhoto;
import com.example.telegrampetbot.repositories.CatRepository;
import com.example.telegrampetbot.repositories.CatPhotoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * A class that stores methods for working with a photo of a cat
 */
@Service
@Transactional
public class CatPhotoService {
    private final CatPhotoRepository catPhotoRepository;
    private final CatRepository catRepository;

    public CatPhotoService(CatPhotoRepository catPhotoRepository, CatRepository catRepository) {
        this.catPhotoRepository = catPhotoRepository;
        this.catRepository = catRepository;
    }

    @Value("/photo")
    private String photoDir;

    /**
     * Method for processing the incoming image
     *
     * @param catId
     * @param photoFile cat image file
     * @throws IOException
     */
    public void uploadPhoto(Long catId, MultipartFile photoFile) throws IOException {
        Cat cat = catRepository.getById(catId);
        Path filePath = Path.of(photoDir, cat + "." + getExtensions(Objects.requireNonNull(photoFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = photoFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        CatPhoto catPhoto = findPhotoPet(catId);
        catPhoto.setCat(cat);
        catPhoto.setFilePath(filePath.toString());
        catPhoto.setFileSize(photoFile.getSize());
        catPhoto.setMediaType(photoFile.getContentType());
        catPhoto.setData(photoFile.getBytes());
        catPhotoRepository.save(catPhoto);
    }

    public CatPhoto findPhotoPet(Long petId) {
        if (catPhotoRepository.findById(petId).isPresent()) {
            return catPhotoRepository.findByCatId(petId).orElse(new CatPhoto());
        }
        throw new PhotoNotFoundException();
    }

    /**
     * Returns the name of the image file
     *
     * @param fileName
     * @return
     */
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
