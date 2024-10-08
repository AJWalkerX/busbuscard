package com.ahmete.busbuscard.controller;

import static com.ahmete.busbuscard.constans.RestApi.*;

import com.ahmete.busbuscard.dto.request.BankTransactionDto;
import com.ahmete.busbuscard.dto.request.MoneyTransactionDto;
import com.ahmete.busbuscard.service.CardService;
import com.ahmete.busbuscard.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TRANSACTION)
@RequiredArgsConstructor
public class TransactionController {
	private final TransactionService transactionService;
	private final CardService cardService;

	@PostMapping(ADD_MONEY_CASH)
	public ResponseEntity<String> addMoneyCashBank(MoneyTransactionDto dto){
		if(!cardService.existsByCardUUID(dto.getUuid())){
			return ResponseEntity.notFound().build();
		}
		return transactionService.addMoneyCash(dto);
	}

	@PostMapping(ADD_MONEY_BANK)
	public ResponseEntity<String> addMoneyCashBank(BankTransactionDto dto){
		if(!cardService.existsByCardUUID(dto.getUuid())){
			return ResponseEntity.notFound().build();
		}
		return transactionService.addMoneyBank(dto);
	}
}