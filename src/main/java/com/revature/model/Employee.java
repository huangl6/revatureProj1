package com.revature.model;

public class Employee {
	private int empID;
	private String name;
	private String email;
	private String username;
	private String password;
	private boolean isManager;
	
	public Employee(int empID, String name, String email, String username, String password, boolean isManager) {
		super();
		this.empID = empID;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.isManager = isManager;
	}
	
	public Employee() {
		
	}
	
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", name=" + name + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", isManager=" + isManager + "]";
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	
}
