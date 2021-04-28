package com.revature.app;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.utils.SessionUtility;

public class SeupDataInDatabase {

	public static void main(String[] args) {
		Session session = SessionUtility.getSessionFactory().openSession();

		//Transaction tx = session.beginTransaction();

//		// Setup Reimb Type table
//		ReimbursementType reimbType = new ReimbursementType(0, "Lodging");
//		ReimbursementType reimbType1 = new ReimbursementType(0, "Dining");
//		ReimbursementType reimbType2 = new ReimbursementType(0, "Travel");
//		ReimbursementType reimbType3 = new ReimbursementType(0, "Other");
//
//		// Setup Reimb Status table
//		ReimbursementStatus reimbStatus = new ReimbursementStatus(0, "Denied");
//		ReimbursementStatus reimbStatus2 = new ReimbursementStatus(0, "Approved");
//		ReimbursementStatus reimbStatus3 = new ReimbursementStatus(0, "Pending");
//		
//		// Setup UserRoles table
//		UserRoles ur =  new UserRoles(0, "Employee");
//		UserRoles ur2 =  new UserRoles(0, "Finance Manager");
//
//		session.save(reimbType);
//		session.save(reimbType1);
//		session.save(reimbType2);
//		session.save(reimbType3);
//		session.save(reimbStatus);
//		session.save(reimbStatus2);
//		session.save(reimbStatus3);
//		session.save(ur);
//		session.save(ur2);

		//tx.commit();
		Query usernameQuery = session.createQuery("FROM User where username = :username").setParameter("username", "Mwally117");

		User user = (User) usernameQuery.uniqueResult();
		System.out.println(user);
		

	}

}
