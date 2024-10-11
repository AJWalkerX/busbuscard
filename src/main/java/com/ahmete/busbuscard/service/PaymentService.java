package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.entity.Payment;
import com.ahmete.busbuscard.repository.PaymentRepository;
import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.ETransport;
import com.ahmete.busbuscard.views.VwLatestPayment;
import com.ahmete.busbuscard.views.VwPaymentDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            if(freeTransferIsActive(card.getId())){
                ControlPayment(card, eTransport, 0);
                return "UCRETSİZ AKTARMA!";
            }
            int paymentAmount = calculatePayment(card, eTransport);
            if(checkCardBalance(paymentAmount, card.getId())){
                ControlPayment(card, eTransport, paymentAmount);
                return "BİİİP! " + paymentAmount;
            }
            return "YETERSİİZ BAKİYEE!";
        }
        return "ARKADAN BASMAYAN KALDI MI?";
    }

    private int calculatePayment(Card card, ETransport eTransport) {

        int paymentRate = (int) (BASE_VALUE * eTransport.getPaymentRate());
        int rawPaymentAmount = paymentRate + BASE_VALUE;
        int discountRate = (int) (BASE_VALUE * card.getType().getDiscountRate());

        if (card.getType() == ECardType.STANDARD){
            return rawPaymentAmount + discountRate;
        }
        else{
            return rawPaymentAmount - discountRate;
        }
    }

    private void ControlPayment(Card card, ETransport eTransport, int paymentAmount) {
        if (paymentAmount == 0) {
            savePayment(paymentAmount, card.getId(), eTransport);
        }else{
            card.setBalance(card.getBalance() - paymentAmount);
            cardService.save(card);
            savePayment(paymentAmount, card.getId(), eTransport);
        }

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

    private boolean checkCardBalance(int paymentAmount, Long cardId){
        return paymentAmount > cardId;
    }

    private Boolean freeTransferIsActive(Long cardId) {
        Optional<VwLatestPayment> latestPaymentOptional = paymentRepository.findLatestPaymentDateAndAmountByCardId(cardId);
        if (latestPaymentOptional.isPresent()) {
            VwLatestPayment latestPaymentVw = latestPaymentOptional.get();
            if (latestPaymentVw.getAmount() == 0){
                return false;
            }
            Long previousPaymentTime = latestPaymentVw.getPaymentDate();
            Long currentTime = System.currentTimeMillis();
            Long transferTimeLimit = 90L * 60L * 1000L;

            if (previousPaymentTime != null){
                return (currentTime - previousPaymentTime) <= transferTimeLimit;
            }
        }
        return false;
    }
    
    public List<VwPaymentDetail> getAllPaymentList(String cardUuid) {
        Long carId = cardService.findMyCardId(cardUuid);
        return paymentRepository.getAllPaymentDetailByCardId(carId);
    }
}