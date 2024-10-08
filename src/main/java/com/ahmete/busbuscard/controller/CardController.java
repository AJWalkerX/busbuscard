package com.ahmete.busbuscard.controller;

import static com.ahmete.busbuscard.constans.RestApi.*;

import com.ahmete.busbuscard.service.CardService;
import com.ahmete.busbuscard.utility.enums.ETransport;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CARD)
@RequiredArgsConstructor
public class CardController {
	private final CardService cardService;

	@GetMapping(GENERATE_CARD)
	public String generateAnonymousCard(){
		return cardService.generateAnonymousCard();
	}

	@GetMapping(SELL_CARD)
	public String sellAnonymousCard(){
		return cardService.sellAnonymousCard();
	}


}