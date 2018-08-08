package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Request;

public interface ERSObjectInt {
	public ERSObject ersObject = null;
	
	public boolean submitRequest(Request r, int id);
	public List<Request> viewRequests(int id);
	public Employee viewInfo(int id);
	public void updateInfo(String name, String email, String us, String pw, int id);
	public boolean addEmployee(Employee e);
}
