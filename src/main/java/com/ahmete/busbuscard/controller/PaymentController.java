package com.ahmete.busbuscard.controller;

import com.ahmete.busbuscard.service.PaymentService;
import com.ahmete.busbuscard.utility.enums.ETransport;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
	private final PaymentService paymentService;

	@PostMapping("/use-card")
	public String useCard(String card_uuid, ETransport eTransport){
		return paymentService.useCard(card_uuid, eTransport);
	}
}