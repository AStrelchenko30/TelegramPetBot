package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.CatNotFoundException;
import com.example.telegrampetbot.model.Cat;
import com.example.telegrampetbot.repositories.CatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CatService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public Cat createCat(Cat cat) {
        logger.info("createPet method used in CatService");
        if(!catRepository.findAll().contains(cat)) {
            return catRepository.save(cat);
        }
        throw new CatNotFoundException();
    }

    public Cat updateCat(Cat catNew) {
        logger.info("updatePet method used in PetService");
        if (catRepository.findById(catNew.getId()).isPresent()) {
            Cat catOld = catRepository.findById(catNew.getId()).get();
            catOld.setName(catNew.getName());
            catOld.setOwner(catNew.getOwner());
            return catRepository.save(catOld);
        }
        throw new CatNotFoundException();
    }

    public Cat findCat(Long id) {
        logger.info("findCat method used in CatService");
        if (catRepository.findById(id).isPresent()) {
            return catRepository.findById(id).get();
        }
        throw new CatNotFoundException();
    }

    public void deleteCat(Long id) {
        logger.info("deleteCat method used in CatService");
        if (catRepository.findById(id).isPresent()) {
            catRepository.deleteById(id);
        }
        throw new CatNotFoundException();
    }
}