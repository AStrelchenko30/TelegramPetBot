package com.example.telegrampetbot.service;

import com.example.telegrampetbot.model.Client;
import com.example.telegrampetbot.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client) {
        logger.info("createClient method used in ClientService");
        return clientRepository.save(client);
    }

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

    public Client findClient(Long id) {
        logger.info("findClient method used in ClientService");
        return clientRepository.findById(id).get();
    }

    public void deleteClient(Long id) {
        logger.info("deleteClient method used in ClientService");
        clientRepository.deleteById(id);
    }
}
