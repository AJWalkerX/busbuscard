package com.ahmete.busbuscard.controller;

import static com.ahmete.busbuscard.constans.RestApi.*;

import com.ahmete.busbuscard.dto.request.ApplyCardRequestDto;
import com.ahmete.busbuscard.entity.Jgov;
import com.ahmete.busbuscard.service.JgovService;
import com.ahmete.busbuscard.service.UserService;
import com.ahmete.busbuscard.utility.enums.ETitle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(JGOV)
@RequiredArgsConstructor
public class JgovController {
	private final JgovService jgovService;
	private final UserService userService;

	/**
	 * localhost:9090/jgov/jgovregister
	 *
	 */

	@PostMapping(REGISTER)
	public ResponseEntity<String> jgovRegister(ApplyCardRequestDto dto) {
		String card_uuid = jgovService.apply(dto);
		if (!userService.existsByTC(String.valueOf(dto.getTc()))){
			return ResponseEntity.badRequest().body(null);
		}
		return ResponseEntity.ok(card_uuid);
	}
}