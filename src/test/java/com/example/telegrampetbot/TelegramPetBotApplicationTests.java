package com.example.telegrampetbot;

import com.example.telegrampetbot.controller.DogController;
import com.example.telegrampetbot.controller.PhotoController;
import com.example.telegrampetbot.controller.VolunteerController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TelegramPetBotApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private DogController dogController;
    @Autowired
    private PhotoController photoController;
    @Autowired
    private VolunteerController volunteerController;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void contextLoads() throws Exception{
        Assertions.assertThat(dogController).isNotNull();
        Assertions.assertThat(photoController).isNotNull();
        Assertions.assertThat(volunteerController).isNotNull();
    }


}
