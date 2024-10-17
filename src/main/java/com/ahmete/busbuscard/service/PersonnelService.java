package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Personnel;
import com.ahmete.busbuscard.entity.PersonnelCard;
import com.ahmete.busbuscard.repository.PersonnelRepository;
import com.ahmete.busbuscard.utility.enums.EGender;
import com.ahmete.busbuscard.utility.enums.EPersonnelType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PersonnelService {
    private final PersonnelRepository personnelRepository;
    private final PersonnelCardService personnelCardService;

    public void generatePersonnelAndCard() {
        Random random = new Random();
        List<String> names = Arrays.asList("John", "Jane", "Alex", "Emily", "Chris");
        List<String> surnames = Arrays.asList("Smith", "Johnson", "Williams", "Brown", "Jones");
        List<EPersonnelType> personnelTypes = Arrays.asList(EPersonnelType.CAPITAN, EPersonnelType.DRIVER, EPersonnelType.MACHINIST);

        for (int i = 0; i < 15; i++) {
            Long cardId = personnelCardService.generateCard();
            String randomName = names.get(random.nextInt(names.size()));
            String randomSurname = surnames.get(random.nextInt(surnames.size()));
            EGender randomGender = random.nextBoolean() ? EGender.MALE : EGender.FEMALE;
            EPersonnelType personnelType = personnelTypes.get(i % personnelTypes.size());

            Personnel personnel = Personnel.builder()
                    .personnelCardId(cardId)
                    .gender(randomGender)
                    .ePersonnelType(personnelType)
                    .name(randomName)
                    .surname(randomSurname)
                    .build();
            personnelRepository.save(personnel);
        }
    }
}
