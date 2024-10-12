package com.ahmete.busbuscard.exception;

public class BusbusCardException extends RuntimeException {
	private EErrorType errorType;
	public BusbusCardException(EErrorType errorType){
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}