package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.PersonnelCard;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
import com.ahmete.busbuscard.repository.PersonnelCardRepository;
import com.ahmete.busbuscard.utility.enums.ECardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonnelCardService {
    private final PersonnelCardRepository personnelCardRepository;

    public Long generateCard() {
        PersonnelCard personnelCard = PersonnelCard.builder()
                .cardType(ECardType.PERSONNEL)
                .build();
        personnelCard =  personnelCardRepository.save(personnelCard);
       return personnelCard.getId();
    }

    public Long findCardIdByUuid(String uuid) {
        Optional<Long> cardId = personnelCardRepository.findIdByUuid(uuid);
        if(cardId.isEmpty()) {
            //TODO: Hata kodu değiştirilebilir!
            throw new BusbusCardException(EErrorType.INVALID_CARD_ERROR);
        }
        return cardId.get();
    }
}
