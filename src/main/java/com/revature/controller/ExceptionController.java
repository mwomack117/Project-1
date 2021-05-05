package com.revature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dto.MessageDTO;
import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.BadReimbursementFormatException;
import com.revature.exceptions.BadReimbursmentAmountException;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.exceptions.InvalidLoginException;
import com.revature.exceptions.InvalidUserRoleException;
import com.revature.exceptions.UsernameAlreadyExistsException;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionController implements Controller {
	
	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	private ExceptionHandler<BadParameterException> badParameterExceptionHandler = (e, ctx) -> {
		logger.warn("A user provided a bad parameter. Exception message is: \n " + e.getMessage());
		ctx.status(400); 
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<InvalidLoginException> invalidLoginExceptionHandler = (e, ctx) -> {
		logger.warn("A user provided a bad parameter. Exception message is: \n " + e.getMessage());
		ctx.status(400); 
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<UsernameAlreadyExistsException> usernameAlreadyExistsExceptionHandler = (e, ctx) -> {
		logger.warn("A user provided a bad parameter. Exception message is: \n " + e.getMessage());
		ctx.status(400); 
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<EmailAlreadyExistsException> emailAlreadyExistsExceptionHandler = (e, ctx) -> {
		logger.warn("A user provided a bad parameter. Exception message is: \n " + e.getMessage());
		ctx.status(400); 
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<BadReimbursmentAmountException> badReimbursmentAmountExceptionHandler = (e, ctx) -> {
		logger.warn("A user provided a bad parameter. Exception message is: \n " + e.getMessage());
		ctx.status(400); 
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<BadReimbursementFormatException> badReimbursementFormatExceptionHandler = (e, ctx) -> {
		logger.warn("A user provided a bad parameter. Exception message is: \n " + e.getMessage());
		ctx.status(400); 
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<InvalidUserRoleException> invalidUserRoleExceptionHandler = (e, ctx) -> {
		logger.warn("A user provided a bad parameter. Exception message is: \n " + e.getMessage());
		ctx.status(400); 
		ctx.json(new MessageDTO(e.getMessage()));
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.exception(BadParameterException.class, badParameterExceptionHandler);
		app.exception(InvalidLoginException.class, invalidLoginExceptionHandler);
		app.exception(UsernameAlreadyExistsException.class, usernameAlreadyExistsExceptionHandler);
		app.exception(EmailAlreadyExistsException.class, emailAlreadyExistsExceptionHandler);
		app.exception(BadReimbursmentAmountException.class, badReimbursmentAmountExceptionHandler);
		app.exception(BadReimbursementFormatException.class, badReimbursementFormatExceptionHandler);
		app.exception(InvalidUserRoleException.class, invalidUserRoleExceptionHandler);
	}

}
