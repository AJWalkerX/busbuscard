package com.ahmete.busbuscard.controller;

import com.ahmete.busbuscard.dto.response.BaseResponse;
import com.ahmete.busbuscard.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.ahmete.busbuscard.constans.RestApi.*;
@RestController
@RequestMapping(TRANSPORT)
@RequiredArgsConstructor
public class TransportController {
	private final TransportService transportService;
	
	@GetMapping(GENERATE_PLATE)
	public ResponseEntity<BaseResponse<String>> generatePlate(){
		return ResponseEntity.ok(
				BaseResponse.<String >builder()
						.success(true)
						.code(200)
						.message("Success")
						.data(transportService.generatePlate())
				            .build()
						
		);
	}
	
	@GetMapping(GENERATE_TRANSPORT)
	public ResponseEntity<BaseResponse<Boolean>> addTransport(){
		return  ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
						.success(true)
						.code(200)
						.message("Success")
						.data(transportService.generateTransport())
				            .build()
		);
	}
}