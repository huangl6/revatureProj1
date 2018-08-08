package com.revature.model;

public class Request {
	private int requestID;
	private int amount;
	private String reason;
	private int resolved;
	private int empID;
	
	public Request(int requestID, int amount, String reason, int resolved, int empID) {
		super();
		this.requestID = requestID;
		this.amount = amount;
		this.reason = reason;
		this.resolved = resolved;
		this.empID = empID;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isResolved() {
		if (resolved == 1) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public int getResolved() {
		return resolved;
	}

	public void setResolved(int resolved) {
		this.resolved = resolved;
	}

	@Override
	public String toString() {
		return "Request [requestID=" + requestID + ", amount=" + amount + ", reason=" + reason
				+ ", resolved=" + resolved + "]";
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}
}
