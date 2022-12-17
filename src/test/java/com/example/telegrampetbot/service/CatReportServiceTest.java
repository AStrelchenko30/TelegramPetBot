package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.ReportCreateException;
import com.example.telegrampetbot.model.CatReport;
import com.example.telegrampetbot.repositories.CatReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatReportServiceTest {
    CatReport report = new CatReport(1L, "", "", "");

    @Mock
    private CatReportRepository repository;

    @InjectMocks
   private CatReportService service;

    @Test
    void createReport()throws ReportCreateException {
        when(service.createReport(report)).thenReturn(new CatReport(1L, "", "", ""));
        assertEquals(report.getId(), service.createReport(report).getId());
    }

    @Test
    void updateReport() throws ReportCreateException {
        when(service.updateReport(report)).thenReturn(new CatReport(1L, "", "", ""));
        assertEquals(report.getId(), service.updateReport(report).getId());
    }

    @Test
    void findReport() throws ReportCreateException{
        when(service.findReport(1L)).thenReturn(new CatReport(1L, "", "", ""));
        assertEquals(report.getId(), service.findReport(1L).getId());
    }

    @Test
    void deleteReport() throws ReportCreateException{
        service.deleteReport(1L);
        verify(service).deleteReport(1L);
    }
}