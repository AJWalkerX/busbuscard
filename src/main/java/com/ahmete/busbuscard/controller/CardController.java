package com.ahmete.busbuscard.controller;

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
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
	private final CardService cardService;

	@GetMapping("/generate-card")
	public String generateAnonymousCard(){
		return cardService.generateAnonymousCard();
	}

	@GetMapping("/sell-card")
	public String sellAnonymousCard(){
		return cardService.sellAnonymousCard();
	}


}