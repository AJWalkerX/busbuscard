package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.dto.request.CardExpirationSaveRequestDto;
import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.entity.CardExpiration;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
import com.ahmete.busbuscard.repository.CardRepository;
import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.EState;
import com.ahmete.busbuscard.utility.enums.ETitle;
import com.ahmete.busbuscard.views.VwCardDetail;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


@Service
public class CardService {
	
	private final CardRepository cardRepository;
	private final CardExpirationService cardExpirationService;

	private static int inActiveCardNumber ;

    public CardService(CardRepository cardRepository, CardExpirationService cardExpirationService) {
        this.cardRepository = cardRepository;
        this.cardExpirationService = cardExpirationService;
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
		throw new BusbusCardException(EErrorType.OVER_REQUEST_CARD_ERROR);
	}

	public String sellAnonymousCard() {
		Card card = cardRepository.findDistinctFirstByStateAndType(EState.PASSIVE, ECardType.STANDARD);
		if (card != null) {
			card.setState(EState.ACTIVE);
			cardRepository.save(card);
			inActiveCardNumber = inActiveCardNumber -1;
			return "Kart Satıldı! " +card.getUuid();
		}
		throw new BusbusCardException(EErrorType.OUT_OF_CARD_ANONYMOUS_ERROR);
	}

	private int getInactiveCardNumber(){
		return cardRepository.countAllByState(EState.PASSIVE);
	}


	public Optional<Card> findByUuid(String cardUuid) {
		return cardRepository.findByUuid(cardUuid);
	}

	public Card generateCardByTitle(ETitle titles) {
		Card card = Card.builder()
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
		card = cardRepository.save(card);
	 	cardExpirationService.save(CardExpirationSaveRequestDto.builder().cardId(card.getId()).build());
		return card;
	}

	public boolean existsByCardUUID(String uuid) {
		return cardRepository.existsByUuid(uuid);
	}
	
	public Optional<VwCardDetail> getCardDetail(String cardUuid) {
		Optional<VwCardDetail> byCardUuid = cardRepository.findByCardUuid(cardUuid);
		if (byCardUuid.isEmpty()) {
			throw new BusbusCardException(EErrorType.CARD_NOT_FOUND_ERROR);
		}
		return byCardUuid;
	}

	public LocalDate extendCardDate(String cardUuid) {
		Card myCard = findMyCard(cardUuid);
		if (myCard == null) {
			throw new BusbusCardException(EErrorType.CARD_NOT_FOUND_ERROR);
		}
		CardExpiration cardExpiration = cardExpirationService.findByCardId(myCard.getId());
		if (myCard.getType() != ECardType.STANDARD && cardExpiration.getExpirationDate() > System.currentTimeMillis()) {
			long oneYearMillis = 365L * 24 * 60 * 60 * 1000;
			long newExpiryDate = System.currentTimeMillis() + oneYearMillis;
			cardExpiration.setExpirationDate(newExpiryDate);

			cardExpirationService.save(CardExpirationSaveRequestDto.builder().cardId(myCard.getId())
							.expirationDate(cardExpiration.getExpirationDate())
					.build());
			return Instant.ofEpochMilli(newExpiryDate)
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
		}
		throw new BusbusCardException(EErrorType.INVALID_CARD_ERROR);
	}
}