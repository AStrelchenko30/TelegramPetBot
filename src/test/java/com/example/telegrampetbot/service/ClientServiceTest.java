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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService service;

    Client client=new Client(1L,"","",232332L,"");

    @Test
    void createClient() throws ClientCreateException {
        when(service.createClient(client)).thenReturn(new Client(1L,"","",232332L,""));
        assertEquals(service.createClient(client).getId(),client.getId());
    }

    @Test
    void updateClient() throws ClientNotFoundException {
        when(service.updateClient(client)).thenReturn(new Client(1L,"","",232332L,""));
        assertEquals(client.getId(),service.updateClient(client).getId());
        verify(repository).save(client);
    }

    @Test
    void findClient() throws ClientNotFoundException {
        when(service.findClient(1)).thenReturn(new Client(1L,"","",232332L,""));
        assertEquals(client.getId(),service.findClient(1).getId());
        verify(repository).findById(1);
    }

    @Test
    void deleteClient()throws ClientNotFoundException {
        service.deleteClient(1);
        verify(repository).deleteById(1);
    }
}