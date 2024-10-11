package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.dto.request.CardExpirationSaveRequestDto;
import com.ahmete.busbuscard.entity.CardExpiration;
import com.ahmete.busbuscard.repository.CardExpirationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardExpirationService {
    private final CardExpirationRepository cardExpirationRepository;

    public CardExpiration save(CardExpirationSaveRequestDto dto) {

        return cardExpirationRepository.save(
                CardExpiration.builder()
                        .cardId(dto.getCardId())
                        .expirationDate(dto.setExpirationDate())
                        .build()
        );
    }
}
