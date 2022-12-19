package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.CatNotFoundException;
import com.example.telegrampetbot.model.Cat;
import com.example.telegrampetbot.repositories.CatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatServiceTest {
    Cat cat = new Cat(1L, "Mur");


    @Mock
    private CatRepository catRepository;
    @InjectMocks
    private CatService service;

    @Test
    void createCat() throws CatNotFoundException {
        when(catRepository.save(cat)).thenReturn((new Cat(1L, "Mur")));
        assertEquals(service.createCat(cat).getName(), cat.getName());

    }


    @Test
    void updateCat() {
        when(catRepository.save(cat)).thenReturn((new Cat(1L, "Mur")));
        assertEquals(service.updateCat(cat).getName(), cat.getName());
    }

    @Test
    void findCat() {
        when(catRepository.findById(1L)).thenReturn(Optional.of((new Cat(1L, "Mur"))));
        assertEquals(service.findCat(1L).getName(),cat.getName());

    }

    @Test
    void deleteCat() {
        service.deleteCat(1L);
        verify(catRepository).deleteById(1L);

    }
}