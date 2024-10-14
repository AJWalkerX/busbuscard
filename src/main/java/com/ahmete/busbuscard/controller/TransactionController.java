package com.ahmete.busbuscard.controller;

import static com.ahmete.busbuscard.constans.RestApi.*;

import com.ahmete.busbuscard.dto.request.BankTransactionDto;
import com.ahmete.busbuscard.dto.request.MoneyTransactionDto;
import com.ahmete.busbuscard.dto.response.BaseResponse;
import com.ahmete.busbuscard.service.CardService;
import com.ahmete.busbuscard.service.TransactionService;
import com.ahmete.busbuscard.views.VwPaymentDetail;
import com.ahmete.busbuscard.views.VwTransactionDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(TRANSACTION)
@RequiredArgsConstructor
public class TransactionController {
	private final TransactionService transactionService;
	private final CardService cardService;

	@PostMapping(ADD_MONEY_CASH)
	public ResponseEntity<BaseResponse<String>> addMoneyCash(MoneyTransactionDto dto){

		return ResponseEntity.ok(BaseResponse.<String>builder()
				.success(true)
				.code(200)
				.message("Money loaded successfully")
				.data(transactionService.addMoneyCash(dto))
				.build());
	}

	@PostMapping(ADD_MONEY_BANK)
	public ResponseEntity<BaseResponse<String>> addMoneyBank(BankTransactionDto dto){

		return ResponseEntity.ok(BaseResponse.<String>builder()
				.success(true)
				.code(200)
				.message("Money loaded successfully")
				.data(transactionService.addMoneyBank(dto))
				.build());
	}

	@GetMapping(GETTRANSACTIONDETAIL)
	public ResponseEntity<BaseResponse<List<VwTransactionDetail>>> getAllTransactionsDetails(String cardUuid){
		return ResponseEntity.ok(BaseResponse.<List<VwTransactionDetail>>builder()
						.success(true)
						.code(200)
						.message("List of transactions")
						.data(transactionService.getAllTransactionsList(cardUuid))
						.build());
	}
}