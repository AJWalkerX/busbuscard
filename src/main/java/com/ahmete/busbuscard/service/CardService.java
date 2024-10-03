package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;
	
	
}