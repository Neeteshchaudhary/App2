package com.eminosoft.eazTravels.util;

public class StatusMessage {
	
	private String message;
	private boolean status;
	private int reasonForRevisitId;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getReasonForRevisitId() {
		return reasonForRevisitId;
	}

	public void setReasonForRevisitId(int reasonForRevisitId) {
		this.reasonForRevisitId = reasonForRevisitId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
