package com.example.telegrampetbot.service;

import com.example.telegrampetbot.model.DogReport;
import com.example.telegrampetbot.repositories.DogReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogReportServiceTest {
    DogReport report = new DogReport(1L, "", "", "");
    @Mock
    private DogReportRepository repository;
    @InjectMocks
    private DogReportService service;

    @Test
    void createReport() {
        when(repository.save(report)).thenReturn(new DogReport(1L, "", "", ""));
        assertEquals(service.createReport(report).getId(),report.getId());
    }

    @Test
    void updateReport() {
        when(repository.save(report)).thenReturn(new DogReport(1L, "", "", ""));
        assertEquals(service.updateReport(report).getId(),report.getId());

    }

    @Test
    void findReport() {
        when(repository.findById(1L)).thenReturn(Optional.of(new DogReport(1L, "", "", "")));
        assertEquals(service.findReport(1L).getId(),report.getId());
    }

    @Test
    void deleteReport() {
        service.deleteReport(1L);
        verify(service).deleteReport(1L);
    }
}