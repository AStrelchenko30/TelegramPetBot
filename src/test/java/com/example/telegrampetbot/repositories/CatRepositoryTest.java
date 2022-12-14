package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.Cat;
import com.example.telegrampetbot.service.CatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatRepositoryTest {
    Cat cat = new Cat(1L, "Mur");
    Cat newCat=new Cat(2L,"Mura1");

    @Mock
    private CatRepository catRepository;
    @InjectMocks
    private CatService service;

    @Test
    void createCat() {
        when(catRepository.createCat(cat)).thenReturn(Optional.of(new Cat(1L, "Mur")));
        Cat actualCat = new Cat(1L, "Mur");
        assertEquals(catRepository.createCat(cat).get().getName(), actualCat.getName());
    }

    @Test
    void updateCat() {
        catRepository.findById(newCat.getId());
        verify(catRepository).findById(newCat.getId());
        
    }

    @Test
    void findCat() {
        when(catRepository.findById(1L)).thenReturn(Optional.of(new Cat(1L, "Mur")));
        assertEquals(catRepository.findById(1L).get().getName(),cat.getName());
    }

    @Test
    void deleteCat() {
        catRepository.save(new Cat(2L,"Mura1"));
        catRepository.deleteById(2L);
        verify(catRepository).deleteById(2L);
    }
}