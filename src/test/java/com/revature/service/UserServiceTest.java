package com.revature.service;

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
import static org.junit.Assert.fail;
import org.mockito.MockedStatic;

import com.revature.dao.UserRepository;
import com.revature.dto.LoginDTO;
import com.revature.dto.PostUserDTO;
import com.revature.encryption.PasswordUtils;
import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.exceptions.InvalidLoginException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.models.User;
import com.revature.models.UserRoles;

public class UserServiceTest {

	// Fake repository dependency (mocked w/ Mockito)
	private static UserRepository mockUserRepository;

	private UserService userService;
	private static Connection mockConnection;

	@BeforeClass
	public static void setUp() {
		mockUserRepository = mock(UserRepository.class);
		mockConnection = mock(Connection.class);

		// mock user Employee
		User userEmployee = new User(1, "userEmp", "password", "john", "doe", "john@email.com",
				new UserRoles(1, "Employee"));
		when(mockUserRepository.getUserByUsernameAndPassword(eq(new LoginDTO("userEmp", "password")), eq("12345")))
				.thenReturn(userEmployee);
		
		when(mockUserRepository.checkIfUsernameExistsAlready(eq("userEmp"))).thenReturn(userEmployee);
		when(mockUserRepository.checkIfEmailExistsAlready(eq("john@email.com"))).thenReturn(userEmployee);
		
		// mock user Manager
		User userManager = new User(2, "userMngr", "password", "jane", "smith", "jane@emial.com",
				new UserRoles(2, "Finance Manager"));
		when(mockUserRepository.getUserByUsernameAndPassword(eq(new LoginDTO("userEngr", "password")), eq("12345")))
				.thenReturn(userEmployee);
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

			User expected = new User(1, "userEmp", "password", "john", "doe", "john@email.com",
					new UserRoles(1, "Employee"));

			assertEquals(expected, actual);
		}
	}

	@Test
	public void test_BlankUsernameAndPasswordWithoutSpaces() throws InvalidLoginException {
		LoginDTO blankInputDTO = new LoginDTO("", "");

		try {
			userService.login(blankInputDTO);
			fail("Exception did not occur");
		} catch (BadParameterException e) {
			assertEquals(e.getMessage(), "Cannot have blank username and/or password");
		}
	}
	
	@Test
	public void test_BlankUsernameAndPasswordBothWithSpaces() throws InvalidLoginException {
		LoginDTO blankInputDTO = new LoginDTO("  ", "   ");

		try {
			userService.login(blankInputDTO);
			fail("Exception did not occur");
		} catch (BadParameterException e) {
			assertEquals(e.getMessage(), "Cannot have blank username and/or password");
		}
	}

	@Test
	public void test_login_BlankUsernameWithSpaces_BlankPasswordWithoutSpaces() throws InvalidLoginException {
		LoginDTO blankInputDTO = new LoginDTO("       ", "");

		try {
			userService.login(blankInputDTO);
			fail("Exception did not occur");
		} catch (BadParameterException e) {
			assertEquals(e.getMessage(), "Cannot have blank username and/or password");
		}
	}

	@Test
	public void test_login_BlankUsernameWithoutSpaces_BlankPasswordWithSpaces() throws InvalidLoginException {
		LoginDTO blankInputDTO = new LoginDTO("", "      ");

		try {
			userService.login(blankInputDTO);
			fail("Exception did not occur");
		} catch (BadParameterException e) {
			assertEquals(e.getMessage(), "Cannot have blank username and/or password");
		}
	}

	@Test
	public void test_IncorrectLoginCredentials() throws InvalidLoginException, BadParameterException {
		try (MockedStatic<PasswordUtils> mockedPasswordUtils = mockStatic(PasswordUtils.class)) {
			mockedPasswordUtils.when(() -> PasswordUtils.hashPassword("password")).thenReturn("12345");

			try {
				userService.login(new LoginDTO("userEmp", "wrongPass"));
				fail("Exception did not occur");
			} catch (InvalidLoginException e) {
				assertEquals(e.getMessage(), "User was not able to login with the supplied credentials");
			}

		}
	}
	
	@Test
	public void test_AddUser_BlankUsername() throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
		PostUserDTO postUserDTO = new PostUserDTO(" ", "pw", "Mike", "Smith", "mike@email.com");
		
		try {
			userService.addUser(postUserDTO);
			fail("Exception did not occur");
		} catch (BadParameterException e) {
			assertEquals(e.getMessage(), "Cannot have a blank username, password, or email");
		}
	}
	
	@Test
	public void test_AddUser_BlankPassword() throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
		PostUserDTO postUserDTO = new PostUserDTO("user123", "  ", "Mike", "Smith", "mike@email.com");
		
		try {
			userService.addUser(postUserDTO);
			fail("Exception did not occur");
		} catch (BadParameterException e) {
			assertEquals(e.getMessage(), "Cannot have a blank username, password, or email");
		}
	}
	
	@Test
	public void test_AddUser_BlankEmail() throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
		PostUserDTO postUserDTO = new PostUserDTO("user123", "pw", "Mike", "Smith", "  ");
		
		try {
			userService.addUser(postUserDTO);
			fail("Exception did not occur");
		} catch (BadParameterException e) {
			assertEquals(e.getMessage(), "Cannot have a blank username, password, or email");
		}
	}
	
	@Test
	public void test_AddUser_BlankUsername_And_BlankPassword() throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
		PostUserDTO postUserDTO = new PostUserDTO(" ", "  ", "Mike", "Smith", "mike@email.com");
		
		try {
			userService.addUser(postUserDTO);
			fail("Exception did not occur");
		} catch (BadParameterException e) {
			assertEquals(e.getMessage(), "Cannot have a blank username, password, or email");
		}
	}
	
	@Test
	public void test_AddUser_BlankUsername_BlankPassword_blankEmail() throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
		PostUserDTO postUserDTO = new PostUserDTO(" ", " ", "Mike", "Smith", "  ");
		
		try {
			userService.addUser(postUserDTO);
			fail("Exception did not occur");
		} catch (BadParameterException e) {
			assertEquals(e.getMessage(), "Cannot have a blank username, password, or email");
		}
	}
	
	@Test
	public void test_AddUser_BlankPassword_blankEmail() throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
		PostUserDTO postUserDTO = new PostUserDTO("user123", " ", "Mike", "Smith", "  ");
		
		try {
			userService.addUser(postUserDTO);
			fail("Exception did not occur");
		} catch (BadParameterException e) {
			assertEquals(e.getMessage(), "Cannot have a blank username, password, or email");
		}
	}
	
	@Test
	public void test_AddUser_BlankUsername_blankEmail() throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
		PostUserDTO postUserDTO = new PostUserDTO(" ", "pw", "Mike", "Smith", " ");
		
		try {
			userService.addUser(postUserDTO);
			fail("Exception did not occur");
		} catch (BadParameterException e) {
			assertEquals(e.getMessage(), "Cannot have a blank username, password, or email");
		}
	}
	
	@Test
	public void test_UsernameAlreadyTaken() throws UsernameAlreadyExistsException, EmailAlreadyExistsException, BadParameterException {
		PostUserDTO postUserDTO = new PostUserDTO("userEmp", "pw", "Mike", "Smith", "user@email.com");
		
		try {
			userService.addUser(postUserDTO);
			fail("Exception did not occur");
		} catch (UsernameAlreadyExistsException e) {
			assertEquals(e.getMessage(), "Sorry, that username is taken.");
		}
	}
	
	@Test
	public void test_EmailAlreadyTaken() throws BadParameterException, UsernameAlreadyExistsException  {
		PostUserDTO postUserDTO = new PostUserDTO("user123", "pw", "Mike", "Smith", "john@email.com");
		
		try {
			userService.addUser(postUserDTO);
			fail("Exception did not occur");
		} catch (EmailAlreadyExistsException e) {
			assertEquals(e.getMessage(), "Sorry, that email is taken.");
		}
	}


}
