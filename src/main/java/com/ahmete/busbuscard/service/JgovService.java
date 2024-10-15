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
import com.ahmete.mapper.JgovMapper;
import com.ahmete.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JgovService {

    private final JgovRepository jgovRepository;
    private final UserService userService;
    private final CardService cardService;

    public String apply(ApplyCardRequestDto dto) {
        if (userService.existsByTC(dto.getTc())
        ) {
            throw new BusbusCardException(EErrorType.NOT_UNIQUE_TC);
        }
        if (dto.getTitles().isEmpty() || dto.getTc().length() != 11 || dto.getGender().isEmpty() ||
                dto.getName().isEmpty() || dto.getSurname().isEmpty()) {
            throw new BusbusCardException(EErrorType.VALIDATION_ERROR);
        }
        EGender gender = getGender(dto.getGender());
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .tc(dto.getTc())
                .gender(gender)
                .build();
        user = userService.save(user);
        if (user.getId() == null) {
            throw new BusbusCardException(EErrorType.INTERNAL_SERVER_ERROR);
        }

        ETitle title = getTitle(dto.getTitles());
        Card card = cardService.generateCardByTitle(title);
        if (card.getId() == null) {
            throw new BusbusCardException(EErrorType.INTERNAL_SERVER_ERROR);
        }
        Jgov jgov = Jgov.builder()
                .titles(title)
                .address(dto.getAddress())
                .userId(user.getId())
                .cardId(card.getId())
                .build();
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
        switch (gender.toLowerCase()) {
            case "male" -> {
                return EGender.MALE;
            }
            case "female" -> {
                return EGender.FEMALE;
            }
            default -> throw new BusbusCardException(EErrorType.VALIDATION_ERROR);
        }
    }
}