package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.Dog;
import com.example.telegrampetbot.service.DogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogRepositoryTest {
    Dog newDog = new Dog(1L, "Gaf");

    @Mock
    private DogRepository dogRepository;
    @InjectMocks
    private DogService dogService;

    @Test
    void createDog() {
        when(dogRepository.createDog(newDog)).thenReturn(new Dog(1L, "Gaf"));
        assertEquals(dogRepository.createDog(newDog).getName(), newDog.getName());
    }

    @Test
    void updateDog() {
        when(dogRepository.save(newDog)).thenReturn(new Dog(1L, "Gaf"));
        assertEquals(dogRepository.save(newDog).getName(), newDog.getName());
    }

    @Test
    void findDog() {
        when(dogRepository.findDog(1L)).thenReturn(new Dog(1L, "Gaf"));
        assertEquals(dogRepository.findDog(1L).getName(), newDog.getName());
    }

    @Test
    void deleteDog() {
        dogService.updateDog(new Dog(1L, "Gaf"));
        dogRepository.save(new Dog(1L, "Gaf"));
        dogService.deleteDog(1L);
        verify(dogRepository).deleteById(1L);
    }
}