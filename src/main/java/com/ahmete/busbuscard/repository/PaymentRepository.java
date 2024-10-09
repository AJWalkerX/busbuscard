package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Payment;
import com.ahmete.busbuscard.views.VwPaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	@Query("select new com.ahmete.busbuscard.views.VwPaymentDetail(p.cardId,p.amount,p.transport,p.paymentDate) from Payment p")
	List<VwPaymentDetail> getAllPaymentDetailByCardId(String cardUuid);
}