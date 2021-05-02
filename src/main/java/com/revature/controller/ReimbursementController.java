package com.revature.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dto.ReimbursementDTO;
import com.revature.dto.StatusDTO;
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
	
	private Handler getReimbursementsForUserHandler = ctx -> {
		User author = (User) ctx.sessionAttribute("currentlyLoggedInUser");
		
		List<Reimbursement> reimbs = reimbursementService.getReimbsByEmployee(author);
		
		ctx.status(201);
		ctx.json(reimbs);
	};
	
	// route working - no FE
	private Handler getAllReimbursementsHandler = ctx -> {
		List<Reimbursement> allReimbs = reimbursementService.getAllreimbs(); 
		
		ctx.status(201);
		ctx.json(allReimbs);
	};
	
	
	// check setupdata for working example no FE
	private Handler filterReimbursementsByStatusHandler = ctx -> {
		StatusDTO statusDTO = ctx.bodyAsClass(StatusDTO.class);
		
		List<Reimbursement> filteredList = reimbursementService.filterReimbsByStatus(statusDTO);
		
		ctx.json(filteredList);
	};
	
	private Handler changeReimbursementStatusHandler = ctx -> {
		// TODO
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.post("/reimbursements", addReimbursementHandler);
		app.get("/reimbursements", getReimbursementsForUserHandler);
		app.get("/reimbursements/users", getAllReimbursementsHandler);
		app.post("/reimbursements/status", filterReimbursementsByStatusHandler);
	}
	
	
	
}
