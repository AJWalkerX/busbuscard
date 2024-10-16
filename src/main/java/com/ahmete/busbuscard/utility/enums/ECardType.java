package com.ahmete.busbuscard.utility.enums;

import lombok.Getter;

@Getter
public enum ECardType {
	STANDARD(0.1),
	STUDENT(0.7),
	HANDICAPPED(0.7),
	SPECIAL_FORCE(0.4),
	EYT(0.65),
	VETERAN(0.99),
	PERSONNEL(0);

	private double discountRate;

	ECardType(double discountRate){
		this.discountRate = discountRate;
	}
	
}