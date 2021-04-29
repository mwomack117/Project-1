package com.revature.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dto.ReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller{
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private ReimbursementService reimbursementService;

	public ReimbursementController() {
		this.reimbursementService = new ReimbursementService();
	}
	
	private Handler addReimbursementHandler = ctx -> {
		User author = (User) ctx.sessionAttribute("currentlyLoggedInUser");

		ReimbursementDTO reimbDTO = ctx.bodyAsClass(ReimbursementDTO.class);
		
		Reimbursement reimb = reimbursementService.addReimb(reimbDTO, author);
		
		ctx.json(reimb);
	};
	
	private Handler getAllReimbursementsForUser = ctx -> {
		User author = (User) ctx.sessionAttribute("currentlyLoggedInUser");
		
		List<Reimbursement> reimbs = reimbursementService.getReimbs(author);
		
		ctx.json(reimbs);
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.post("/reimbursement", addReimbursementHandler);
		app.get("/allReimbursements", getAllReimbursementsForUser);
	}
	
	
	
}
