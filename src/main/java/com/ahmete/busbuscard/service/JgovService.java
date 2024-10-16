package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.dto.request.ApplyCardRequestDto;
import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.entity.Jgov;
import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
import com.ahmete.busbuscard.repository.JgovRepository;
import com.ahmete.busbuscard.utility.enums.EGender;
import com.ahmete.busbuscard.utility.enums.ETitle;
import com.ahmete.busbuscard.mapper.JgovMapper;
import com.ahmete.busbuscard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JgovService {

    private final JgovRepository jgovRepository;
    private final UserService userService;
    private final CardService cardService;

    public String apply(ApplyCardRequestDto dto) {
        if (userService.existsByTC(dto.tcNo())
        ) {
            throw new BusbusCardException(EErrorType.NOT_UNIQUE_TC);
        }
        if (dto.title().isEmpty() || dto.tcNo().length() != 11 || dto.gender().isEmpty() ||
                dto.firstname().isEmpty() || dto.lastname().isEmpty()) {
            throw new BusbusCardException(EErrorType.VALIDATION_ERROR);
        }
        EGender gender = getGender(dto.gender().toUpperCase());

        User user = UserMapper.INSTANCE.fromApplyCardRequestDto(dto);
        user.setGender(gender);
        user = userService.save(user);
        if (user.getId() == null) {
            throw new BusbusCardException(EErrorType.INTERNAL_SERVER_ERROR);
        }

        ETitle title = getTitle(dto.title());
        Card card = cardService.generateCardByTitle(title);
        if (card.getId() == null) {
            throw new BusbusCardException(EErrorType.INTERNAL_SERVER_ERROR);
        }

        Jgov jgov = JgovMapper.INSTANCE.fromApplyCardRequestDto(dto);
        jgov.setTitles(title);
        jgovRepository.save(jgov);
        return "<h1 style=\"color: green\">Card UUID: " + card.getUuid() + " Card Type: " + card.getType() + "</h1>";
    }

    private ETitle getTitle(String titles) {
        switch (titles.toLowerCase()) {
            case "student" -> {
                return ETitle.STUDENT;
            }
            case "civil" -> {
                return ETitle.CIVIL;
            }
            case "disabled" -> {
                return ETitle.DISABLED;
            }
            case "medicinal" -> {
                return ETitle.MEDICINAL;
            }
            case "soldier" -> {
                return ETitle.SOLDIER;
            }
            case "teacher" -> {
                return ETitle.TEACHER;
            }
            case "journalist" -> {
                return ETitle.JOURNALIST;
            }
            case "police" -> {
                return ETitle.POLICE;
            }
            case "guards" -> {
                return ETitle.GUARDS;
            }
            case "eyt" -> {
                return ETitle.EYT;
            }
            case "veteran" -> {
                return ETitle.VETERAN;
            }
            default -> throw new BusbusCardException(EErrorType.VALIDATION_ERROR);
        }
    }

    private EGender getGender(String gender) {
        switch (gender.toUpperCase()) {
            case "MALE" -> {
                return EGender.MALE;
            }
            case "FEMALE" -> {
                return EGender.FEMALE;
            }
            default -> throw new BusbusCardException(EErrorType.VALIDATION_ERROR);
        }
    }

    public Jgov findByUserId(Long id) {
       return jgovRepository.findByUserId(id);
    }
}