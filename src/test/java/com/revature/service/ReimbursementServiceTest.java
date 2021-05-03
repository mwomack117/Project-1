package com.revature.service;

import static org.mockito.Mockito.mock;

import java.sql.Connection;

import org.junit.Before;
import org.junit.BeforeClass;

import com.revature.dao.ReimbursementRepository;

public class ReimbursementServiceTest {

	private static ReimbursementRepository mockReimbursementRepository;
	private static Connection mockConnection;
	
	private ReimbursementService reimbursementService;
	
	@BeforeClass
	public static void setUp() {
		mockReimbursementRepository = mock(ReimbursementRepository.class);
		
		
	}
	
	
	@Before
	public void beforeTest() {
		reimbursementService = new ReimbursementService(mockReimbursementRepository);
	}
	
}
