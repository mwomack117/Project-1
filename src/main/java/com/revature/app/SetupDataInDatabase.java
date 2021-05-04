package com.revature.app;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.encryption.PasswordUtils;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.utils.SessionUtility;

public class SetupDataInDatabase {

	public static void main(String[] args) {
		Session session = SessionUtility.getSessionFactory().openSession();
		
//		
//		Query query = session.createQuery("FROM Reimbursement WHERE reimbStatus = :status").setParameter("status", queryStatus);
//		List<Reimbursement> fullReimbList = query.list();
////		
//		System.out.println(fullReimbList);
//		System.out.println((fullReimbList).size());

//		User user = session.get(User.class, 5);
//		System.out.println(user);
		
		//Transaction tx = session.beginTransaction();
		// *** statusDTO -> pass in deny/approve value from button on FE
//		ReimbursementStatus queryStatus = (ReimbursementStatus) session.createQuery("FROM ReimbursementStatus WHERE reimbStatus='Denied'").getSingleResult();
//		System.out.println(queryStatus);
//		
//		User resolver = session.get(User.class, 1);
//		System.out.println(resolver);
//		
//		Date today = new Date();
//		System.out.println(today);
//		
//		
//		Reimbursement reimb = session.get(Reimbursement.class, 4);
//		System.out.println(reimb);
		
		//ReimbursementStatus queryStatus = (ReimbursementStatus) session
		//		.createQuery("FROM ReimbursementStatus WHERE reimbStatus='Approved'").getSingleResult();
		
//		Query query = session.createQuery("UPDATE Reimbursement r SET r.reimbStatus=:status, r.resolver=:resolver, r.reimbResolved=:date WHERE r.id=:id ")
//				.setParameter("status", queryStatus)
//				.setParameter("resolver", resolver)
//				.setParameter("date", today)
//				.setParameter("id", reimb.getId());
//		query.executeUpdate();
//		
//		tx.commit();

		// List<Reimbursement> reimbs = (List<Reimbursement>) session.createQuery("FROM
		// Reimbursement R WHERE R.author = :user").setParameter("user", user);
//		Query query = session.createQuery("FROM Reimbursement R WHERE R.author = :user").setParameter("user", user);
//		List<Reimbursement> reimbList = query.getResultList(); //no type warning
//		System.out.println(reimbList);
		// Query usernameQuery = session.createQuery("FROM User where username =
		// :username").setParameter("username", "mwally117");

		// User user = (User) usernameQuery.uniqueResult();
		// System.out.println(user);

//		Query query = session.createQuery("FROM Reimbursement r WHERE r.author.id = :id").setParameter("id", 5);
//		Reimbursement reimb = (Reimbursement) query.uniqueResult();
//		System.out.println(reimb);

		//Transaction tx = session.beginTransaction();
//
//		User user = session.get(User.class, 5);
////		System.out.println(user);
////		
//		Reimbursement reimb = new Reimbursement(0, 100.00, null, null, "Dinner with clients", null, user, null, new ReimbursementStatus(1, "Denied"), new ReimbursementType(3, "Travel"));
////		System.out.println(reimb);
//		session.save(reimb);
//		tx.commit();

//		// Setup Reimb Type table
//		ReimbursementType reimbType = new ReimbursementType(0, "Lodging");
//		ReimbursementType reimbType1 = new ReimbursementType(0, "Dining");
//		ReimbursementType reimbType2 = new ReimbursementType(0, "Travel");
//		ReimbursementType reimbType3 = new ReimbursementType(0, "Other");

//		// Setup Reimb Status table
//		ReimbursementStatus reimbStatus = new ReimbursementStatus(0, "Denied");
//		ReimbursementStatus reimbStatus2 = new ReimbursementStatus(0, "Approved");
//		ReimbursementStatus reimbStatus3 = new ReimbursementStatus(0, "Pending");
//		
//		// Setup UserRoles table
//		UserRoles ur =  new UserRoles(0, "Employee");
//		UserRoles ur2 =  new UserRoles(0, "Finance Manager");
		
		//String salt = PasswordUtils.getSalt(30);

		// Protect user's password. The generated value can be stored in DB.
		//String mySecurePassword = PasswordUtils.generateSecurePassword("password", salt);
//		String securePass1 = PasswordUtils.hashPassword("larry123");
//		String securePass2 = PasswordUtils.hashPassword("jen123");
//		User m1 = new User(0, "Larry117", securePass1, "Larry", "Johnson", "LarryJ@gmail.com",
//				new UserRoles(2, "Finance Manager"));
//		User m2 = new User(0, "Jen117", securePass2, "Jennifer", "Greer", "JenCGreer@yahoo.com",
//				new UserRoles(2, "Finance Manager"));
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
//		session.save(m1);
//		session.save(m2);
//
		//tx.commit();

	}

}
