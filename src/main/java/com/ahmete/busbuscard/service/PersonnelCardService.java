package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.PersonnelCard;
import com.ahmete.busbuscard.repository.PersonnelCardRepository;
import com.ahmete.busbuscard.utility.enums.ECardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
