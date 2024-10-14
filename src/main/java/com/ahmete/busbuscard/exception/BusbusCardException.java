package com.ahmete.busbuscard.exception;

import lombok.Getter;

@Getter
public class BusbusCardException extends RuntimeException {
	private EErrorType errorType;
	public BusbusCardException(EErrorType errorType){
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}