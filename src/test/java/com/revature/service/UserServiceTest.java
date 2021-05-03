package com.revature.service;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;


import java.sql.Connection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockedStatic;

import com.revature.dao.UserRepository;
import com.revature.dto.LoginDTO;
import com.revature.dto.PostUserDTO;
import com.revature.encryption.PasswordUtils;
import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.InvalidLoginException;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.utils.SessionUtility;

public class UserServiceTest {

	// Fake repository dependency (mocked w/ Mockito)
	private static UserRepository mockUserRepository;
	
	private UserService userService;
	private static Connection mockConnection;
	
	
	@BeforeClass
	public static void setUp() {
		mockUserRepository = mock(UserRepository.class);
		mockConnection = mock(Connection.class);
		
		//when(mockUserRepository.getUserByUsernameAndPassword(eq( new LoginDTO("testUser", "testpass"), "wrongPass"))).thenReturn(null);
		
		// mock user Employee
		User userEmployee = new User(1, "userEmp", "password", "john", "doe", "john@email.com", new UserRoles(1, "Employee"));
		when(mockUserRepository.getUserByUsernameAndPassword(eq(new LoginDTO("userEmp", "password")), eq("12345"))).thenReturn(userEmployee);
		// mock user Employee
		User userManager = new User(2, "userMngr", "password", "jane", "smith", "jane@emial.com", new UserRoles(2, "Finance Manager"));
		
	}
	
	@Before
	public void beforeTest() {
		userService = new UserService(mockUserRepository);
	}
	
	@Test
	public void test_happyPath() throws BadParameterException, InvalidLoginException {
		try (MockedStatic<PasswordUtils> mockedPasswordUtils = mockStatic(PasswordUtils.class)) {
			mockedPasswordUtils.when(() -> PasswordUtils.hashPassword("password")).thenReturn("12345");
		
			User actual = userService.login(new LoginDTO("userEmp", "password"));

			User expected = new User(1,"userEmp", "password", "john", "doe", "john@email.com", new UserRoles(1, "Employee"));

			assertEquals(expected, actual);
		}
	}
	
}
