package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.Cat;
import com.example.telegrampetbot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findById(Integer id);
}
