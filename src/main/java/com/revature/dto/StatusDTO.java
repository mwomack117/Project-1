package com.revature.dto;

public class StatusDTO {
	
	private String reimbStatus;

	public StatusDTO() {
		super();
	}

	public StatusDTO(String reimbStatus) {
		super();
		this.reimbStatus = reimbStatus;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
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
		StatusDTO other = (StatusDTO) obj;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatusDTO [reimbStatus=" + reimbStatus + "]";
	}
	
	
	
}
