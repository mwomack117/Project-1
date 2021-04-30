package com.revature.exceptions;

public class BadReimbursementFormatException extends Exception {

	public BadReimbursementFormatException() {
		
	}

	public BadReimbursementFormatException(String message) {
		super(message);
		
	}

	public BadReimbursementFormatException(Throwable cause) {
		super(cause);
		
	}

	public BadReimbursementFormatException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public BadReimbursementFormatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
