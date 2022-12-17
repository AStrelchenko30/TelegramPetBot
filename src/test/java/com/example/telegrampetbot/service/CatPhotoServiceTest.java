package com.example.telegrampetbot.service;

import com.example.telegrampetbot.model.Cat;
import com.example.telegrampetbot.model.CatPhoto;
import com.example.telegrampetbot.repositories.CatPhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatPhotoServiceTest {
    Cat newCat = mock(Cat.class);
    CatPhoto newCatPhoto=mock(CatPhoto.class);
    MultipartFile photo = mock(MultipartFile.class);
    Long catId = 1L;

    @Mock
    private CatPhotoRepository catPhotoRepository;

    @InjectMocks
    private CatPhotoService catPhotoService;

    @Test
    void uploadPhoto() throws IOException {
        catPhotoService.uploadPhoto(catId, photo);
        verify(catPhotoService).uploadPhoto(catId, photo);

    }

    @Test
    void findPhotoCat() {
        when(catPhotoRepository.findByCatId(catId)).thenReturn(Optional.ofNullable(newCatPhoto));
        final CatPhoto actual = catPhotoService.findPhotoCat(catId);
        assertEquals(newCat, actual);
        verify(catPhotoService).findPhotoCat(catId);
    }
}