package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}