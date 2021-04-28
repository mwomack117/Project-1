package com.revature.service;

import com.revature.dao.UserRepository;
import com.revature.dto.LoginDTO;
import com.revature.dto.PostUserDTO;
import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.exceptions.InvalidLoginException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.models.User;

public class UserService {
	
	private UserRepository userRepository;
	
	public UserService() {
		this.userRepository = new UserRepository();
	}
	
	// For mockito
	public UserService(UserRepository userRepository) {
		this.userRepository = new UserRepository();
	}
	

	public User addUser(PostUserDTO userDTO) throws BadParameterException, UsernameAlreadyExistsException, EmailAlreadyExistsException {
		if (userDTO.getUserName().trim().equals("") || userDTO.getPassword().trim().equals("") || userDTO.getEmail().trim().equals("")) {
			throw new BadParameterException("Cannot have a blank username, password, or email");
		}
		if (userDTO.getFirstName().trim().equals("") || userDTO.getLastName().trim().equals("")) {
			throw new BadParameterException("Cannot have a blank firstname or lastname");
		}
		
		User checkUsername = userRepository.checkIfUsernameExistsAlready(userDTO.getUserName());
		User checkEmail = userRepository.checkIfEmailExistsAlready(userDTO.getEmail());
		
		if (checkUsername != null) {
			throw new UsernameAlreadyExistsException("Sorry, that username is taken.");
		}
		if (checkEmail != null) {
			throw new EmailAlreadyExistsException("Sorry, that email is taken.");
		}
		
		User user = userRepository.addUser(userDTO);
		return user;
		
	}

	public User login(LoginDTO loginDTO) throws BadParameterException, InvalidLoginException {
		if (loginDTO.getUsername().trim().equals("") || loginDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Cannot have blank username and/or password");
		}
		
		User user = userRepository.getUserByUsernameAndPassword(loginDTO);
		
		if (user == null) {
			throw new InvalidLoginException("User was not able to login with the supplied credentials");
		}
		
		return user;
	}

}
