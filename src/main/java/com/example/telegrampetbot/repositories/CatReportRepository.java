package com.example.telegrampetbot.repositories;

import com.example.telegrampetbot.model.CatReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatReportRepository extends JpaRepository<CatReport, Long> {
}
