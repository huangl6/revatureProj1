package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.service.ERSService;

public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ERSService ers = null;
       
    public InfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("viewAll") != null) {
			response.setContentType("text/html");
			ERSService ers = ERSService.getERSService(); 
			PrintWriter pw1 = response.getWriter();
			List<Employee> emps = ers.viewAllEmployees();
			pw1.println("<div>");
			pw1.println("<table><tr><th>EmpID</th><th>Name</th><th>Email</th><th>Username</th><th>Password</th><th>Manager?</th></tr>");
			
			for (Employee emp : emps) {
				pw1.println("<tr><td>" + emp.getEmpID() + "</td><td>" + emp.getName() + "</td><td>" + emp.getEmail() 
				+ "</td><td>" + emp.getUsername() + "</td><td>" + emp.getPassword() + "</td><td>" + emp.isManager() + "</tr>");
			}
			
			pw1.println("</table></div><br><a href='/ERSProject/managerOptions.html'>Go back.</a>");
		}
		
		else {
			response.setContentType("text/html");
			int id = Integer.parseInt(request.getParameter("empID"));
			ERSService ers = ERSService.getERSService(); 
			PrintWriter pw1 = response.getWriter();
			Employee emp = ers.viewInfo(id);
			pw1.println("<div>");
			pw1.println("<table><tr><th>EmployeeID</th><th>Name</th><th>Email</th><th>Username</th><th>Password</th><th>Manager?</th></tr>");
			pw1.println("<tr><td>" + emp.getEmpID() + "</td><td>" + emp.getName() + "</td><td>" + emp.getEmail() 
			+ "</td><td>" + emp.getUsername() + "</td><td>" + emp.getPassword() + "</td><td>" + emp.isManager() + "</tr>");
			pw1.println("</table></div><br><a href='/ERSProject/employeeOptions.html'>Go back.</a>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw1 = response.getWriter();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String us = request.getParameter("username");
		String pw = request.getParameter("password");
		int id = Integer.parseInt(request.getParameter("empID"));
		ers = ERSService.getERSService();
		ers.changeInfo(name, email, us, pw, id);
		pw1.println("</table></div><br><a href='/ERSProject/employeeOptions.html'>Go back.</a>");
	}
}
