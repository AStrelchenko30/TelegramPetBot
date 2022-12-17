package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.CatNotFoundException;
import com.example.telegrampetbot.model.Cat;
import com.example.telegrampetbot.repositories.CatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(service.createCat(cat)).thenReturn((new Cat(1L, "Mur")));
        Cat actualCat = new Cat(1L, "Mur");
        assertEquals(service.createCat(cat).getName(), actualCat.getName());

    }


    @Test
    void updateCat() {
        when(service.updateCat(cat)).thenReturn((new Cat(1L, "Mur")));
        Cat actualCat = new Cat(1L, "Mur");
        assertEquals(service.updateCat(cat).getName(), actualCat.getName());
        verify(catRepository).save(cat);
    }

    @Test
    void findCat() {
        when(service.findCat(1L)).thenReturn((new Cat(1L, "Mur")));
        assertEquals(cat.getName(), service.findCat(1L).getName());

    }

    @Test
    void deleteCat() {
        service.deleteCat(isA(Long.class));
        verify(service, times(1)).deleteCat(isA(Long.class));

    }
}