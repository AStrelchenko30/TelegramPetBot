package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.DogPhoto;
import com.example.telegrampetbot.service.DogPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller for adding a dog photo to a separate database with a photo
 */
@RequestMapping("/dogPhoto")
@RestController
public class DogPhotoController {

    private final DogPhotoService dogPhotoService;

    public DogPhotoController(DogPhotoService dogPhotoService) {
        this.dogPhotoService = dogPhotoService;
    }
    @Operation(
            summary = "Нахождение фото собаки по id",
            responses =
            @ApiResponse(
                    responseCode = "200",
                    description = " Фото, найденное по id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Фото найдено по id"
            )
    )
    /**
     * Find DogPhoto in Db by ID
     *
     * @return DogPhoto in BD
     */
    @GetMapping(params = {"dogId"})
    public ResponseEntity<DogPhoto> findPhotoDog(@PathVariable Integer dogId) {
        DogPhoto findPhoto = dogPhotoService.findPhotoDog(dogId);
        return ResponseEntity.ok(findPhoto);
    }
}
