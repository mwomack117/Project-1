package com.revature.dto;

import java.util.Arrays;

import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;

public class ReimbursementDTO {
	// form data for receipt
	
	private double reimbAmount;
	private String reimbDescription;
	//private byte[] recieptPhoto;
	private String type;
	

	public ReimbursementDTO() {
		super();
	}


	public ReimbursementDTO(double reimbAmount, String reimbDescription, String type) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbDescription = reimbDescription;
		this.type = type;
	}

	
	
	public double getReimbAmount() {
		return reimbAmount;
	}


	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}


	public String getReimbDescription() {
		return reimbDescription;
	}


	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(reimbAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ReimbursementDTO other = (ReimbursementDTO) obj;
		if (Double.doubleToLongBits(reimbAmount) != Double.doubleToLongBits(other.reimbAmount))
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ReimbursementDTO [reimbAmount=" + reimbAmount + ", reimbDescription=" + reimbDescription + ", type="
				+ type + "]";
	}
	
	

	
	
}
