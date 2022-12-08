package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.PhotoNotFoundException;
import com.example.telegrampetbot.model.Dog;
import com.example.telegrampetbot.model.DogPhoto;
import com.example.telegrampetbot.repositories.DogRepository;
import com.example.telegrampetbot.repositories.DogPhotoRepository;
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
 * A class that stores methods for working with a photo of a dog
 */
@Service
@Transactional
public class DogPhotoService {
    private final DogPhotoRepository dogPhotoRepository;
    private final DogRepository dogRepository;

    public DogPhotoService(DogPhotoRepository dogPhotoRepository, DogRepository dogRepository) {
        this.dogPhotoRepository = dogPhotoRepository;
        this.dogRepository = dogRepository;
    }

    @Value("/photo")
    private String photoDir;

    /**
     * Method for processing the incoming image
     *
     * @param dogId
     * @param photoFile dog image file
     * @throws IOException
     */
    public void uploadPhoto(Long dogId, MultipartFile photoFile) throws IOException {
        Dog dog = dogRepository.getById(dogId);
        Path filePath = Path.of(photoDir, dog + "." + getExtensions(Objects.requireNonNull(photoFile.getOriginalFilename())));
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
        DogPhoto dogPhoto = findPhotoDog(dogId);
        dogPhoto.setDog(dog);
        dogPhoto.setFilePath(filePath.toString());
        dogPhoto.setFileSize(photoFile.getSize());
        dogPhoto.setMediaType(photoFile.getContentType());
        dogPhoto.setData(photoFile.getBytes());
        dogPhotoRepository.save(dogPhoto);
    }

    public DogPhoto findPhotoDog(Long dogId) {
        if (dogPhotoRepository.findById(dogId).isPresent()) {
            return dogPhotoRepository.findByDogId(dogId).orElse(new DogPhoto());
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
