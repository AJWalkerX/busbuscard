package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.repository.CardRepository;
import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.EState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CardService {
	
	private final CardRepository cardRepository;

	private static int inActiveCardNumber ;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
		this.inActiveCardNumber = cardRepository.findAllState(EState.PASSIVE);
    }


    public void save(Card card) {
		cardRepository.save(card);
	}

	public Card findMyCard(Long cardId) {
		return cardRepository.findById(cardId).orElse(null);
	}

	public void generateAnonymousCard() {
		Card card = Card.builder().type(ECardType.STANDARD).balance(0L).state(EState.PASSIVE).build();
		Card card1 = Card.builder().type(ECardType.STANDARD).balance(0L).state(EState.PASSIVE).build();
		Card card2 = Card.builder().type(ECardType.STANDARD).balance(0L).state(EState.PASSIVE).build();
		Card card3 = Card.builder().type(ECardType.STANDARD).balance(0L).state(EState.PASSIVE).build();
		Card card4 = Card.builder().type(ECardType.STANDARD).balance(0L).state(EState.PASSIVE).build();
		cardRepository.saveAll(List.of(card, card1, card2, card3, card4));
	}
}