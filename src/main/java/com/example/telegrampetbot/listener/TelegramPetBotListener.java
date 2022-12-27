package com.example.telegrampetbot.listener;

import com.example.telegrampetbot.model.Client;
import com.example.telegrampetbot.model.DogPhoto;
import com.example.telegrampetbot.model.DogReport;
import com.example.telegrampetbot.repositories.ClientRepository;
import com.example.telegrampetbot.repositories.DogPhotoRepository;
import com.example.telegrampetbot.repositories.DogReportRepository;
import com.example.telegrampetbot.repositories.DogRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public DogPhotoRepository dogPhotoRepository;
    public DogReportRepository dogReportRepository;
    /**
     * Search rule pattern
     */
    private final Pattern patternMessage = Pattern.compile("(^[0-9]{11})(\\s)([a-zA-Z]+$)");
    private final Logger logger = LoggerFactory.getLogger(TelegramPetBotListener.class);
    @Autowired
    private final TelegramBot telegramBot;

    public TelegramPetBotListener(ClientRepository clientRepository, DogRepository dogRepository, TelegramBot telegramBot, DogReportRepository dogReportRepository, DogPhotoRepository dogPhotoRepository) {
        this.clientRepository = clientRepository;
        this.dogRepository = dogRepository;
        this.telegramBot = telegramBot;
        this.dogReportRepository = dogReportRepository;
        this.dogPhotoRepository = dogPhotoRepository;
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
            logger.info("Processing update: {}", update);
            Message message = update.message();
            Long chatId = update.message().chat().id();
            String textM = message.text();
            String surName = update.message().from().lastName();

            DogPhoto dogPhoto = new DogPhoto();
            DogReport dogReport = new DogReport();

            if (message != null) {

                if (message.document().mimeType().equals("image"))
                {
                    String photoId = update.message().document().fileId();
                    String photoType = update.message().document().mimeType();
                    int photoSize = update.message().document().fileSize();

                    dogPhoto.setFileSize(photoSize);
                    dogPhoto.setFilePath(photoId);
                    dogPhoto.setMediaType(photoType);
                    dogPhotoRepository.save(dogPhoto);
            }

                Matcher matcherMessage = patternMessage.matcher(textM);
                if (matcherMessage.matches()) {
                    String phone = matcherMessage.group(1);
                    String name = matcherMessage.group(3);
                    Client client = new Client(chatId, phone, name, 0L, surName);
                    clientRepository.save(client);
                }

                switch (textM) {
                    case "/start" -> {
                        SendMessage sendMenuPet = new SendMessage(chatId,
                                "Приветствуем Вас в нашем телеграм-боте." +
                                        "\n Для выбора приюта для собак, введите dog" +
                                        "\n Для выбора приюта для кошек, введите cat");
                        telegramBot.execute(sendMenuPet);
                    }
                    case "dog" -> {
                        SendMessage sendMenu = new SendMessage(chatId,
                                "Какую услугу вы хотите получить?" +
                                        "\nНапишите нужное для ответа:" +
                                        "\n1d-Узнать информацию о приюте" +
                                        "\n2d-Как взять собаку из приюта" +
                                        "\n3d-Прислать отчет о питомце");
                        telegramBot.execute(sendMenu);
                    }
                    case "1d" -> {
                        SendMessage sendMessage1 = new SendMessage(chatId, "Наш приют 'Лапа' находится в г. Астана, ул. Аккорган, 5Б. Работаем ежедневно 10:00-17:00." +
                                "Здесь Вы сможете обрести себе друга - одного из наших чудесных" +
                                "подопечных. У каждой собаки есть паспорт со всеми необходимыми вакцинами. " +
                                "Вы можете оставить нам свои контактные данные для связи. Укажите Ваш телефон и имя " +
                                "в формате: 81234567788 name");
                        telegramBot.execute(sendMessage1);

                    }
                    case "2d" -> {
                        SendMessage receievedMenuDog2 = new SendMessage(chatId,
                                "Со всеми, кто желает взять собаку из приюта, наши волонтеры проводят личные встречи. " +
                                        "После чего Вы выбираете питомца и забираете его к себе домой. В дальнейшем необходимо " +
                                        "будет отправлять отчеты о состоянии собаки.");
                        telegramBot.execute(receievedMenuDog2);
                    }
                    case "3d" -> {
                        SendMessage receievedMenuDog3 = new SendMessage(chatId,
                                "Отправьте, пожалуйста, отчет в следуещем формате:" +
                                        "\nв первом сообщении фото собаки" +
                                        "\nвторое сообщение-условия" +
                                        "\nтретье сообщение-рацион" +
                                        "\nчетвертое сообщение-изменения");
                        telegramBot.execute(receievedMenuDog3);
                    }
                    case "cat" -> {
                        SendMessage sendMenuCat = new SendMessage(chatId,
                                "Какую услугу вы хотите получить?" +
                                        "\nНапишите нужное для ответа:" +
                                        "\n1с-Узнать информацию о приюте" +
                                        "\n2с-Как взять кошку из приюта" +
                                        "\n3с-Прислать отчет о питомце");
                        telegramBot.execute(sendMenuCat);
                    }
                    case "1c" -> {
                        SendMessage sendMessage1 = new SendMessage(chatId, "Наш приют 'Лапа' находится в г. Астана, ул. Аккорган, 5В. Работаем ежедневно 11:00-18:00." +
                                "Здесь Вы сможете обрести себе друга - одного из наших чудесных" +
                                "подопечных. У каждой кошки есть паспорт со всеми необходимыми вакцинами. " +
                                "Вы можете оставить нам свои контактные данные для связи. Укажите Ваш телефон и имя " +
                                "в формате: 81234567788 name");
                        telegramBot.execute(sendMessage1);
                    }
                    case "2c" -> {
                        SendMessage receievedMenuCat2 = new SendMessage(chatId,
                                "Со всеми, кто желает взять кошку из приюта, наши волонтеры проводят личные встречи. " +
                                        "После чего Вы выбираете питомца и забираете его к себе домой. В дальнейшем необходимо " +
                                        "будет отправлять отчеты о состоянии кошки.");
                        telegramBot.execute(receievedMenuCat2);
                    }
                    case "3c" -> {
                        SendMessage receievedMenuCat3 = new SendMessage(chatId,
                                "Отправьте, пожалуйста, отчет в следующем формате:" +
                                        "\nв первом сообщении фото кошки" +
                                        "\nвторое сообщение-условия" +
                                        "\nтретье сообщение-рацион" +
                                        "\nчетвертое сообщение-изменения");
                        telegramBot.execute(receievedMenuCat3);
                    }
                }

                if (textM.contains("условия")) {
                    dogReport.setCondition(textM);
                } else if (textM.contains("рацион")) {
                    dogReport.setCondition(textM);
                } else if (textM.contains("изменения")) {
                    dogReport.setCondition(textM);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

