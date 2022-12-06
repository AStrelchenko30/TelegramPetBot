package com.example.telegrampetbot.controller;

import com.example.telegrampetbot.model.Volunteer;
import com.example.telegrampetbot.repositories.VolunteerRepository;
import com.example.telegrampetbot.service.VolunteerService;
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
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = VolunteerController.class)
class VolunteerControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private VolunteerRepository volunteerRepository;

    @SpyBean
    private VolunteerService volunteerService;

    @InjectMocks
    private VolunteerController volunteerController;

        @Test
        void createVolunteer()throws Exception {
            JSONObject volunteerObject=new JSONObject();
            volunteerObject.put("id", 1L);
            volunteerObject.put("name","Ivan");
            volunteerObject.put("surname","Ivanov");
            volunteerObject.put("mail","mail");

            Volunteer volunteer=new Volunteer();
            volunteer.setId(1L);
            volunteer.setName("Ivan");
            volunteer.setSurname("Ivanov");
            volunteer.setMail("mail");
            when(volunteerService.createVolunteer(any(Volunteer.class))).thenReturn(volunteer);

            mockMvc.perform(MockMvcRequestBuilders
                    .post("/volunteer")
                    .content(volunteerObject.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value("name"))
                    .andExpect(jsonPath("$.surname").value("surname"))
                    .andExpect(jsonPath("$.mail").value("mail"));


        }
}