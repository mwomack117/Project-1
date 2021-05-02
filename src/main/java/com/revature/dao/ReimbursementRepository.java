package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.dto.ReimbursementDTO;
import com.revature.dto.StatusDTO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.utils.SessionUtility;

public class ReimbursementRepository {

	public Reimbursement addReimb(ReimbursementDTO reimbDTO, User author) {

		Session session = SessionUtility.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		ReimbursementStatus queryStatus = (ReimbursementStatus) session
				.createQuery("FROM ReimbursementStatus WHERE reimbStatus='Pending'").getSingleResult();
		Query queryType = session.createQuery("FROM ReimbursementType WHERE reimbType=:type").setParameter("type",
				reimbDTO.getType());
		ReimbursementType type = (ReimbursementType) queryType.uniqueResult();

		Reimbursement newReimb = new Reimbursement(0, reimbDTO.getReimbAmount(), null, null,
				reimbDTO.getReimbDescription(), null, author, null, queryStatus, type);

		session.save(newReimb);

		tx.commit();

		return newReimb;
	}

	public List<Reimbursement> getAllReimbsByEmployee(User author) {
		Session session = SessionUtility.getSessionFactory().openSession();

		Query query = session.createQuery("FROM Reimbursement R WHERE R.author = :author").setParameter("author",
				author);
		List<Reimbursement> reimbList = query.getResultList();

		return reimbList;
	}

	public List<Reimbursement> getAllReimbs() {
		Session session = SessionUtility.getSessionFactory().openSession();

		Query query = session.createQuery("FROM Reimbursement");
		List<Reimbursement> fullReimbList = query.list();

		return fullReimbList;
	}

	public List<Reimbursement> filterReimbsByStatus(StatusDTO statusDTO) {
		Session session = SessionUtility.getSessionFactory().openSession();

		ReimbursementStatus queryStatus = (ReimbursementStatus) session
				.createQuery("FROM ReimbursementStatus WHERE reimbStatus = :status").setParameter("status", statusDTO.getReimbStatus()).getSingleResult();

		Query filterQuery = session.createQuery("FROM Reimbursement WHERE reimbStatus = :status").setParameter("status", queryStatus);
		List<Reimbursement> filteredReimbList = filterQuery.list();

		return filteredReimbList;
	}

//	public ReimbursementType getReimbType (String type) {
//		Session session = SessionUtility.getSessionFactory().openSession();
//		
//		Query queryType = session.createQuery("FROM ReimbursementType WHERE reimbType=:type").setParameter("type", type);
//		ReimbursementType reimbType = (ReimbursementType) queryType.uniqueResult();
//		return reimbType;
//	}

}
