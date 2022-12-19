package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.DogNotFoundException;
import com.example.telegrampetbot.model.Dog;
import com.example.telegrampetbot.repositories.DogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogServiceTest {
    Dog newDog = new Dog(1L, "Gaf");

    @Mock
    private DogRepository dogRepository;
    @InjectMocks
    private DogService dogService;

    @Test
    void createDog() throws DogNotFoundException {
        when(dogRepository.save(newDog)).thenReturn(new Dog(1L, "Gaf"));
        assertEquals(dogService.createDog(newDog).getName(), newDog.getName());
    }

    @Test
    void updateDog() {
        when(dogRepository.save(newDog)).thenReturn(new Dog(1L, "Gaf"));
        assertEquals(dogService.updateDog(newDog).getName(), newDog.getName());
    }

    @Test
    void findDog() {
        when(dogRepository.findById(1L)).thenReturn(Optional.of(new Dog(1L, "Gaf")));
        assertEquals(dogService.findDog(1L).getName(), newDog.getName());
    }

    @Test
    void deleteDog() {
        dogService.deleteDog(isA(Long.class));
        verify(dogRepository,times(1)).deleteById(isA(Long.class));

    }
}