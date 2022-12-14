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
    public ResponseEntity<DogPhoto> findPhotoDog(@PathVariable Long dogId) {
        DogPhoto findPhoto = dogPhotoService.findPhotoDog(dogId);
        return ResponseEntity.ok(findPhoto);
    }
}
