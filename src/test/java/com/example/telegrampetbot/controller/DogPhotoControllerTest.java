package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.Dog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DogPhotoControllerTest {
    Dog newDog = Mockito.mock(Dog.class);
    MultipartFile photo=Mockito.mock(MultipartFile.class);
    Long dogId=newDog.getId();


    @Mock
    private DogPhotoController dogPhotoController;


    @Test
    void tryUploadPhoto() throws IOException {
        dogPhotoController.uploadPhoto(dogId,photo);
        verify(dogPhotoController).uploadPhoto(dogId,photo);
    }
    @Test
    void tryFindPhoto(){
        dogPhotoController.findPhotoDog(dogId);
        verify(dogPhotoController).findPhotoDog(dogId);
    }
    }

