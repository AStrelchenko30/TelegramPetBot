package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.DogReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogReportRepository extends JpaRepository<DogReport, Long> {
}
