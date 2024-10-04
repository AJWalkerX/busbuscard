package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.entity.Transaction;
import com.ahmete.busbuscard.repository.CardRepository;
import com.ahmete.busbuscard.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository transactionRepository;
	private final CardService cardService;

	public void addMoney(Transaction transaction) {
		transactionRepository.save(transaction);
		Card card = cardService.findMyCard(transaction.getCardId());
		if (card != null) {
			card.setBalance(card.getBalance() + transaction.getAmount());
			cardService.save(card);
		}
	}
}