package com.revature.exceptions;

public class InvalidUserRoleException extends Exception {

	public InvalidUserRoleException() {
		
	}

	public InvalidUserRoleException(String message) {
		super(message);
		
	}

	public InvalidUserRoleException(Throwable cause) {
		super(cause);
		
	}

	public InvalidUserRoleException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public InvalidUserRoleException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
