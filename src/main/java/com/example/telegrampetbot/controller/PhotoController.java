package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.service.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PhotoController {

private final PhotoService photoService;
public PhotoController(PhotoService photoService){
    this.photoService=photoService;
}

    @Operation(summary = "Adding a photo",
            responses =
            @ApiResponse(
                    responseCode = "200",
                    description = "Adding a photo"
            )
    )
    @PostMapping(value = "/{petId}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhoto(@PathVariable Long petId, @RequestParam MultipartFile photo) throws IOException {
        photoService.uploadPhoto(petId, photo);
        return ResponseEntity.ok().build();
    }

}
