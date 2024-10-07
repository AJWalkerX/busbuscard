package com.ahmete.busbuscard.controller;

import com.ahmete.busbuscard.service.JgovService;
import com.ahmete.busbuscard.service.UserService;
import com.ahmete.busbuscard.utility.enums.ETitle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jgov")
@RequiredArgsConstructor
public class JgovController {
	private final JgovService jgovService;
	private final UserService userService;

	/**
	 * localhost:9090/jgov/jgovregister
	 *
	 */

	@PostMapping("/jgovregister")
	public void jgovRegister(Long userTc, String address, ETitle title) {
		jgovService.save(userTc,address,title);
	}
}