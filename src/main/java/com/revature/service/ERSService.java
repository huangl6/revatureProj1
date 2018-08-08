package com.revature.service;

import java.util.List;

import com.revature.dao.ERSObject;
import com.revature.model.Employee;
import com.revature.model.Request;

public class ERSService {
	private static ERSService ersService;
	
	private ERSService() {
		
	}
	
	public static ERSService getERSService() {
		if (ersService == null) {
			ersService = new ERSService();
		}
		
		return ersService;
	}
	
	public static boolean submitRequest(Request r, int id) {
		return ERSObject.getERSObject().submitRequest(r, id);
	}
	
	public static void changeReqStatus(int res, int id) {
		ERSObject.getERSObject().changeReqStatus(res, id);
	}
	
	public static Employee login(String us, String pw) {
		Employee e = ERSObject.getERSObject().viewInfo(us, pw);
		return e;
	}
	
	public static List<Request> viewRequests(int id) {
		return ERSObject.getERSObject().viewRequests(id);
	}
	
	public static Employee viewInfo(int id) {
		return ERSObject.getERSObject().viewInfo(id);
	}
	
	public static Employee viewInfo(String us, String pw) {
		return ERSObject.getERSObject().viewInfo(us, pw);
	}
	
	public static List<Request> viewAllRequests() {
		return ERSObject.getERSObject().viewAllRequests();
	}
	
	public static List<Employee> viewAllEmployees() {
		return ERSObject.getERSObject().viewAllEmployees();
	}
	
	public static void changeInfo(String name, String email, String us, String pw, int id) {
		ERSObject.getERSObject().updateInfo(name, email, us, pw, id);
	}
	
	public static boolean addEmployee(Employee e) {
		return ERSObject.getERSObject().addEmployee(e);
	}
}
