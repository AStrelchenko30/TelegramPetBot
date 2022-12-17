package com.example.telegrampetbot.service;

import com.example.telegrampetbot.controller.VolunteerController;
import com.example.telegrampetbot.model.Volunteer;
import com.example.telegrampetbot.repositories.VolunteerRepository;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = VolunteerService.class)
class VolunteerServiceTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private VolunteerRepository volunteerRepository;

    @SpyBean
    private VolunteerService volunteerService;

    @InjectMocks
    private VolunteerController volunteerController;

    @Test
    void createVolunteer() throws Exception {

        final long id = 1;
        final String name = "Ivan";
        final String surName = "Ivanov";
        final String mail = "mail";

        JSONObject volunteerObject = new JSONObject();
        volunteerObject.put("id", id);
        volunteerObject.put("name", name);
        volunteerObject.put("surname", surName);
        volunteerObject.put("mail", mail);


        Volunteer volunteer = new Volunteer(id, name, surName, mail);
        when(volunteerService.createVolunteer(any(Volunteer.class))).thenReturn(volunteer);
        when(volunteerService.updateVolunteer(any(Volunteer.class))).thenReturn(volunteer);
        when(volunteerService.findVolunteer(eq(id))).thenReturn(volunteer);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/volunteer")
                        .content(volunteerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.surname").value(surName))
                .andExpect(jsonPath("$.mail").value(mail));

    }
}