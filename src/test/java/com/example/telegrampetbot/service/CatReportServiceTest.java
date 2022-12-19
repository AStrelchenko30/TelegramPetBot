package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.ReportCreateException;
import com.example.telegrampetbot.model.CatReport;
import com.example.telegrampetbot.repositories.CatReportRepository;
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
class CatReportServiceTest {
    CatReport report = new CatReport(1L, "dgg", "dgdg", "dgdg");

    @Mock
    private CatReportRepository repository;


    @InjectMocks
   private CatReportService service;



    @Test
    void createReport()throws ReportCreateException {
        when(repository.save(report)).thenReturn(new CatReport(1L, "dgg", "dgdg", "dgdg"));
        assertEquals(service.createReport(report).getId(),report.getId());

    }

    @Test
    void updateReport() throws ReportCreateException {
        when(repository.save(report)).thenReturn(new CatReport(1L, "dgg", "dgdg", "dgdg"));
        assertEquals(service.updateReport(report).getId(),report.getId());
    }

    @Test
    void findReport() throws ReportCreateException{
        when(repository.findById(1L)).thenReturn(Optional.of(new CatReport(1L, "dgg", "dgdg", "dgdg")));
        assertEquals(service.findReport(1L).getId(),report.getId());
    }

    @Test
    void deleteReport() throws ReportCreateException{
        service.deleteReport(1L);
        verify(repository).deleteById(1L);
    }
}