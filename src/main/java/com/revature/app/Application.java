package com.revature.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import com.revature.controller.ReimbursementController;
import com.revature.controller.UserController;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.utils.SessionUtility;
import com.revature.utils.TestHibernate;

import io.javalin.Javalin;

public class Application {

	private static Javalin app;
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {


		app = Javalin.create((config) -> {
			config.addStaticFiles("static");
		});
		app.before(ctx -> {
			String URI = ctx.req.getRequestURI();
			String httpMethod = ctx.req.getMethod();
			logger.info(httpMethod + " request to endpoint " + URI + " received");
		});
		mapControllers(new UserController(), new ExceptionController(), new ReimbursementController());

		app.start(7001);

	}

	// Create a helper method
	public static void mapControllers(Controller... controllers) {

		for (Controller c : controllers) {
			c.mapEndpoints(app);
		}

	}

}
