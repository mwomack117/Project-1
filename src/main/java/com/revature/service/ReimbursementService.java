package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementRepository;
import com.revature.dto.ReimbursementDTO;
import com.revature.dto.StatusDTO;
import com.revature.dto.UpdateReimbDTO;
import com.revature.exceptions.BadReimbursementFormatException;
import com.revature.exceptions.BadReimbursmentAmountException;
import com.revature.exceptions.InvalidUserRoleException;
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
		this.reimbursementRepository = reimbursementRepository;
	}

	public Reimbursement addReimb(ReimbursementDTO reimbDTO, User author) throws BadReimbursmentAmountException, BadReimbursementFormatException {
		if(reimbDTO.getReimbAmount() < 1) {
			throw new BadReimbursmentAmountException("Reimbursment amount must be greate than zero");
		}
		if(reimbDTO.getReimbDescription().trim().equals("") || reimbDTO.getType().trim().equals("")) {
			throw new BadReimbursementFormatException("Reimbursement type and description cannot be blank");
		}
		
		Reimbursement reimb = reimbursementRepository.addReimb(reimbDTO, author);
		return reimb;
	}

	public List<Reimbursement> getReimbsByEmployee(User author) {
		List<Reimbursement> reimbs = reimbursementRepository.getAllReimbsByEmployee(author);
		
		return reimbs;
	}

	public List<Reimbursement> getAllreimbs(User author) throws InvalidUserRoleException {
		if (author.getUserRoles().getId() != 2) {
			throw new InvalidUserRoleException("You don't have the neccessary credentials to access all reimbursements");
		}
		
		List<Reimbursement> allReimbs = reimbursementRepository.getAllReimbs();
		
		return allReimbs;
	}

	public List<Reimbursement> filterReimbsByStatus(StatusDTO statusDTO) {
		List<Reimbursement> filteredReimbs = reimbursementRepository.filterReimbsByStatus(statusDTO);
		
		return filteredReimbs;
	}

	// add junit tests for this method
	public Reimbursement updateReimb(UpdateReimbDTO updateDTO, User resolver) throws InvalidUserRoleException {
		if (resolver.getUserRoles().getId() != 2) {
			throw new InvalidUserRoleException("You don't have the neccessary credentials to update reimbursements");
		}
		
		Reimbursement updatedReimb = reimbursementRepository.updateReimb(updateDTO, resolver);
		
		return updatedReimb;
	}

}
