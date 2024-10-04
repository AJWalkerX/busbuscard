package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.entity.Payment;
import com.ahmete.busbuscard.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final CardService cardService;

	public void payTicket(Payment payment) {
		Card card = cardService.findMyCard(payment.getCardId());
		if (card != null) {
			card.setBalance(card.getBalance()-payment.getAmount());
			cardService.save(card);
			paymentRepository.save(payment);
		}
	}
}