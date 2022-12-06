package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.PhotoNotFoundException;
import com.example.telegrampetbot.model.Pet;
import com.example.telegrampetbot.model.Photo;
import com.example.telegrampetbot.repositories.PetRepository;
import com.example.telegrampetbot.repositories.PhotoRepository;
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
 * A class that stores methods for working with a photo of a pet
 */
@Service
@Transactional
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final PetRepository petRepository;

    public PhotoService(PhotoRepository photoRepository, PetRepository petRepository) {
        this.photoRepository = photoRepository;
        this.petRepository = petRepository;
    }

    @Value("/photo")
    private String photoDir;

    /**
     * Method for processing the incoming image
     *
     * @param petId
     * @param photoFile pet image file
     * @throws IOException
     */
    public void uploadPhoto(Long petId, MultipartFile photoFile) throws IOException {
        Pet pet = petRepository.getById(petId);
        Path filePath = Path.of(photoDir, pet + "." + getExtensions(Objects.requireNonNull(photoFile.getOriginalFilename())));
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
        Photo photo = findPhotoPet(petId);
        photo.setPet(pet);
        photo.setFilePath(filePath.toString());
        photo.setFileSize(photoFile.getSize());
        photo.setMediaType(photoFile.getContentType());
        photo.setData(photoFile.getBytes());
        photoRepository.save(photo);
    }

    public Photo findPhotoPet(Long petId) {
        if (photoRepository.findById(petId).isPresent()) {
            return photoRepository.findByPetId(petId).orElse(new Photo());
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
