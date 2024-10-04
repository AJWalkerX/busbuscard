package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Jgov;
import com.ahmete.busbuscard.repository.JgovRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JgovService {

	private final JgovRepository jgovRepository;

	public void save(Jgov jgov) {
		jgovRepository.save(jgov);
	}
}