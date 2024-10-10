package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Transaction;
import com.ahmete.busbuscard.views.VwTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select new com.ahmete.busbuscard.views.VwTransactionDetail(t.amount,t.transDate,t.transactionType) from Transaction t")
    List<VwTransactionDetail> getAllTransactionByCardId(Long cardId);
}