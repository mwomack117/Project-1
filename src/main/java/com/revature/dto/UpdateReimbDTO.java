package com.revature.dto;

public class UpdateReimbDTO {

	private String newStatus;
	private int reimbId;
	
	public UpdateReimbDTO() {
		super();
	}

	public UpdateReimbDTO(String newStatus, int reimbId) {
		this.newStatus = newStatus;
		this.reimbId = reimbId;
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((newStatus == null) ? 0 : newStatus.hashCode());
		result = prime * result + reimbId;
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
		UpdateReimbDTO other = (UpdateReimbDTO) obj;
		if (newStatus == null) {
			if (other.newStatus != null)
				return false;
		} else if (!newStatus.equals(other.newStatus))
			return false;
		if (reimbId != other.reimbId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpdateReimbDTO [newStatus=" + newStatus + ", reimbId=" + reimbId + "]";
	}
	
	
	
	
}
