package com.ahmete.busbuscard.controller;

import com.ahmete.busbuscard.service.CardExpirationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardExpirationController {

    private final CardExpirationService cardExpirationService;
}
