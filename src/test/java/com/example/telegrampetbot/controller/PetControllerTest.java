package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.Pet;
import com.example.telegrampetbot.repositories.PetRepository;
import com.example.telegrampetbot.service.PetService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PetRepository petRepository;
    @SpyBean
    private PetService petService;

    @Test
    void createPet() throws Exception {
        JSONObject petObject = new JSONObject();
        petObject.put("id", 1L);
        petObject.put("name", "Ivan");


        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Ivan");
        when(petService.createPet(any(Pet.class))).thenReturn(pet);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/pet")
                        .content(petObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("id"))
                .andExpect(jsonPath("$.name").value("name"));
    }


}