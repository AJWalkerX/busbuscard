package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.repository.CardRepository;
import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.EState;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CardService {
	
	private final CardRepository cardRepository;

	private static int inActiveCardNumber ;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
		inActiveCardNumber = getInactiveCardNumber();

    }


    public void save(Card card) {
		cardRepository.save(card);
	}

	public Card findMyCard(Long cardId) {
		return cardRepository.findById(cardId).orElse(null);
	}


	public String  generateAnonymousCard() {
		if (inActiveCardNumber == 0) {
			Card card = Card.builder().type(ECardType.STANDARD).balance(0L).state(EState.PASSIVE).build();
			Card card1 = Card.builder().type(ECardType.STANDARD).balance(0L).state(EState.PASSIVE).build();
			Card card2 = Card.builder().type(ECardType.STANDARD).balance(0L).state(EState.PASSIVE).build();
			Card card3 = Card.builder().type(ECardType.STANDARD).balance(0L).state(EState.PASSIVE).build();
			Card card4 = Card.builder().type(ECardType.STANDARD).balance(0L).state(EState.PASSIVE).build();
			cardRepository.saveAll(List.of(card, card1, card2, card3, card4));
			inActiveCardNumber = inActiveCardNumber + 5;
			return "Kart oluşturuldu!";
		}
		return "Elinde yeterince kart var!";
	}

	public String sellAnonymousCard() {
		Card card = cardRepository.findDistinctFirstByStateAndType(EState.PASSIVE, ECardType.STANDARD);
		if (card != null) {
			card.setState(EState.ACTIVE);
			cardRepository.save(card);
			inActiveCardNumber = inActiveCardNumber -1;
			return "Kart Satıldı! " +card.getUuid();
		}
		return "Elimdeki kartlar tükendi!";
	}

	private int getInactiveCardNumber(){
		return cardRepository.countAllByState(EState.PASSIVE);
	}
}