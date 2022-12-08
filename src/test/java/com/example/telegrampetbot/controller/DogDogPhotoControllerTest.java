package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.DogPhoto;
import com.example.telegrampetbot.repositories.DogPhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DogDogPhotoControllerTest {

    @Mock
    private DogPhotoRepository dogPhotoRepository;



    @Test
    void uploadPhoto()  {
        DogPhoto newDogPhoto = Mockito.mock(DogPhoto.class);
        dogPhotoRepository.save(newDogPhoto);
        verify(dogPhotoRepository).save(newDogPhoto);
    }
    }

