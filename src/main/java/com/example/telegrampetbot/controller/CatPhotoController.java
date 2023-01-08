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
@RequestMapping("/catPhoto")
@RestController
public class CatPhotoController {

    private final CatPhotoService catPhotoService;

    public CatPhotoController(CatPhotoService catPhotoService) {
        this.catPhotoService = catPhotoService;
    }

    @Operation(
            summary = "Нахождение фото кошки по id",
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
     * Find CatPhoto in Db by ID
     *
     * @return CatPhoto in BD
     */
    @GetMapping(params = {"catId"})
    public ResponseEntity<CatPhoto> findPhotoCat(@PathVariable Long catId) {
        CatPhoto catPhoto = catPhotoService.findPhotoCat(catId);
        return ResponseEntity.ok(catPhoto);
    }
}
