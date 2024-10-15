package com.ahmete.busbuscard.controller;

import static com.ahmete.busbuscard.constans.RestApi.*;

import com.ahmete.busbuscard.dto.request.ApplyCardRequestDto;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
import com.ahmete.busbuscard.service.JgovService;
import com.ahmete.busbuscard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<String> jgovRegister(@RequestBody @Valid ApplyCardRequestDto dto) {
		String card_uuid = jgovService.apply(dto);
		return ResponseEntity.ok(card_uuid);
	}


}