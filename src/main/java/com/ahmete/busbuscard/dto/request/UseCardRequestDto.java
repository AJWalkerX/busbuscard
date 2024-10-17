package com.ahmete.busbuscard.dto.request;

import com.ahmete.busbuscard.utility.enums.ETransportType;

public record UseCardRequestDto(
		String card_uuid,
		ETransportType eTransport

) {
	}