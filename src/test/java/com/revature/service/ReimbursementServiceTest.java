package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;

import com.revature.dao.ReimbursementRepository;
import com.revature.dto.LoginDTO;
import com.revature.dto.ReimbursementDTO;
import com.revature.encryption.PasswordUtils;
import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.BadReimbursementFormatException;
import com.revature.exceptions.BadReimbursmentAmountException;
import com.revature.exceptions.InvalidLoginException;
import com.revature.exceptions.InvalidUserRoleException;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.models.UserRoles;

public class ReimbursementServiceTest {

	private static ReimbursementRepository mockReimbursementRepository;
	private static Connection mockConnection;
	
	private ReimbursementService reimbursementService;
	
	@BeforeClass
	public static void setUp() {
		mockReimbursementRepository = mock(ReimbursementRepository.class);
		mockConnection = mock(Connection.class);
		
		Timestamp date = new Timestamp(0);
		User userAuthor = new User(1, "userEmp", "password", "john", "doe", "john@email.com",
				new UserRoles(1, "Employee"));
		User userResolver = new User(2, "userMngr", "password", "jane", "smith", "jane@email.com",
				new UserRoles(2, "Finance Manager"));
		ReimbursementStatus reimbStatus = new ReimbursementStatus(1, "Pending");
		ReimbursementType reimbType = new ReimbursementType(1, "Dining");
		
		Reimbursement reimb = new Reimbursement(1,200.00, date, date, "Hotel stay", null, userAuthor, userResolver, reimbStatus, reimbType);
		
		List<Reimbursement> reimbList = new ArrayList<>();
		reimbList.add(reimb);
		
		when(mockReimbursementRepository.getAllReimbsByEmployee(eq(userAuthor))).thenReturn(reimbList);
		when(mockReimbursementRepository.getAllReimbs()).thenReturn(reimbList);
	}
	
	
	@Before
	public void beforeTest() {
		reimbursementService = new ReimbursementService(mockReimbursementRepository);
	}
	
	@Test
	public void test_happyPath() throws BadParameterException, InvalidLoginException {
			User user = new User(1, "userEmp", "password", "john", "doe", "john@email.com",
					new UserRoles(1, "Employee"));
			
			Timestamp date = new Timestamp(0);
			User userAuthor = new User(1, "userEmp", "password", "john", "doe", "john@email.com",
					new UserRoles(1, "Employee"));
			User userResolver = new User(2, "userMngr", "password", "jane", "smith", "jane@email.com",
					new UserRoles(2, "Finance Manager"));
			ReimbursementStatus reimbStatus = new ReimbursementStatus(1, "Pending");
			ReimbursementType reimbType = new ReimbursementType(1, "Dining");
			
			Reimbursement reimb = new Reimbursement(1,200.00, date, date, "Hotel stay", null, userAuthor, userResolver, reimbStatus, reimbType);
			
			List<Reimbursement> reimbList = new ArrayList<>();
			reimbList.add(reimb);
			
			
			List<Reimbursement> actual = (List<Reimbursement>) reimbursementService.getReimbsByEmployee(user);

			List<Reimbursement> expected = reimbList;

			assertEquals(expected, actual);
		}
	
	@Test 
	public void test_userInputAmountIsLessThanOne() throws BadReimbursementFormatException {
		User user = new User(1, "userEmp", "password", "john", "doe", "john@email.com",
				new UserRoles(1, "Employee"));
		ReimbursementDTO reimbDTO = new ReimbursementDTO(-200, "Plane tickets", "Travel");
		
		try {
			reimbursementService.addReimb(reimbDTO, user);
			fail("Exception did not occur");
		} catch (BadReimbursmentAmountException e) {
			assertEquals(e.getMessage(), "Reimbursment amount must be greate than zero");
		}
	}
	
	@Test 
	public void test_userInputTypeIsBlank() throws BadReimbursmentAmountException {
		User user = new User(1, "userEmp", "password", "john", "doe", "john@email.com",
				new UserRoles(1, "Employee"));
		ReimbursementDTO reimbDTO = new ReimbursementDTO(200, "Plane tickets", " ");
		
		try {
			reimbursementService.addReimb(reimbDTO, user);
			fail("Exception did not occur");
		} catch (BadReimbursementFormatException e) {
			assertEquals(e.getMessage(), "Reimbursement type and description cannot be blank");
		}
	}
	
	@Test 
	public void test_userInputDescriptionIsBlank() throws BadReimbursmentAmountException {
		User user = new User(1, "userEmp", "password", "john", "doe", "john@email.com",
				new UserRoles(1, "Employee"));
		ReimbursementDTO reimbDTO = new ReimbursementDTO(200, " ", "Travel");
		
		try {
			reimbursementService.addReimb(reimbDTO, user);
			fail("Exception did not occur");
		} catch (BadReimbursementFormatException e) {
			assertEquals(e.getMessage(), "Reimbursement type and description cannot be blank");
		}
	}
	
	@Test 
	public void test_getAllReimbursments_WithValidCredentials() throws InvalidUserRoleException {		
		Timestamp date = new Timestamp(0);
		User userAuthor = new User(1, "userEmp", "password", "john", "doe", "john@email.com",
				new UserRoles(1, "Employee"));
		User userResolver = new User(2, "userMngr", "password", "jane", "smith", "jane@email.com",
				new UserRoles(2, "Finance Manager"));
		ReimbursementStatus reimbStatus = new ReimbursementStatus(1, "Pending");
		ReimbursementType reimbType = new ReimbursementType(1, "Dining");
		
		Reimbursement reimb = new Reimbursement(1,200.00, date, date, "Hotel stay", null, userAuthor, userResolver, reimbStatus, reimbType);
		
		List<Reimbursement> reimbList = new ArrayList<>();
		reimbList.add(reimb);
		
		List<Reimbursement> actual = reimbursementService.getAllreimbs(userResolver);
		List<Reimbursement> expected =  reimbList;
		
		assertEquals(expected, actual);
	}
	
	@Test 
	public void test_getAllReimbursments_WithOutValidCredentials() throws InvalidUserRoleException {		
		Timestamp date = new Timestamp(0);
		User userEmp = new User(1, "userEmp", "password", "john", "doe", "john@email.com",
				new UserRoles(1, "Employee"));
		User userMngr = new User(2, "userMngr", "password", "jane", "smith", "jane@email.com",
				new UserRoles(2, "Finance Manager"));
		ReimbursementStatus reimbStatus = new ReimbursementStatus(1, "Pending");
		ReimbursementType reimbType = new ReimbursementType(1, "Dining");
		
		Reimbursement reimb = new Reimbursement(1,200.00, date, date, "Hotel stay", null, userEmp, userMngr, reimbStatus, reimbType);
		
		List<Reimbursement> reimbList = new ArrayList<>();
		reimbList.add(reimb);
		
		try {
			reimbursementService.getAllreimbs(userEmp);
			fail("Exception did not occur");
		} catch (InvalidUserRoleException e) {
			assertEquals(e.getMessage(), "You don't have the neccessary credentials to access all reimbursements");
		}	
	}

	
}
