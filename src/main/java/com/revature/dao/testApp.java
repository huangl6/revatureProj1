package com.revature.dao;

import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.service.ERSService;

public class testApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Employee(int empID, String name, String email, String username, String password, boolean isManager)
//		Employee e = new Employee(0, "", "", "", "", false);
//		e = ERSService.viewInfo("admin", "admin");
//		System.out.println(e);
//		Request(int requestID, int amount, String reason, boolean resolved, int empID)
		Employee e = new Employee (0, "tim", "tim@mail.com", "tim", "tim", false);
		boolean adder = ERSService.addEmployee(e);
//		System.out.println(adder);
//		Request r = new Request(0, 10, "asdf", 1, 61);
//		boolean submitted = ERSService.submitRequest(r,  61);
//		System.out.println(submitted);
	}

}
