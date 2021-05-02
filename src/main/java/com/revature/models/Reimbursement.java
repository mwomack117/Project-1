package com.revature.models;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "reimb_id")
	private int id;
	
	@Column(name = "reimb_amount")
	private double reimbAmount;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reimb_submitted")
	private Date reimbSubmitted;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reimb_resolved", nullable = true)
	private Date reimbResolved;
	
	@Column(name = "reimb_description", length = 250)
	private String reimbDescription;
	
	@Column(name = "reimb_receipt")
	@Lob
	private byte[] receipt;
	
	@ManyToOne // many reimbs belong to one author
	@JoinColumn(name = "reimb_author") // FK 
	private User author;
	
	@ManyToOne // many reimbs belong to one resolver
	@JoinColumn(name = "reimb_resolver") // FK 
	private User resolver;
	
	@ManyToOne // many reimbs belong to one status
	@JoinColumn(name = "reimb_status_id") // FK 
	private ReimbursementStatus reimbStatus;
	
	@ManyToOne // many reimbs with one type
	@JoinColumn(name = "reimb_type_id") // FK 
	private  ReimbursementType reimbType;
	
	

	public Reimbursement() {
		super();
	}



	public Reimbursement(int id, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbDescription, byte[] receipt, User author, User resolver, ReimbursementStatus reimbStatus,
			ReimbursementType reimbType) {
		super();
		this.id = id;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public Date getReimbSubmitted() {
		return reimbSubmitted;
	}

	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}

	public Date getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(Timestamp reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}


	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public ReimbursementStatus getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(ReimbursementStatus reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public ReimbursementType getReimbType() {
		return reimbType;
	}

	public void setReimbType(ReimbursementType reimbType) {
		this.reimbType = reimbType;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + id;
		result = prime * result + Arrays.hashCode(receipt);
		long temp;
		temp = Double.doubleToLongBits(reimbAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + ((reimbResolved == null) ? 0 : reimbResolved.hashCode());
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + ((reimbSubmitted == null) ? 0 : reimbSubmitted.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id != other.id)
			return false;
		if (!Arrays.equals(receipt, other.receipt))
			return false;
		if (Double.doubleToLongBits(reimbAmount) != Double.doubleToLongBits(other.reimbAmount))
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbResolved == null) {
			if (other.reimbResolved != null)
				return false;
		} else if (!reimbResolved.equals(other.reimbResolved))
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbSubmitted == null) {
			if (other.reimbSubmitted != null)
				return false;
		} else if (!reimbSubmitted.equals(other.reimbSubmitted))
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", reimbAmount=" + reimbAmount + ", reimbSubmitted=" + reimbSubmitted
				+ ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription + ", receipt="
				+ Arrays.toString(receipt) + ", author=" + author + ", resolver=" + resolver + ", reimbStatus="
				+ reimbStatus + ", reimbType=" + reimbType + "]";
	}
	
	

}
