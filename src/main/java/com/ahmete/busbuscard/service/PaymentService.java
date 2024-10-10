package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.entity.Payment;
import com.ahmete.busbuscard.repository.PaymentRepository;
import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.ETransport;
import com.ahmete.busbuscard.views.VwPaymentDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final int BASE_VALUE = 10;
    private final PaymentRepository paymentRepository;
    private final CardService cardService;

    public String useCard(String card_uuid, ETransport eTransport) {
        Optional<Card> optionalCard = cardService.findByUuid(card_uuid);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            if (card.getBalance() <= 0){
                return "YETERSİİZ BAKİYEE!";
            }
            if(freeTransferIsActive(card.getId())){

            }
            if(calculatePayment(card, eTransport)){
                return "BİİİP!";
            }
            return "YETERSİİZ BAKİYEE!";
        }
        return "ARKADAN BASMAYAN KALDI MI?";
    }

    private boolean calculatePayment(Card card, ETransport eTransport) {

        int paymentRate = (int) (BASE_VALUE * eTransport.getPaymentRate());
        int rawPaymentAmount = paymentRate + BASE_VALUE;
        int discountRate = (int) (BASE_VALUE * card.getType().getDiscountRate());
        int paymentAmount;
        if (card.getType() == ECardType.STANDARD){
            paymentAmount = rawPaymentAmount + discountRate;
        }
        else{
            paymentAmount = rawPaymentAmount - discountRate;
        }
        if (paymentAmount > card.getBalance()) {
            return false;
        }
        card.setBalance(card.getBalance() - paymentAmount);
		cardService.save(card);
        savePayment(paymentAmount, card.getId(), eTransport);
        return true;
    }

    private Boolean freeTransferIsActive(Long cardId) {
        Long previousPaymentTime = getPreviousPaymentTime(cardId);
        Long currentTime = System.currentTimeMillis();
        Long transferTimeLimit = 90L * 60L * 1000L;

        if (previousPaymentTime != null){
            return (currentTime - previousPaymentTime) <= transferTimeLimit;
        }
        return false;
    }

    private Long  getPreviousPaymentTime(Long cardId){
        Optional<Long> latestPaymentDateByCardId = paymentRepository.findLatestPaymentDateByCardId(cardId);
        if (latestPaymentDateByCardId.isPresent()){
            return latestPaymentDateByCardId.get();
        }
        return null;
    }

    private void savePayment(long paymentAmount, Long cardId, ETransport eTransport) {

        Payment payment = Payment.builder()
                .paymentDate(System.currentTimeMillis())
                .amount(paymentAmount)
                .cardId(cardId)
                .transport(eTransport)
                .build();
        paymentRepository.save(payment);
    }
    
    public List<VwPaymentDetail> getAllPaymentList(String cardUuid) {
        Long carId = cardService.findMyCardId(cardUuid);
        return paymentRepository.getAllPaymentDetailByCardId(carId);
    }
}