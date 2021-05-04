package com.revature.service;

import static org.junit.Assert.assertEquals;
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
import com.revature.encryption.PasswordUtils;
import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.InvalidLoginException;
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

	
}
