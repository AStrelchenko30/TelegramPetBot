package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.ReportCreateException;
import com.example.telegrampetbot.exception.ReportNotFoundException;
import com.example.telegrampetbot.model.CatReport;
import com.example.telegrampetbot.repositories.CatReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CatReportService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final CatReportRepository catReportRepository;

    public CatReportService(CatReportRepository catReportRepository) {
        this.catReportRepository = catReportRepository;
    }

    /**
     * Создание нового отчета по питомцу
     * Используется метод {@link JpaRepository#save(Object)}
     *
     * @param catReport новый отчет
     * @return создание отчета
     */
    public CatReport createReport(CatReport catReport) {
        logger.info("createReport method used in ReportService");
        if (!catReportRepository.findAll().contains(catReport)) {
            return catReportRepository.save(catReport);
        }
        throw new ReportCreateException();
    }

    /**
     * Добавление нового отчета в БД приюта
     * Используется метод {@link JpaRepository#save(Object)}
     *
     * @param catReportNew новый отчет
     * @return сохраненный отчет
     */

    public CatReport updateReport(CatReport catReportNew) {
        logger.info("updateReport method used in ReportService");
        if (catReportRepository.findById(catReportNew.getId()).isPresent()) {
            CatReport catReportOld = catReportRepository.findById(catReportNew.getId()).get();
            catReportOld.setId(catReportNew.getId());
            catReportOld.setCondition(catReportNew.getCondition());
            catReportOld.setRation(catReportNew.getRation());
            catReportOld.setChanges(catReportNew.getChanges());
            catReportOld.setCatPhoto(catReportNew.getCatPhoto());
            return catReportRepository.save(catReportOld);
        }
        throw new ReportNotFoundException();
    }

    /**
     * Нахождение отчета по иденфикатору
     * Используется метод {@link JpaRepository#findById(Object)}
     *
     * @param id идентификатор отчета
     * @return найденного отчета из БД
     */

    public CatReport findReport(Long id) {
        logger.info("findReport method used in ReportService");
        if (catReportRepository.findById(id).isPresent()) {
            return catReportRepository.findById(id).get();
        }
        throw new ReportNotFoundException();
    }

    /**
     * Нахождение отчета по иденфикатору и его удаление
     * Используется метод {@link JpaRepository#deleteById(Object)}
     *
     * @param id идентификатор отчета
     * @return удаленного отчета из БД
     */

    public void deleteReport(Long id) {
        logger.info("deleteReport method used in ReportService");
        if (catReportRepository.findById(id).isPresent()) {
            catReportRepository.deleteById(id);
        }
        throw new ReportNotFoundException();
    }
}
