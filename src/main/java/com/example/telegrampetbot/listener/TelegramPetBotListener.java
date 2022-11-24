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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramPetBotListener implements UpdatesListener {
    public ClientRepository clientRepository;
    public PetRepository petRepository;

    private final Pattern patternMessage = Pattern.compile("([1])");

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
            Matcher matcherMessage = patternMessage.matcher(message.text());
            if ("/start".equals(textM)) {
                SendMessage sendMessage = new SendMessage(chatId, "Приветсвуем Вас в наше телеграм боте");
                telegramBot.execute(sendMessage);
                SendMessage sendMenu = new SendMessage(chatId,
                        "Какую услугу вы хотите получить?" +
                                " \nНапишите цифру для ответа:" +
                                "\n1-Узнать информацию о приюте" +
                                "\n2-Как взять собаку из приюта" +
                                "\n3-Прислать отчет о питомце");
                telegramBot.execute(sendMenu);
            } else if (matcherMessage.matches()) {
                SendMessage receievedMenu1 = new SendMessage(chatId,
                            "Наш приют находится в г.Астана, здесь Вы сможете обрести себе друга - одного из наших чудесных подопечных. У каждой собаки есть паспорт со всеми необходимыми вакцинами");
                    telegramBot.execute(receievedMenu1);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

