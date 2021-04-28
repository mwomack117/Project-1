package com.revature.exceptions;

public class InvalidLoginException extends Exception {

	public InvalidLoginException() {

	}

	public InvalidLoginException(String message) {
		super(message);
		
	}

	public InvalidLoginException(Throwable cause) {
		super(cause);
		
	}

	public InvalidLoginException(String message, Throwable cause) {
		super(message, cause);
		
	}
	
	public InvalidLoginException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
