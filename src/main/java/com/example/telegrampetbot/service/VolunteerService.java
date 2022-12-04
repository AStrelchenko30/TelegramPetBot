package com.example.telegrampetbot.service;

import com.example.telegrampetbot.exception.ReportNotFoundException;
import com.example.telegrampetbot.exception.VolunteerCreateException;
import com.example.telegrampetbot.exception.VolunteerNotFoundException;
import com.example.telegrampetbot.model.Volunteer;
import com.example.telegrampetbot.repositories.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /**
     * Создание нового волонтера в приюте
     * Используется метод {@link JpaRepository#save(Object)}
     *
     * @param volunteer новый волонтер
     * @return создание волонтера
     */
    public Volunteer createVolunteer(Volunteer volunteer) {
        logger.info("createVolunteer method used in VolunteerService");
        if (!volunteerRepository.findAll().contains(volunteer)) {
            return volunteerRepository.save(volunteer);
        }
        throw new VolunteerCreateException();
    }

    /**
     * Добавление нового волонтера в БД приюта
     * Используется метод {@link JpaRepository#save(Object)}
     *
     * @param volunteerNew новый волонтер
     * @return сохраненный волонтер
     */
    public Volunteer updateVolunteer(Volunteer volunteerNew) {
        logger.info("updateVolunteer method used in VolunteerService");
        if (volunteerRepository.findById(volunteerNew.getId()).isPresent()) {
            Volunteer volunteerOld = volunteerRepository.findById(volunteerNew.getId()).get();
            volunteerOld.setName(volunteerNew.getName());
            volunteerOld.setSurname(volunteerNew.getSurname());
            volunteerOld.setMail(volunteerNew.getMail());
            return volunteerRepository.save(volunteerOld);
        }
        throw new VolunteerNotFoundException();
    }

    /**
     * Нахождение волонтера по иденфикатору
     * Используется метод {@link JpaRepository#findById(Object)}
     *
     * @param id идентификатор волонтера
     * @return найденного волонтера из БД
     */

    public Volunteer findVolunteer(Long id) {
        logger.info("findVolunteer method used in VolunteerService");
        if (volunteerRepository.findById(id).isPresent()) {
            return volunteerRepository.findById(id).get();
        }
        throw new VolunteerNotFoundException();
    }

    /**
     * Нахождение волонтера по иденфикатору и его удаление из БД
     * Используется метод {@link JpaRepository#deleteById(Object)}
     *
     * @param id идентификатор волонтера
     * @return удаленного волонтера из БД
     */

    public void deleteVolunteer(Long id) {
        logger.info("deleteVolunteer method used in VolunteerService");
        if (volunteerRepository.findById(id).isPresent()) {
            volunteerRepository.deleteById(id);
        }
        throw new VolunteerNotFoundException();
    }
}
