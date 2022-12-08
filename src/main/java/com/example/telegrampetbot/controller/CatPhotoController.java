package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.CatPhoto;
import com.example.telegrampetbot.service.CatPhotoService;
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
 * Controller for adding a cat photo to a separate database with a photo
 */
@RestController
public class CatPhotoController {

    private final CatPhotoService catPhotoService;

    public CatPhotoController(CatPhotoService catPhotoService) {
        this.catPhotoService = catPhotoService;
    }

    @Operation(summary = "Добавление фото",
            responses =
            @ApiResponse(
                    responseCode = "200",
                    description = "Фото кота добавлено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            )
    )

    /**
     * Post request to create a photo
     */
    @PostMapping(value = "/{catId}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhoto(@Parameter(description = "Укажите ID кота") @PathVariable Long catId, @Parameter(description = "Загрузите фото кота") @RequestParam(name = "Фото кота") MultipartFile photo) throws IOException {
        catPhotoService.uploadPhoto(catId, photo);
        return ResponseEntity.ok().build();
    }
    /**
     * Find CatPhoto in Db by ID
     *
     * @return CatPhoto in BD
     */
    @GetMapping(params = {"catId"})
    public ResponseEntity<CatPhoto> findPhotoCat(@PathVariable Long catId){
        CatPhoto catPhoto=catPhotoService.findPhotoCat(catId);
        return ResponseEntity.ok(catPhoto);
    }

}
