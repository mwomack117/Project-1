package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursement_type")
public class ReimbursementType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "type_id")
	private int id;
	
	@Column(name = "reimb_type", length = 10) // LODGING, TRAVEL, FOOD, or OTHER.
	private String reimbType;

	public ReimbursementType() {
		super();
	}

	public ReimbursementType(int id, String reimbType) {
		super();
		this.id = id;
		this.reimbType = reimbType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReimbType() {
		return reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
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
		ReimbursementType other = (ReimbursementType) obj;
		if (id != other.id)
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementType [id=" + id + ", reimbType=" + reimbType + "]";
	}
	
	

	
	
}
