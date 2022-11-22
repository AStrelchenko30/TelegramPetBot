package com.example.telegrampetbot.listener;

import com.example.telegrampetbot.repositories.ClientRepository;
import com.example.telegrampetbot.repositories.PetRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;

import java.util.List;

@Service
public class TelegramPetBotListener implements UpdatesListener  {
    public ClientRepository clientRepository;
    public PetRepository petRepository;

    @Autowired
    private final TelegramBot telegramBot;

    public TelegramPetBotListener(ClientRepository clientRepository, PetRepository petRepository, TelegramBot telegramBot) {
        this.clientRepository = clientRepository;
        this.petRepository = petRepository;
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }


    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Message message = update.message();
            Long chatId = update.message().chat().id();
            String textM = message.text();

            if ("/start".equals(textM)) {
                SendMessage sendMessage = new SendMessage(chatId, "Приветсвуем Вас в наше телеграм боте");
                telegramBot.execute(sendMessage);

            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

