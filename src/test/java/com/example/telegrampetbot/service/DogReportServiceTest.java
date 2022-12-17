package com.example.telegrampetbot.service;

import com.example.telegrampetbot.model.DogReport;
import com.example.telegrampetbot.repositories.DogReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(service.createReport(report)).thenReturn(new DogReport(1L, "", "", ""));
        assertEquals(report.getId(), service.createReport(report).getId());
    }

    @Test
    void updateReport() {
        when(service.updateReport(report)).thenReturn(new DogReport(1L, "", "", ""));
        assertEquals(report.getId(), service.updateReport(report).getId());

    }

    @Test
    void findReport() {
        when(service.findReport(1L)).thenReturn(new DogReport(1L, "", "", ""));
        assertEquals(report.getId(), service.findReport(1L).getId());
    }

    @Test
    void deleteReport() {
        service.deleteReport(1L);
        verify(service).deleteReport(1L);
    }
}