package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}