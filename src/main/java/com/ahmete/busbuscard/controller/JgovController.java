package com.ahmete.busbuscard.controller;

import com.ahmete.busbuscard.service.JgovService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jgov")
@RequiredArgsConstructor
public class JgovController {
	private final JgovService jgovService;
}