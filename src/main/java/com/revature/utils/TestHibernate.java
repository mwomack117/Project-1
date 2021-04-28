package com.revature.utils;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRoles;

public class TestHibernate {
	
	public static void Test() {
		
		//Session session = SessionUtility.getSession();
		
		//Transaction tx = session.beginTransaction();
		//User user = new User(0, "Mwomack117", "password", "Michael", "Womack", "mike@email.com", null);
//	System.out.println(user);
		
		//User user = new User(0, "Janne", "pw123", "Janet", "Johnson", "jane@user.com", new UserRoles(1, "Employee"));
		//UserRoles userRoles = new UserRoles(0, "Finance Manager");
		
		//ReimbursementType reimbType = new ReimbursementType(0, "Lodging");
		//ReimbursementType reimbType2 = new ReimbursementType(0, "Travel");
		//ReimbursementType reimbType3 = new ReimbursementType(0, "Other");
		//ReimbursementStatus reimbStatus = new ReimbursementStatus(0, "Denied");
		//ReimbursementStatus reimbStatus2 = new ReimbursementStatus(0, "Approved");
		//User user = session.get(User.class, 1);
		//UserRoles ur = session.get(UserRoles.class, 2);
		//user.setUserRoles(ur);
		//System.out.println(user);
		//ReimbursementStatus reimbStatus = session.get(ReimbursementStatus.class, 1);
		//ReimbursementType reimbType = session.get(ReimbursementType.class, 2);
		//Reimbursement reimb = session.get(Reimbursement.class, 3);
		//System.out.println(reimb);
		
		//List<Reimbursement> reimbs = (List<Reimbursement>) session.createQuery("FROM Reimbursement R WHERE reimbAmount > 200").getResultList();
		//System.out.println(reimbs);
		
		//Reimbursement reimb = new Reimbursement(0, 300.00, null, null, "Plane Tickets", null, user, null, reimbStatus, reimbType);
		//reimb.setAuthor(user);
		//reimb.setReimbStatus(reimbStatus);
		//reimb.setReimbType(reimbType);
//	
		//session.save(user);
		//session.save(user);
		//session.save(reimb);
		//session.save(reimbType);
		//session.save(reimbType2);
		//session.save(reimbType3);
		//session.save(reimbStatus);
		
	//	tx.commit();
		
		//User user = session.get(User.class, 1); // .get user object is in the persistent state
//	System.out.println(user);
//	
//	Transaction tx = session.beginTransaction();
//	
		//UserRoles userRoles = new UserRoles(0, "Employee");
//	
		//session.save(userRoles);
		
		//user.setUserRoles(userRoles);
		//tx.commit();
//	
//	User u2 = session.get(User.class, 1);
//	System.out.println(u2);
	}


}
