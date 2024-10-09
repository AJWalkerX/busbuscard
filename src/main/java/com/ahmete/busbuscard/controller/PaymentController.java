package com.ahmete.busbuscard.controller;

import static com.ahmete.busbuscard.constans.RestApi.*;

import com.ahmete.busbuscard.dto.response.BaseResponse;
import com.ahmete.busbuscard.service.PaymentService;
import com.ahmete.busbuscard.utility.enums.ETransport;
import com.ahmete.busbuscard.views.VwPaymentDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PAYMENT)
@RequiredArgsConstructor
public class PaymentController {
	private final PaymentService paymentService;

	@PostMapping(USE_CARD)
	public String useCard(String card_uuid, ETransport eTransport){
		return paymentService.useCard(card_uuid, eTransport);
	}
	
	@GetMapping(GETPAYMENTDETAIL)
	public ResponseEntity<BaseResponse<List<VwPaymentDetail>>> getAllPaymentDetails(String cardUuid){
		return ResponseEntity.ok(BaseResponse.<List<VwPaymentDetail>>builder()
				                         .success(true)
				                         .code(200)
				                         .message("List of payments")
				                         .data(paymentService.getAllPaymentList(cardUuid))
		                                     .build());
	}
}