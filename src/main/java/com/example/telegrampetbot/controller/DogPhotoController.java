package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.service.DogPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller for adding a dog photo to a separate database with a photo
 */
@RestController
public class DogPhotoController {

    private final DogPhotoService dogPhotoService;

    public DogPhotoController(DogPhotoService dogPhotoService) {
        this.dogPhotoService = dogPhotoService;
    }

    @Operation(summary = "Добавление фото",
            responses =
            @ApiResponse(
                    responseCode = "200",
                    description = "Фото собаки добавлено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            )
    )

    /**
     * Post request to create a photo
     */
    @PostMapping(value = "/{dogId}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhoto(@Parameter(description = "Укажите ID собаки") @PathVariable Long dogId, @Parameter(description = "Загрузите фото собаки") @RequestParam(name = "Фото собаки") MultipartFile photo) throws IOException {
        dogPhotoService.uploadPhoto(dogId, photo);
        return ResponseEntity.ok().build();
    }

}
