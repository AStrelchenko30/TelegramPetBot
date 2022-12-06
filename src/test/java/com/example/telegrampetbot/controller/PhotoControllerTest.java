package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.Photo;
import com.example.telegrampetbot.repositories.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class PhotoControllerTest {

    @Mock
    private PhotoRepository photoRepository;



    @Test
    void uploadPhoto()  {
        Photo newPhoto= Mockito.mock(Photo.class);
        photoRepository.save(newPhoto);
        verify(photoRepository).save(newPhoto);
    }
    }

