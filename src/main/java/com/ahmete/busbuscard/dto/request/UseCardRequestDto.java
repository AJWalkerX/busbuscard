package com.ahmete.busbuscard.dto.request;

import com.ahmete.busbuscard.utility.enums.ETransport;

public record UseCardRequestDto(
		String card_uuid,
		ETransport eTransport

) {
	}