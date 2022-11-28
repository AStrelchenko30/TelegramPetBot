package com.example.telegrampetbot.service;

import com.example.telegrampetbot.model.Volunteer;
import com.example.telegrampetbot.repositories.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public Volunteer createVolunteer(Volunteer volunteer) {
        logger.info("createVolunteer method used in VolunteerService");
        return volunteerRepository.save(volunteer);
    }

    public Volunteer updateVolunteer(Volunteer volunteerNew) {
        logger.info("updateVolunteer method used in VolunteerService");
        Volunteer volunteerOld = volunteerRepository.findById(volunteerNew.getId()).get();
        volunteerOld.setName(volunteerNew.getName());
        volunteerOld.setSurname(volunteerNew.getSurname());
        volunteerOld.setMail(volunteerNew.getMail());
        return volunteerRepository.save(volunteerOld);
    }

    public Volunteer findVolunteer(Long id) {
        logger.info("findVolunteer method used in VolunteerService");
        return volunteerRepository.findById(id).get();
    }

    public void deleteVolunteer(Long id) {
        logger.info("deleteVolunteer method used in VolunteerService");
        volunteerRepository.deleteById(id);
    }
}
