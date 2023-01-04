package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.PhotoNotFoundException;
import com.example.telegrampetbot.model.Dog;
import com.example.telegrampetbot.model.DogPhoto;
import com.example.telegrampetbot.repositories.DogPhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DogPhotoServiceTest {
    Dog newDog = Mockito.mock(Dog.class);
    MultipartFile photo=Mockito.mock(MultipartFile.class);
    Integer dogId=newDog.getId();
    DogPhoto dogPhoto=mock(DogPhoto.class);


    @Mock
    private DogPhotoRepository repository;

    @InjectMocks
    private DogPhotoService service;


    @Test
    void uploadPhoto() throws IOException {
        service.uploadPhoto(dogId,photo);
        verify(repository).save(dogPhoto);
    }
    @Test
    void tryFindPhoto() throws PhotoNotFoundException {
        service.findPhotoDog(dogId);
        verify(repository).findByDogId(dogId);
    }
    @Test
    void getExtensions(){
        //как проверить его - не имею понятия
    }
    }

