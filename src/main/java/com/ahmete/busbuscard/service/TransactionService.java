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
	private final Integer MIN_TRANSACTION_LIMIT = 20;
	private final Integer MAX_TRANSACTION_LIMIT = 500;

	public String addMoneyCash(MoneyTransactionDto dto) {
			Card card = cardService.findMyCard(dto.uuid());

			if (card == null) {
				throw new BusbusCardException(EErrorType.CARD_NOT_FOUND_ERROR);
			}
			if(dto.amount()== 0){
				throw new BusbusCardException(EErrorType.TRANSACTION_INSUFFICIENT_BALANCE);
			}
			if(dto.amount()<MIN_TRANSACTION_LIMIT||dto.amount()>MAX_TRANSACTION_LIMIT){
				throw new BusbusCardException(EErrorType.TRANSACTION_OUT_OF_BOUNDS);
			}
		Transaction transaction = Transaction.builder()
				.amount(dto.amount())
				.transactionType(ETransactionType.CASH)
				.cardId(card.getId())
				.build();
		card.setBalance(card.getBalance() + transaction.getAmount());
		cardService.save(card);
		transactionRepository.save(transaction);
		return "New busbus card balance: "+card.getBalance().toString();

	}

    public String addMoneyBank(BankTransactionDto dto) {
		Card card = cardService.findMyCard(dto.uuid());
		if (card == null) {
			throw new BusbusCardException(EErrorType.CARD_NOT_FOUND_ERROR);
		}
		if (dto.endYear() < Integer.parseInt(String.valueOf(LocalDate.now().getYear()))||dto.endMonth() < Integer.parseInt(String.valueOf(LocalDate.now().getMonthValue())) ) {
			throw new BusbusCardException(EErrorType.BANK_CARD_EXPIRY_DATE_ERROR);
		}
		if(dto.amount()==0){
			throw new BusbusCardException(EErrorType.TRANSACTION_INSUFFICIENT_BALANCE);
		}
		if(dto.amount()<MIN_TRANSACTION_LIMIT||dto.amount()>MAX_TRANSACTION_LIMIT){
			throw new BusbusCardException(EErrorType.TRANSACTION_OUT_OF_BOUNDS);
		}
		Transaction transaction = Transaction.builder()
				.amount(dto.amount())
				.transactionType(ETransactionType.BANK)
				.cardId(card.getId())
				.build();
		card.setBalance(card.getBalance() + transaction.getAmount());
		cardService.save(card);
		transactionRepository.save(transaction);
		return "New busbus card balance: "+card.getBalance().toString();
    }
	
	public List<VwTransactionDetail> getAllTransactionsList(String cardUuid) {
		Long cardId = cardService.findMyCardId(cardUuid);
		return transactionRepository.getAllTransactionByCardId(cardId);
	}
}