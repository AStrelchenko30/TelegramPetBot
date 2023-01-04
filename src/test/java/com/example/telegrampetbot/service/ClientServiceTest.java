package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.ClientCreateException;
import com.example.telegrampetbot.exception.ClientNotFoundException;
import com.example.telegrampetbot.model.Client;
import com.example.telegrampetbot.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService service;

    Client client=new Client(1L,"","","",new Timestamp(System.currentTimeMillis()));

    @Test
    void createClient() throws ClientCreateException {
        when(repository.save(client)).thenReturn(new Client(1L,"","","",new Timestamp(System.currentTimeMillis())));
        assertEquals(service.createClient(client).getId(),client.getId());
    }

    @Test
    void updateClient() throws ClientNotFoundException {
        when(repository.save(client)).thenReturn(new Client(1L,"","","",new Timestamp(System.currentTimeMillis())));
        assertEquals(service.updateClient(client).getId(),client.getId());
    }

    @Test
    void findClient() throws ClientNotFoundException {
        when(repository.findById(1)).thenReturn(Optional.of(new Client(1L, "", "",  "",new Timestamp(System.currentTimeMillis()))));
        assertEquals(service.findClient(1).getId(),client.getId());

    }

    @Test
    void deleteClient()throws ClientNotFoundException {
        service.deleteClient(1);
        verify(repository).deleteById(1);
    }
}