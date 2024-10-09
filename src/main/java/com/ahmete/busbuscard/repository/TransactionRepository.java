package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Transaction;
import com.ahmete.busbuscard.views.VwTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}