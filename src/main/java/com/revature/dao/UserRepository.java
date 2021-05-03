package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.dto.LoginDTO;
import com.revature.dto.PostUserDTO;
import com.revature.encryption.PasswordUtils;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.utils.SessionUtility;

public class UserRepository {

	public User addUser(PostUserDTO userDTO, String securePass) throws UsernameAlreadyExistsException {

		Session session = SessionUtility.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

			User newUser = new User(0, userDTO.getUserName(), securePass, userDTO.getFirstName(),
					userDTO.getLastName(), userDTO.getEmail(), new UserRoles(1, "Employee"));

			session.save(newUser);

			tx.commit();

			return newUser;
		}


	public User getUserByUsernameAndPassword(LoginDTO loginDTO, String hashedUserInput) {
		Session session = SessionUtility.getSessionFactory().openSession();
		
		Query query = session.createQuery("SELECT u from User u WHERE  u.username = :username AND u.password = :password");
		query.setParameter("username", loginDTO.getUsername());
		query.setParameter("password", hashedUserInput);
		
		User user = (User) query.uniqueResult();
		if(user != null) {
			return user;
		} 
		
		return null;
	}

	public User checkIfUsernameExistsAlready(String username) {
		Session session = SessionUtility.getSessionFactory().openSession();

		Query usernameQuery = session.createQuery("FROM User where username = :username").setParameter("username", username);

		User user = (User) usernameQuery.uniqueResult();
		
		if (user != null ) {
			return user;
		} else {
			return null;
		}
	}
	
	public User checkIfEmailExistsAlready(String email) {
		Session session = SessionUtility.getSessionFactory().openSession();

		Query usernameQuery = session.createQuery("FROM User where email = :email").setParameter("email", email);

		User user = (User) usernameQuery.uniqueResult();
		
		if (user != null ) {
			return user;
		} else {
			return null;
		}
	}
}
