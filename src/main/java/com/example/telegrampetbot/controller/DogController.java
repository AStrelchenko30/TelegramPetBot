package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.Dog;
import com.example.telegrampetbot.service.DogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for adding a pet to the database
 */
@RequestMapping("dog")
@RestController
public class DogController {
    public final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @Operation(
            summary = "Добавление данных о питомце",
            responses =
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные о питомце добавлены",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Добавление данных о питомце"
            )
    )

    /**
     * Post request to create a pet
     */
    @PostMapping
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        Dog createdDog = dogService.createDog(dog);
        return ResponseEntity.ok(createdDog);
    }
    /**
     * Update Dog in Db
     *
     * @return updatedDog
     */
    @PutMapping
    public ResponseEntity<Dog> updateDog(@RequestBody Dog dogNew) {
        Dog updatedDog = dogService.updateDog(dogNew);
        return ResponseEntity.ok(updatedDog);
    }
    /**
     * Find Dog in Db by ID
     *
     * @return Dog in BD
     */
    @GetMapping(params = {"id"})
    public ResponseEntity<Dog> findDog(@PathVariable Long id) {
        Dog findDog = dogService.findDog(id);
        return ResponseEntity.ok(findDog);
    }

    /**
     * Find Dog in Db and delete
     * @param id
     */
    @DeleteMapping(params = {"id"})
    public void deleteDog(@PathVariable Long id) {
        dogService.deleteDog(id);
    }

}
