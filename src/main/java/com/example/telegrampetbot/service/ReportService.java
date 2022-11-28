package com.example.telegrampetbot.service;

import com.example.telegrampetbot.model.Report;
import com.example.telegrampetbot.repositories.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report createReport(Report report) {
        logger.info("createReport method used in ReportService");
        return reportRepository.save(report);
    }

    public Report updateReport(Report reportNew) {
        logger.info("updateReport method used in ReportService");
        Report reportOld = reportRepository.findById(reportNew.getId()).get();
        reportOld.setId(reportNew.getId());
        reportOld.setCondition(reportNew.getCondition());
        reportOld.setRation(reportNew.getRation());
        reportOld.setChanges(reportNew.getChanges());

        reportOld.setFilePath(reportNew.getFilePath());
        reportOld.setFileSize(reportNew.getFileSize());
        reportOld.setMediaType(reportNew.getMediaType());
        reportOld.setData(reportNew.getData());
        return reportRepository.save(reportOld);
    }

    public Report findReport(Long id) {
        logger.info("findReport method used in ReportService");
        return reportRepository.findById(id).get();
    }

    public void deleteReport(Long id) {
        logger.info("deleteReport method used in ReportService");
        reportRepository.deleteById(id);
    }
}
