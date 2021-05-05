package com.revature.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dto.ReimbursementDTO;
import com.revature.dto.StatusDTO;
import com.revature.dto.UpdateReimbDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;

public class ReimbursementController implements Controller{
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private ReimbursementService reimbursementService;

	public ReimbursementController() {
		this.reimbursementService = new ReimbursementService();
	}
	
	private Handler addReimbursementHandler = ctx -> {
		User author = (User) ctx.sessionAttribute("currentlyLoggedInUser");

		ReimbursementDTO reimbDTO = ctx.bodyAsClass(ReimbursementDTO.class);
		
		// user form data instead of json 
		Reimbursement reimb = reimbursementService.addReimb(reimbDTO, author);
		
		logger.info("Reimbursements successfully added for: " + author.getUsername());
		ctx.json(reimb);
		ctx.status(201);
	};
	
	////////////
//	private Handler addReimbursementHandler = ctx -> {
//		User author = (User) ctx.sessionAttribute("currentlyLoggedInUser");
//
//		UploadedFile upfile = ctx.uploadedFiles().get(0);
//		ReimbursementDTO reimbDTO = new ReimbursementDTO();
//		reimbDTO.setReimbAmount(ctx.formParam("reimbAmount"));
//		reimbDTO.setRecieptPhoto(ctx.formParam("recieptPhoto"));
//		reimbDTO.setReimbDescription(ctx.formParam("reimbDescription"));
//		reimbDTO.setType(ctx.formParam("type"));
//		
//		Reimbursement reimb = reimbursementService.addReimb(reimbDTO, author);
//		
//		ctx.json(reimb);
	//};
	//////////////
	
	private Handler getReimbursementsForUserHandler = ctx -> {
		User author = (User) ctx.sessionAttribute("currentlyLoggedInUser");
		
		List<Reimbursement> reimbs = reimbursementService.getReimbsByEmployee(author);
		
		logger.info("All reimbursements successfully retrieved for: " + author.getUsername());
		ctx.status(201);
		ctx.json(reimbs);
	};
	
	private Handler getAllReimbursementsHandler = ctx -> {
		List<Reimbursement> allReimbs = reimbursementService.getAllreimbs(); 
		
		logger.info("All reimbursements successfully retrieved");
		ctx.status(201);
		ctx.json(allReimbs);
	};
	
	private Handler filterReimbursementsByStatusHandler = ctx -> {
		StatusDTO statusDTO = ctx.bodyAsClass(StatusDTO.class);
		
		List<Reimbursement> filteredList = reimbursementService.filterReimbsByStatus(statusDTO);
		
		ctx.status(200);
		ctx.json(filteredList);
	};
	
	private Handler updateReimbursementHandler = ctx -> {
		User resolver = (User) ctx.sessionAttribute("currentlyLoggedInUser");
		
		UpdateReimbDTO updateDTO = ctx.bodyAsClass(UpdateReimbDTO.class);
		
		Reimbursement updatedReimb = reimbursementService.updateReimb(updateDTO, resolver);
		
		logger.info("Reimbursement was updated successfully for: " + resolver.getUsername());
		ctx.status(204);
		ctx.json(updatedReimb);
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.post("/reimbursements", addReimbursementHandler);
		app.get("/reimbursements", getReimbursementsForUserHandler);
		app.get("/reimbursements/users", getAllReimbursementsHandler);
		app.post("/reimbursements/status", filterReimbursementsByStatusHandler);
		app.put("/reimbursements/users/update", updateReimbursementHandler);
	}
	
	
	
}
