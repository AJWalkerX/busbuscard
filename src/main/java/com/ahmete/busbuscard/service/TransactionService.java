package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.dto.request.BankTransactionDto;
import com.ahmete.busbuscard.dto.request.MoneyTransactionDto;
import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.entity.Transaction;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
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
			throw new BusbusCardException(EErrorType.CARD_NOT_FOUND_ERROR);
	}

    public ResponseEntity<String> addMoneyBank(BankTransactionDto dto) {
		Card card = cardService.findMyCard(dto.getUuid());
		if (dto.getEndYear() < Integer.parseInt(String.valueOf(LocalDate.now().getYear())) ) {
			throw new BusbusCardException(EErrorType.BANK_CARD_EXPIRY_DATE_ERROR);
		}
		if (dto.getEndMonth() < Integer.parseInt(String.valueOf(LocalDate.now().getMonthValue())) ) {
			throw new BusbusCardException(EErrorType.BANK_CARD_EXPIRY_DATE_ERROR);
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
	    throw new BusbusCardException(EErrorType.CARD_NOT_FOUND_ERROR);
    }
	
	public List<VwTransactionDetail> getAllTransactionsList(String cardUuid) {
		Long cardId = cardService.findMyCardId(cardUuid);
		return transactionRepository.getAllTransactionByCardId(cardId);
	}
}