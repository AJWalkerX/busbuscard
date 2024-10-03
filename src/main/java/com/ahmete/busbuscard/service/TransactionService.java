package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
}