package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.ReportCreateException;
import com.example.telegrampetbot.exception.ReportNotFoundException;
import com.example.telegrampetbot.model.DogReport;
import com.example.telegrampetbot.repositories.DogReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DogReportService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final DogReportRepository dogReportRepository;

    public DogReportService(DogReportRepository dogReportRepository) {
        this.dogReportRepository = dogReportRepository;
    }

    /**
     * Создание нового отчета по питомцу
     * Используется метод {@link JpaRepository#save(Object)}
     *
     * @param dogReport новый отчет
     * @return создание отчета
     */
    public DogReport createReport(DogReport dogReport) {
        logger.info("createReport method used in ReportService");
        if (!dogReportRepository.findAll().contains(dogReport)) {
            return dogReportRepository.save(dogReport);
        }
        throw new ReportCreateException();
    }

    /**
     * Добавление нового отчета в БД приюта
     * Используется метод {@link JpaRepository#save(Object)}
     *
     * @param dogReportNew новый отчет
     * @return сохраненный отчет
     */

    public DogReport updateReport(DogReport dogReportNew) {
        logger.info("updateReport method used in ReportService");
        if (dogReportRepository.findById(dogReportNew.getId()).isPresent()) {
            DogReport dogReportOld = dogReportRepository.findById(dogReportNew.getId()).get();
            dogReportOld.setId(dogReportNew.getId());
            dogReportOld.setCondition(dogReportNew.getCondition());
            dogReportOld.setRation(dogReportNew.getRation());
            dogReportOld.setChanges(dogReportNew.getChanges());
            dogReportOld.setDogPhoto(dogReportNew.getDogPhoto());
            return dogReportRepository.save(dogReportOld);
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

    public DogReport findReport(Long id) {
        logger.info("findReport method used in ReportService");
        if (dogReportRepository.findById(id).isPresent()) {
            return dogReportRepository.findById(id).get();
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
        if (dogReportRepository.findById(id).isPresent()) {
            dogReportRepository.deleteById(id);
        }
        throw new ReportNotFoundException();
    }
}
