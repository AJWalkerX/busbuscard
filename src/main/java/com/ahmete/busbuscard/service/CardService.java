package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.repository.CardRepository;
import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.EState;
import com.ahmete.busbuscard.utility.enums.ETitle;
import com.ahmete.busbuscard.views.VwCardDetail;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class CardService {
	
	private final CardRepository cardRepository;

	private static int inActiveCardNumber ;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
        inActiveCardNumber = getInactiveCardNumber();
    }

    public Card save(Card card) {
		return cardRepository.save(card);
	}

	public Card findMyCard(String  uuid) {
		return cardRepository.findByUuid(uuid).orElse(null);
	}

	public Long findMyCardId(String  uuid) {
		return cardRepository.findMyCardId(uuid);
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


	public Optional<Card> findByUuid(String cardUuid) {
		return cardRepository.findByUuid(cardUuid);
	}

	public Card generateCardByTitle(ETitle titles) {
		LocalDate today = LocalDate.now();
		LocalDate nextYear = today.plusYears(1);
		Card card = Card.builder()
				.expiryDate(nextYear)
				.balance(0L)
				.build();
		switch (titles){
			case CIVIL -> card.setType(ECardType.STANDARD);
			case DISABLED -> card.setType(ECardType.HANDICAPPED);
			case EYT -> card.setType(ECardType.EYT);
			case GUARDS,JOURNALIST,MEDICINAL,TEACHER,SOLDIER,POLICE -> card.setType(ECardType.SPECIAL_FORCE);
			case STUDENT -> card.setType(ECardType.STUDENT);
			case VETERAN -> card.setType(ECardType.VETERAN);
		}
		return cardRepository.save(card);

	}

	public boolean existsByCardUUID(String uuid) {
		return cardRepository.existsByUuid(uuid);
	}
	
	public Optional<VwCardDetail> getCardDetail(String cardUuid) {
		return cardRepository.findByCardUuid(cardUuid);
	}
}