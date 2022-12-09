package com.example.telegrampetbot.listener;

import com.example.telegrampetbot.model.Client;
import com.example.telegrampetbot.repositories.ClientRepository;
import com.example.telegrampetbot.repositories.DogRepository;
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

/**
 * Message handling class
 */
@Service
public class TelegramPetBotListener implements UpdatesListener {
    public ClientRepository clientRepository;

    public DogRepository dogRepository;
    /**
     * Search rule pattern
     */
    private final Pattern patternMessage = Pattern.compile("(^[0-9]{11})(\\s)([a-zA-Z]+$)");
    @Autowired
    private final TelegramBot telegramBot;

    public TelegramPetBotListener(ClientRepository clientRepository, DogRepository dogRepository, TelegramBot telegramBot) {
        this.clientRepository = clientRepository;
        this.dogRepository = dogRepository;
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Processing user messages
     *
     * @param updates
     * @return id of the last processed message
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Message message = update.message();
            Long chatId = update.message().chat().id();
            String textM = message.text();
            Matcher matcherMessage = patternMessage.matcher(message.text());

            if ("/start".equals(textM)) {
                SendMessage sendMenuPet = new SendMessage(chatId,
                        "Приветствуем Вас в нашем телеграм-боте." +
                                "\n Для выбора приюта для собак, введите dog" +
                                "\n Для выбора приюта для кошек, введите cat");
                telegramBot.execute(sendMenuPet);
            }
            if ("dog".equals(textM)) {
                SendMessage sendMenu = new SendMessage(chatId,
                        "Какую услугу вы хотите получить?" +
                                " \nНапишите цифру для ответа:" +
                                "\n1-Узнать информацию о приюте" +
                                "\n2-Как взять собаку из приюта" +
                                "\n3-Прислать отчет о питомце");
                telegramBot.execute(sendMenu);
                switch (textM) {
                    case "1" -> {
                        SendMessage sendMessage1 = new SendMessage(chatId, "Наш приют 'Лапа' находится в г. Астана, ул. Аккорган, 5Б. Работаем ежедневно 10:00-17:00." +
                                "Здесь Вы сможете обрести себе друга - одного из наших чудесных" +
                                "подопечных. У каждой собаки есть паспорт со всеми необходимыми вакцинами. " +
                                "Вы можете оставить нам свои контактные данные для связи. Укажите Ваш телефон и имя " +
                                "в формате: 81234567788 name");
                        telegramBot.execute(sendMessage1);
                    }
                    case "2" -> {
                        SendMessage receievedMenuDog2 = new SendMessage(chatId,
                                "Со всеми, кто желает взять собаку из приюта, наши волонтеры проводят личные встречи. " +
                                        "После чего Вы выбираете питомца и забираете его к себе домой. В дальнейшем необходимо " +
                                        "будет отправлять отчеты о состоянии собаки.");
                        telegramBot.execute(receievedMenuDog2);
                    }
                    case "3" -> {
                        SendMessage receievedMenuDog3 = new SendMessage(chatId,
                                "Отправьте, пожалуйста, отчет в следуещем формате:" +
                                        "в первом сообщении фото" +
                                        "второе сообщение-условия" +
                                        "третье сообщение-рацион" +
                                        "четвертое сообщение-изменения");
                        telegramBot.execute(receievedMenuDog3);
                    }
                }

            } else if ("cat".equals(textM)) {
                SendMessage sendMenuCat = new SendMessage(chatId,
                        "Какую услугу вы хотите получить?" +
                                " \nНапишите цифру для ответа:" +
                                "\n1-Узнать информацию о приюте" +
                                "\n2-Как взять кошку из приюта" +
                                "\n3-Прислать отчет о питомце");
                telegramBot.execute(sendMenuCat);
                switch (textM) {
                    case "1" -> {
                        SendMessage sendMessage1 = new SendMessage(chatId, "Наш приют 'Лапа' находится в г. Астана, ул. Аккорган, 5В. Работаем ежедневно 11:00-18:00." +
                                "Здесь Вы сможете обрести себе друга - одного из наших чудесных" +
                                "подопечных. У каждой кошки есть паспорт со всеми необходимыми вакцинами. " +
                                "Вы можете оставить нам свои контактные данные для связи. Укажите Ваш телефон и имя " +
                                "в формате: 81234567788 name");
                        telegramBot.execute(sendMessage1);
                    }
                    case "2" -> {
                        SendMessage receievedMenuCat2 = new SendMessage(chatId,
                                "Со всеми, кто желает взять собаку из приюта, наши волонтеры проводят личные встречи. " +
                                        "После чего Вы выбираете питомца и забираете его к себе домой. В дальнейшем необходимо " +
                                        "будет отправлять отчеты о состоянии кошки.");
                        telegramBot.execute(receievedMenuCat2);
                    }
                    case "3" -> {
                        SendMessage receievedMenuCat3 = new SendMessage(chatId,
                                "Отправьте, пожалуйста, отчет в следуещем формате:" +
                                        "в первом сообщении фото" +
                                        "второе сообщение-условия" +
                                        "третье сообщение-рацион" +
                                        "четвертое сообщение-изменения");
                        telegramBot.execute(receievedMenuCat3);
                    }
                }
            }
            if (matcherMessage.matches()) {
                String phone = matcherMessage.group(1);
                String name = matcherMessage.group(3);
                Client client = new Client(chatId, phone, name, 0L, " ");
                clientRepository.save(client);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

