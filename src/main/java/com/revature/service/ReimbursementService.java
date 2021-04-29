package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementRepository;
import com.revature.dto.ReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;
import com.revature.models.User;

public class ReimbursementService {
	
	private ReimbursementRepository reimbursementRepository;
	
	public ReimbursementService() {
		this.reimbursementRepository = new ReimbursementRepository();
	}
	
	// for mockito
	public ReimbursementService(ReimbursementRepository reimbursementRepository) {
		this.reimbursementRepository = new ReimbursementRepository();
	}

	public Reimbursement addReimb(ReimbursementDTO reimbDTO, User author) {
		Reimbursement reimb = reimbursementRepository.addReimb(reimbDTO, author);
		return reimb;
	}

	public List<Reimbursement> getReimbs(User author) {
		List<Reimbursement> reimbs = reimbursementRepository.getAllReimbs(author);
		
		return reimbs;
	}

}
