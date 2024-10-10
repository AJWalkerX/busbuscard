package com.ahmete.busbuscard.utility.enums;

import lombok.Getter;

@Getter
public enum ETransport {
	BUS (0.1),
	HIGHWAY (0.8),
	SUBWAY(0.4),
	FERRY (0.4);

	private double paymentRate;

	ETransport(double paymentRate){
		this.paymentRate = paymentRate;
	}

}