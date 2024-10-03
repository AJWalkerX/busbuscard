package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.repository.JgovRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JgovService {
	@Autowired
	private JgovRepository jgovRepository;
}