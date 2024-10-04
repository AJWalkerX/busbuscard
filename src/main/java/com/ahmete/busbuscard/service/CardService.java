package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CardService {
	
	private final CardRepository cardRepository;
	
	public void save(Card card) {

		cardRepository.save(card);
	}

	public Card findMyCard(Long cardId) {
		return cardRepository.findById(cardId).orElse(null);
	}


}