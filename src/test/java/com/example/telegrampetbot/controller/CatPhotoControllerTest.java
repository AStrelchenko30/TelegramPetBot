package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.Cat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CatPhotoControllerTest {
    Cat newCat= Mockito.mock(Cat.class);
    MultipartFile photo=Mockito.mock(MultipartFile.class);
    Long catId=newCat.getId();
    @Mock
    private CatPhotoController catPhotoController;

    @Test
    void uploadPhoto() throws IOException {
        catPhotoController.uploadPhoto(catId,photo);
        verify(catPhotoController).uploadPhoto(catId,photo);

    }

    @Test
    void findPhotoCat() {
        catPhotoController.findPhotoCat(catId);
        verify(catPhotoController).findPhotoCat(catId);
    }
}