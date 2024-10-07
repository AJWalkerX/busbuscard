package com.ahmete.busbuscard.utility.enums;

public enum ECardType {
	STANDARD(1.1),
	STUDENT(0.7),
	HANDICAPPED(0.7),
	SPECIAL_FORCE(0.4),
	EYT(0.65),
	VETERAN(0.99);

	private double discountRate;

	ECardType(double discountRate){
		this.discountRate = discountRate;
	}

	public double getDiscountRate() {
		return discountRate;
	}
}