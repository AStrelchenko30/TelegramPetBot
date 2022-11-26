package com.example.telegrampetbot.service;

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

    public void uploadPhoto(Long petId, MultipartFile photoFile) throws IOException {
        Pet pet = petRepository.getById(petId);
        Path filePath = Path.of(photoDir, pet + "." + getExtensions(Objects.requireNonNull(photoFile.getOriginalFilename())));
//        Path filePath = Path.of(photoDir, pet + "." + getExtensions(photoFile.getOriginalFilename()));
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
        return photoRepository.findByPetId(petId).orElse(new Photo());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
