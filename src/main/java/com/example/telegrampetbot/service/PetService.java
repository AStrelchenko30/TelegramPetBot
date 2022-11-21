package com.example.telegrampetbot.service;

import com.example.telegrampetbot.model.Pet;
import com.example.telegrampetbot.repositories.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet createPet(Pet pet) {
        logger.info("createPet method used in PetService");
        return petRepository.save(pet);
    }

    public Pet updatePet(Pet petNew) {
        logger.info("updatePet method used in PetService");
        Pet petOld = petRepository.findById(petNew.getId()).get();
        petOld.setName(petNew.getName());
        petOld.setOwnerId(petNew.getOwnerId());
        return petRepository.save(petOld);
    }

    public Pet findPet(Long id) {
        logger.info("findPet method used in PetService");
        return petRepository.findById(id).get();
    }

    public void deletePet(Long id) {
        logger.info("deletePet method used in PetService");
        petRepository.deleteById(id);
    }
}