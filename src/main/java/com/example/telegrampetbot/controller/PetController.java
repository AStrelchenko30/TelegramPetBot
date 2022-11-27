package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.Pet;
import com.example.telegrampetbot.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for adding a pet to the database
 */
@RequestMapping("pet")
@RestController
public class PetController {
    public final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
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
    public ResponseEntity<Pet> createStudent(@RequestBody Pet pet) {
        Pet createdPet = petService.createPet(pet);
        return ResponseEntity.ok(createdPet);
    }
}
