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
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.sql.Timestamp;
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
    private final Pattern patternMessage = Pattern.compile("(^[0-9]{11})");
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
            Client client = clientRepository.findByChatId(update.message().chat().id()).get();

            DogPhoto dogPhoto = new DogPhoto();
            DogReport dogReport = new DogReport();

            if (message != null) {
                if (message.text() != null) {
                    Matcher matcherMessage = patternMessage.matcher(textM);
                    if (matcherMessage.matches()) {
                        String phone = matcherMessage.group(1);
                        client.setPhone(phone);
                        clientRepository.save(client);
                    }

                    switch (textM) {
                        case "/start" -> {
                            registerClient(update.message());
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
                                            "\nвторое сообщение-начинается словом-'условия'" +
                                            "\nтретье сообщение-начинается словом-'рацион'" +
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
                                    "в формате: 81234567788");
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
                                            "\nфото кошки" +
                                            "\nвторое сообщение-начинается словом-'условия'" +
                                            "\nтретье сообщение-начинается словом-'рацион'" +
                                            "\nчетвертое сообщение-начинается словом-'изменения'");
                            telegramBot.execute(receievedMenuCat3);
                        }
                    }

                    if (textM.contains("условия")) {
                        dogReport.setCondition(textM);
                        dogReport.setClient(client);
                    } else if (textM.contains("рацион")) {
                        dogReport.setRation(textM);
                    } else if (textM.contains("изменения")) {
                        dogReport.setChanges(textM);
                    }
                }
                if (update.message().photo() != null) {
                    PhotoSize[] photoSizes = update.message().photo();
                    for (PhotoSize photoSize : photoSizes) {
                        try {
                            GetFileResponse getFileResponse = telegramBot.execute(new GetFile(photoSize.fileId()));
                            byte[] bytes = telegramBot.getFileContent(getFileResponse.file());
//                            String filePath = getFileResponse.file().filePath();
//                            String extension = filePath.substring(filePath.lastIndexOf('.'));
                            dogPhoto.setData(bytes);
                            dogReport.setDogPhoto(dogPhoto);
                            dogPhotoRepository.save(dogPhoto);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                dogReportRepository.save(dogReport);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void registerClient(Message msg) {
        if (clientRepository.findByChatId(msg.chat().id()).isEmpty()) {
            Long chatId = msg.chat().id();
            Chat chat = msg.chat();
            Client client = new Client();
            client.setName(chat.firstName());
            client.setSurname(chat.lastName());
            client.setChatId(chatId);
            client.setTimeRegistered(new Timestamp(System.currentTimeMillis()));
            clientRepository.save(client);
        } else {
            logger.info("user already created");
        }
    }
}

