package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.Volunteer;
import com.example.telegrampetbot.service.VolunteerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller class for adding a volunteer to the database
 */
@RequestMapping("volunteer")
@RestController
public class VolunteerController {
    public final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Operation(
            summary = "Добавление данных волонтера",
            responses =
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные о волонтере добавлены",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Добавление данных о волонтере"
            )
    )

    /**
     * Post request to create a volunteer
     */
    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer createdVolunteer = volunteerService.createVolunteer(volunteer);
        return ResponseEntity.ok(createdVolunteer);
    }

    @Operation(
            summary = "Изменение данных о волонтере",
            responses =
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные о волонтере изменены",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Изменение данных о волонтере"
            )
    )
    /**
     * Update Volunteer in Db
     *
     * @return updatedVolunteer
     */
    @PutMapping
    public ResponseEntity<Volunteer> updateVolunteer(@RequestBody Volunteer volunteerNew) {
        Volunteer updatedVolunteer = volunteerService.updateVolunteer(volunteerNew);
        return ResponseEntity.ok(updatedVolunteer);
    }

    @Operation(
            summary = "Нахождение волонтера по id",
            responses =
            @ApiResponse(
                    responseCode = "200",
                    description = " волонтер, найденный по id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "волонтер найден по id"
            )
    )
    /**
     * Find Volunteer in Db by ID
     *
     * @return Volunteer in BD
     */
    @GetMapping(params = {"id"})
    public ResponseEntity<Volunteer> findVolunteer(@PathVariable Long id) {
        Volunteer findVolunteer = volunteerService.findVolunteer(id);
        return ResponseEntity.ok(findVolunteer);
    }

    @Operation(
            summary = "Удаление волонтера по id",
            responses =
            @ApiResponse(
                    responseCode = "200",
                    description = " Волонтер, удаленный по id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Волонтер удален по id"
            )
    )
    /**
     * Find Volunteer in Db and delete
     *
     * @param id
     */
    @DeleteMapping(params = {"id"})
    public void deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
    }
}
