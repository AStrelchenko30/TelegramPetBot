package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.Cat;
import com.example.telegrampetbot.service.CatService;
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
 * Controller class for adding a cat to the database
 */
@RequestMapping("cat")
@RestController
public class CatController {
    public final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @Operation(
            summary = "Добавление данных о cat",
            responses =
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные о cat добавлены",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Добавление данных о cat"
            )
    )

    /**
     * Post request to create a cat
     */
    @PostMapping
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
        Cat createdCat = catService.createCat(cat);
        return ResponseEntity.ok(createdCat);
    }
}
