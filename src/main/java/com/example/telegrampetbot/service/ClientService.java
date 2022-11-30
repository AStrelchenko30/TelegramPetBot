package com.example.telegrampetbot.service;

import com.example.telegrampetbot.model.Client;
import com.example.telegrampetbot.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Создание нового клиента приюта
     * Используется метод {@link JpaRepository#save(Object)}
     * @param client новый клиент. посетивший Бот
     * @return создание клиент
     */
    public Client createClient(Client client) {
        logger.info("createClient method used in ClientService");
        return clientRepository.save(client);
    }
    /**
     * Добавление нового клиента в БД приюта
     * Используется метод {@link JpaRepository#save(Object)}
     * @param clientNew новый клиент
     * @return сохраненный клиент
     */
    public Client updateClient(Client clientNew) {
        logger.info("updateClient method used in ClientService");
        Client clientOld = clientRepository.findById(clientNew.getId()).get();
        clientOld.setPassportNumber(clientNew.getPassportNumber());
        clientOld.setChatId(clientNew.getChatId());
        clientOld.setName(clientNew.getName());
        clientOld.setSurname(clientNew.getSurname());
        clientOld.setMail(clientNew.getMail());
        return clientRepository.save(clientOld);
    }

    /**
     * Нахождение клиента по иденфикатору
     * Используется метод {@link JpaRepository#findById(Object)}
     * @param id идентификатор клиента
     * @return найденного клиента из БД
     */
    public Client findClient(Integer id) {
        logger.info("findClient method used in ClientService");
        return clientRepository.findById(id).get();
    }
    /**
     * Ищет клиента по иденфикатору и удаляет его
     * Используется метод {@link JpaRepository#deleteById(Object)}
     * @param id идентификатор клиента
     * @return найденного и удаленного клиента из БД
     */
    public void deleteClient(Integer id) {
        logger.info("deleteClient method used in ClientService");
        clientRepository.deleteById(id);
    }
}
