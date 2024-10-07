package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Jgov;
import com.ahmete.busbuscard.repository.JgovRepository;
import com.ahmete.busbuscard.utility.enums.ETitle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JgovService {

	private final JgovRepository jgovRepository;

	public void save(Long userTc, String address, ETitle titles) {
		Jgov jgov = Jgov.builder().userId(userTc).address(address).titles(titles).build();
		jgovRepository.save(jgov);
	}

}