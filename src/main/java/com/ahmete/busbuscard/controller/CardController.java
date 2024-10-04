package com.ahmete.busbuscard.controller;

import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.repository.UserRepository;
import com.ahmete.busbuscard.service.CardService;
import com.ahmete.busbuscard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
	private final CardService cardService;
}