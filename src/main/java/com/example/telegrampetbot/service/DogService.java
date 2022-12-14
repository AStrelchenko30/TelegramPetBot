package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.DogNotFoundException;
import com.example.telegrampetbot.model.Dog;
import com.example.telegrampetbot.repositories.DogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DogService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    /**
     * Создание нового питомца в БД приюта
     * Используется метод {@link JpaRepository#save(Object)}
     *
     * @param dog новый питомец
     * @return создание питомца
     */
    public Dog createDog(Dog dog) {
        logger.info("createPet method used in PetService");
        if (!dogRepository.findAll().contains(dog)) {
            return dogRepository.save(dog);
        }
        throw new DogNotFoundException();
    }

    /**
     * Добавление нового питомца в БД приюта
     * Используется метод {@link JpaRepository#save(Object)}
     *
     * @param dogNew новый питомец
     * @return добавленного питомца
     */

    public Dog updateDog(Dog dogNew) {
        logger.info("updatePet method used in PetService");
        if (dogRepository.findById(dogNew.getId()).isPresent()) {
            Dog dogOld = dogRepository.findById(dogNew.getId()).get();
            dogOld.setName(dogNew.getName());
            dogOld.setOwner(dogNew.getOwner());
            return dogRepository.save(dogOld);
        }
        throw new DogNotFoundException();
    }

    /**
     * Нахождение питомца по идентификатору в БД приюта
     * Используется метод {@link JpaRepository#findById(Object)}
     *
     * @param id идентификатор нужного питомца
     * @return найденный питомец
     */

    public Dog findDog(Long id) {
        logger.info("findPet method used in PetService");
        if (dogRepository.findById(id).isPresent()) {
            return dogRepository.findById(id).get();
        }
        throw new DogNotFoundException();
    }

    /**
     * Нахождение питомца по идентификатору и удаление его из БД приюта
     * Используется метод {@link JpaRepository#deleteById(Object)}
     *
     * @param id идентификатор нужного питомца
     */

    public void deleteDog(Long id) {
        logger.info("deletePet method used in PetService");
        if (dogRepository.findById(id).isPresent()) {
            dogRepository.deleteById(id);
        }
        throw new DogNotFoundException();
    }
}