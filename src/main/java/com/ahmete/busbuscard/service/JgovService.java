package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.dto.request.ApplyCardRequestDto;
import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.entity.Jgov;
import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
import com.ahmete.busbuscard.repository.JgovRepository;
import com.ahmete.busbuscard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JgovService {

    private final JgovRepository jgovRepository;
    private final UserService userService;
    private final CardService cardService;

    public String apply(ApplyCardRequestDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .tc(dto.getTc())
                .gender(dto.getGender())
                .build();
        user = userService.save(user);
        if (user.getId() == null){
            throw new BusbusCardException(EErrorType.INTERNAL_SERVER_ERROR);
        }
        Card card = cardService.generateCardByTitle(dto.getTitles());
        if (card.getId() == null){
            throw new BusbusCardException(EErrorType.INTERNAL_SERVER_ERROR);
        }
        Jgov jgov = Jgov.builder()
                .titles(dto.getTitles())
                .address(dto.getAddress())
                .userId(user.getId())
                .cardId(card.getId())
                .build();
        jgovRepository.save(jgov);
        return "<h1 style=\"color: green\">Card UUID: "+card.getUuid()+ " Card Type: "+card.getType() + "</h1>";
    }
}