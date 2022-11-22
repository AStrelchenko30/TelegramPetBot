package com.example.telegrampetbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TelegramPetBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramPetBotApplication.class, args);
    }

}
