package com.revature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dto.LoginDTO;
import com.revature.dto.MessageDTO;
import com.revature.dto.PostUserDTO;
import com.revature.models.User;
import com.revature.service.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	public UserController() {
		this.userService = new UserService();
	}

	private Handler addUserHandler = ctx -> {
		PostUserDTO userDTO = ctx.bodyAsClass(PostUserDTO.class);

		User user = userService.addUser(userDTO);

		logger.info("A user was added to the database");
		ctx.json(user);
		ctx.status(201);
	};

	private Handler loginHandler = ctx -> {
		LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);

		User user = userService.login(loginDTO);

		ctx.sessionAttribute("currentlyLoggedInUser", user);
		ctx.json(user);
		ctx.status(200);
	};

	private Handler currentUserHandler = ctx -> {		
		User user = (User) ctx.sessionAttribute("currentlyLoggedInUser");

		if (user == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("User is not logged in!");
			ctx.json(messageDTO);
			ctx.status(400);
		} else {
			ctx.json(user);
		}
	};

	private Handler logoutHandler = (ctx) -> {
		
		ctx.req.getSession().invalidate();
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.post("/user/register", addUserHandler);
		app.post("/user/login", loginHandler);
		app.get("/user/current", currentUserHandler);
		app.post("/user/logout", logoutHandler);
	}

}
