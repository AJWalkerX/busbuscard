package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.dto.request.BankTransactionDto;
import com.ahmete.busbuscard.dto.request.MoneyTransactionDto;
import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.entity.Transaction;
import com.ahmete.busbuscard.repository.TransactionRepository;
import com.ahmete.busbuscard.utility.enums.ETransactionType;
import com.ahmete.busbuscard.views.VwTransactionDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository transactionRepository;
	private final CardService cardService;

	public ResponseEntity<String> addMoneyCash(MoneyTransactionDto dto) {
			Card card = cardService.findMyCard(dto.getUuid());
			if (card != null) {
				Transaction transaction = Transaction.builder()
						.amount(dto.getAmount())
						.transactionType(ETransactionType.CASH)
						.cardId(card.getId())
						.build();
				card.setBalance(card.getBalance() + transaction.getAmount());
				cardService.save(card);
				transactionRepository.save(transaction);
				return ResponseEntity.ok("New busbus card balance: "+card.getBalance().toString());
			}
			return ResponseEntity.notFound().build();
	}

    public ResponseEntity<String> addMoneyBank(BankTransactionDto dto) {
		Card card = cardService.findMyCard(dto.getUuid());
		if (dto.getEndYear() < Integer.parseInt(String.valueOf(LocalDate.now().getYear())) ) {
			return ResponseEntity.badRequest().body("<p style=\"color:red\">Invalid end year!</p>");
		}
		if (dto.getEndMonth() < Integer.parseInt(String.valueOf(LocalDate.now().getMonthValue())) ) {
			return ResponseEntity.badRequest().body("<p style=\"color:red\">Invalid end Month!</p>");
		}
		if (card != null) {
			Transaction transaction = Transaction.builder()
					.amount(dto.getAmount())
					.transactionType(ETransactionType.BANK)
					.cardId(card.getId())
					.build();
			card.setBalance(card.getBalance() + transaction.getAmount());
			cardService.save(card);
			transactionRepository.save(transaction);
			return ResponseEntity.ok("New busbus card balance: "+card.getBalance().toString());
		}
		return ResponseEntity.notFound().build();
    }
	
	public List<VwTransactionDetail> getAllTransactionsList(String cardUuid) {
		Long cardId = cardService.findMyCardId(cardUuid);
		return transactionRepository.getAllTransactionByCardId(cardId);
	}
}