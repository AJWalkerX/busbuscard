package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.PersonnelCard;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
import com.ahmete.busbuscard.repository.PersonnelCardRepository;
import com.ahmete.busbuscard.utility.enums.ECardStatus;
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
            throw new BusbusCardException(EErrorType.CARD_NOT_FOUND_ERROR);
        }
        return cardId.get();
    }


    public void updateCardStatusToActive(String uuid) {
        Optional<PersonnelCard> optionalPersonnelCard = personnelCardRepository.findByUuid(uuid);
        if (optionalPersonnelCard.isEmpty()) {
            throw new BusbusCardException(EErrorType.CARD_NOT_FOUND_ERROR);
        }
        PersonnelCard personnelCard = optionalPersonnelCard.get();
        personnelCard.setECardStatus(ECardStatus.WORKING);
        personnelCardRepository.save(personnelCard);
    }

    public boolean checkCardStatus(String uuid) {
        ECardStatus cardStatus = personnelCardRepository.findCardStatusByUuid(uuid);
        return cardStatus.equals(ECardStatus.WORKING);
    }

    public void updateCardStatusToPassive(String uuid) {
        Optional<PersonnelCard> optionalPersonnelCard = personnelCardRepository.findByUuid(uuid);
        if (optionalPersonnelCard.isEmpty()) {
            throw new BusbusCardException(EErrorType.CARD_NOT_FOUND_ERROR);
        }
        PersonnelCard personnelCard = optionalPersonnelCard.get();
        personnelCard.setECardStatus(ECardStatus.RESTING);
        personnelCardRepository.save(personnelCard);
    }

}
