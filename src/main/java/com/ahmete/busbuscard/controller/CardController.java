package com.ahmete.busbuscard.controller;

import static com.ahmete.busbuscard.constans.RestApi.*;

import com.ahmete.busbuscard.dto.response.BaseResponse;
import com.ahmete.busbuscard.service.CardService;
import com.ahmete.busbuscard.utility.enums.ETransport;
import com.ahmete.busbuscard.views.VwCardDetail;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(CARD)
@RequiredArgsConstructor
public class CardController {
	private final CardService cardService;

	@GetMapping(GENERATE_CARD)
	public ResponseEntity<BaseResponse<String>> generateAnonymousCard(){
		return ResponseEntity.ok(BaseResponse.<String>builder()
				                         .success(true)
				                         .code(200)
				                         .message("Success")
				                         .data(cardService.generateAnonymousCard())
		                                     .build());
	}

	@GetMapping(SELL_CARD)
	public ResponseEntity<BaseResponse<String>> sellAnonymousCard(){
		return ResponseEntity.ok(BaseResponse.<String>builder()
				                         .success(true)
				                         .code(200)
				                         .message("Success")
				                         .data(cardService.sellAnonymousCard())
		                                     .build());
	}
	
	@GetMapping(GETCARD)
	public ResponseEntity<BaseResponse<VwCardDetail>> getCardDetail(String cardUuid){
		return ResponseEntity.ok(BaseResponse.<VwCardDetail>builder()
				                         .success(true)
				                         .code(200)
				                         .message("Card detail")
				                         .data(cardService.getCardDetail(cardUuid).get())
		                                     .build());
	}

	@GetMapping(EXTENT_DATE)
	public ResponseEntity<BaseResponse<String>> extendExpiryDate(String cardUuid){
		return ResponseEntity.ok(BaseResponse.<String>builder()
						.success(true)
						.code(200)
						.message("Expiry date Expanded")
						.data("new expiry date: "+ cardService.extendCardDate(cardUuid).toString())
				.build());
	}

}