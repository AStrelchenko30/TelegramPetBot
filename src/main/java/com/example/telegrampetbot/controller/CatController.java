package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.Cat;
import com.example.telegrampetbot.service.CatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Update Cat in Db
     *
     * @return updatedCat
     */
    @PutMapping
    public ResponseEntity<Cat> updateCat(@RequestBody Cat catNew) {
        Cat updatedCat = catService.updateCat(catNew);
        return ResponseEntity.ok(updatedCat);
    }

    /**
     * Find Cat in Db by ID
     *
     * @return Cat in BD
     */
    @GetMapping(params = {"id"})
    public ResponseEntity<Cat> findCat(@PathVariable Long id) {
        Cat findCat = catService.findCat(id);
        return ResponseEntity.ok(findCat);
    }

    /**
     * Find Cat in Db and delete
     *
     * @param id
     */
    @DeleteMapping(params = {"id"})
    public void deleteCat(@PathVariable Long id) {
        catService.deleteCat(id);
    }


}
