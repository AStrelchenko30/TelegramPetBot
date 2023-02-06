package com.example.telegrampetbot.listener;

import com.example.telegrampetbot.model.*;
import com.example.telegrampetbot.repositories.*;
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

import static org.apache.coyote.http11.Constants.a;

/**
 * Message handling class
 */
@Service
public class TelegramPetBotListener implements UpdatesListener {
    public ClientRepository clientRepository;
    public DogRepository dogRepository;
    public CatRepository catRepository;
    public DogPhotoRepository dogPhotoRepository;
    public CatPhotoRepository catPhotoRepository;
    public DogReportRepository dogReportRepository;
    public CatReportRepository catReportRepository;
    public ConditionRepository conditionRepository;
    public int condition = 0;

    /**
     * Search rule pattern
     */
    private final Pattern patternMessage = Pattern.compile("(^[0-9]{11})");
    private final Logger logger = LoggerFactory.getLogger(TelegramPetBotListener.class);


    @Autowired
    private final TelegramBot telegramBot;

    public TelegramPetBotListener(ClientRepository clientRepository, DogRepository dogRepository, TelegramBot telegramBot, DogReportRepository dogReportRepository, DogPhotoRepository dogPhotoRepository, CatReportRepository catReportRepository, CatPhotoRepository catPhotoRepository, CatRepository catRepository, ConditionRepository conditionRepository) {
        this.clientRepository = clientRepository;
        this.dogRepository = dogRepository;
        this.telegramBot = telegramBot;
        this.dogReportRepository = dogReportRepository;
        this.dogPhotoRepository = dogPhotoRepository;
        this.conditionRepository = conditionRepository;
        this.catReportRepository = catReportRepository;
        this.catPhotoRepository = catPhotoRepository;
        this.catRepository = catRepository;
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
        for (Update update : updates) {

            logger.info("Processing update: {}", update);
            Message message = update.message();
            Long chatId = update.message().chat().id();
            String textM = message.text();
            registerClient(update.message());
            Client client = clientRepository.findByChatId(update.message().chat().id());
            DogPhoto dogPhoto = new DogPhoto();
            DogReport dogReport = new DogReport();
            Condition conditionStatus = new Condition();

            CatPhoto catPhoto = new CatPhoto();
            CatReport catReport = new CatReport();

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
                            SendMessage sendMenuPet = new SendMessage(chatId,
                                    "Приветствуем Вас в нашем телеграм-боте." +
                                            "\n Для выбора приюта для собак, введите Dog" +
                                            "\n Для выбора приюта для кошек, введите Cat");
                            telegramBot.execute(sendMenuPet);
                            condition = 0;
                        }
                        case "Dog" -> {
                            SendMessage sendMenu = new SendMessage(chatId,
                                    "Какую услугу вы хотите получить?" +
                                            "\nНапишите нужное для ответа:" +
                                            "\n1d-Узнать информацию о приюте" +
                                            "\n2d-Как взять собаку из приюта" +
                                            "\n3d-Прислать отчет о питомце");
                            telegramBot.execute(sendMenu);
                            condition = 0;
                        }
                        case "1d" -> {
                            SendMessage sendMessage1Dog = new SendMessage(chatId, "Наш приют 'Лапа' находится в г. Астана, ул. Аккорган, 5Б. Работаем ежедневно 10:00-17:00." +
                                    "Здесь Вы сможете обрести себе друга - одного из наших чудесных" +
                                    "подопечных. У каждой собаки есть паспорт со всеми необходимыми вакцинами. " +
                                    "Вы можете оставить нам свои контактные данные для связи. Укажите Ваш телефон и имя " +
                                    "в формате: 81234567788");
                            telegramBot.execute(sendMessage1Dog);
                            condition = 0;
                        }
                        case "2d" -> {
                            SendMessage receievedMenuDog2 = new SendMessage(chatId,
                                    "Со всеми, кто желает взять собаку из приюта, наши волонтеры проводят личные встречи. " +
                                            "После чего Вы выбираете питомца и забираете его к себе домой. В дальнейшем необходимо " +
                                            "будет отправлять отчеты о состоянии собаки.");
                            telegramBot.execute(receievedMenuDog2);
                            condition = 0;
                        }
                        case "3d" -> {
                            SendMessage receievedMenuDog3 = new SendMessage(chatId,
                                    "Отправьте, пожалуйста, отчет в следуещем формате:" +
                                            "\nв первом сообщении фото собаки" +
                                            "\nвторое сообщение-начинается словом-'Условия собаки'" +
                                            "\nтретье сообщение-начинается словом-'Рацион собаки'" +
                                            "\nчетвертое сообщение-Изменения собаки");
                            telegramBot.execute(receievedMenuDog3);
                            condition = 0;
                        }
                        case "Cat" -> {
                            SendMessage sendMenuCat = new SendMessage(chatId,
                                    "Какую услугу вы хотите получить?" +
                                            "\nНапишите нужное для ответа:" +
                                            "\n1с-Узнать информацию о приюте" +
                                            "\n2с-Как взять кошку из приюта" +
                                            "\n3с-Прислать отчет о питомце");
                            telegramBot.execute(sendMenuCat);
                            condition = 0;
                        }
                        case "1c" -> {
                            SendMessage sendMessage1Cat = new SendMessage(chatId, "Наш приют 'Лапа' находится в г. Астана, ул. Аккорган, 5В. Работаем ежедневно 11:00-18:00." +
                                    "Здесь Вы сможете обрести себе друга - одного из наших чудесных" +
                                    "подопечных. У каждой кошки есть паспорт со всеми необходимыми вакцинами. " +
                                    "Вы можете оставить нам свои контактные данные для связи. Укажите Ваш телефон и имя " +
                                    "в формате: 81234567788");
                            telegramBot.execute(sendMessage1Cat);
                            condition = 0;
                        }
                        case "2c" -> {
                            SendMessage receievedMenuCat2 = new SendMessage(chatId,
                                    "Со всеми, кто желает взять кошку из приюта, наши волонтеры проводят личные встречи. " +
                                            "После чего Вы выбираете питомца и забираете его к себе домой. В дальнейшем необходимо " +
                                            "будет отправлять отчеты о состоянии кошки.");
                            telegramBot.execute(receievedMenuCat2);
                            condition = 0;
                        }
                        case "3c" -> {
                            SendMessage receievedMenuCat3 = new SendMessage(chatId,
                                    "Отправьте, пожалуйста, отчет в следующем формате:" +
                                            "\nфото кошки" +
                                            "\nвторое сообщение-начинается словом-'Условия кошки'" +
                                            "\nтретье сообщение-начинается словом-'Рацион кошки'" +
                                            "\nчетвертое сообщение-начинается словом-'Изменения кошки'");
                            telegramBot.execute(receievedMenuCat3);
                            condition = 0;
                        }
                    }
                }
                conditionStatus.setChat_id(chatId);
                conditionStatus.setConditionForReport(condition);
                conditionRepository.save(conditionStatus);
                condition = condition + 1;

                if (conditionRepository.findByConditionForReport(1) != null && conditionRepository.findByConditionForReport(3) == null && conditionRepository.findByConditionForReport(2) == null) {
                    dogReport.setCondition(textM);
                } else if (conditionRepository.findByConditionForReport(2) != null && conditionRepository.findByConditionForReport(1) != null && conditionRepository.findByConditionForReport(3) == null) {
                    dogReport.setRation(textM);

                } else if (conditionRepository.findByConditionForReport(3) != null && conditionRepository.findByConditionForReport(2) != null && conditionRepository.findByConditionForReport(1) != null) {
                    dogReport.setChanges(textM);
                }
                dogReportRepository.save(dogReport);



               /* if (conditionRepository.findByChatId(chatId).getConditionForReport() == 1) {
                    dogReport.setChanges(textM);
                    dogReportRepository.save(dogReport);

                } else if (conditionRepository.findByChatId(chatId).getConditionForReport() == 2) {
                    dogReport.setRation(textM);
                    dogReportRepository.save(dogReport);
                } else logger.info("!!!");
*/
                if (update.message().photo() != null) {
                    PhotoSize[] photoSizes = update.message().photo();
                    for (PhotoSize photoSize : photoSizes) {
                        try {
                            GetFileResponse getFileResponse = telegramBot.execute(new GetFile(photoSize.fileId()));
                            byte[] bytes = telegramBot.getFileContent(getFileResponse.file());
                            dogPhoto.setData(bytes);
                            dogPhotoRepository.save(dogPhoto);
                            dogReport.setDogPhoto(dogPhoto);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
//                    } else if ((Objects.equals(updates.get(updates.size() - 1).message().text(), "3c"))){
//                        PhotoSize[] photoSizes = update.message().photo();
//                        for (PhotoSize photoSize : photoSizes) {
//                            try {
//                                GetFileResponse getFileResponse = telegramBot.execute(new GetFile(photoSize.fileId()));
//                                byte[] bytes = telegramBot.getFileContent(getFileResponse.file());
//                                catPhoto.setData(bytes);
//                                catReport.setCatPhoto(catPhoto);
//                                catPhotoRepository.save(catPhoto);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
                }
      //         dogReportRepository.save(dogReport);
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void registerClient(Message msg) {
        if (clientRepository.findByChatId(msg.chat().id()) == null) {
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

