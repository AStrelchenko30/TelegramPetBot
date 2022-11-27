package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.service.PhotoService;
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
 * Controller for adding a pet photo to a separate database with a photo
 */
@RestController
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Operation(summary = "Добавление фото",
            responses =
            @ApiResponse(
                    responseCode = "200",
                    description = "Фото питомца добавлено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            )
    )

    /**
     * Post request to create a photo
     */
    @PostMapping(value = "/{petId}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhoto(@Parameter(description = "Укажите ID питомца") @PathVariable Long petId, @Parameter(description = "Загрузите фото питомца") @RequestParam(name = "Фото питомца") MultipartFile photo) throws IOException {
        photoService.uploadPhoto(petId, photo);
        return ResponseEntity.ok().build();
    }

}
