package com.revature.exceptions;

public class BadReimbursmentAmountException extends Exception {

	public BadReimbursmentAmountException() {
		
	}

	public BadReimbursmentAmountException(String message) {
		super(message);
		
	}

	public BadReimbursmentAmountException(Throwable cause) {
		super(cause);
		
	}

	public BadReimbursmentAmountException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public BadReimbursmentAmountException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
