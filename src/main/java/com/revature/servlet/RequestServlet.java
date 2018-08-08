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

public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ERSService ers = null;
       
    public RequestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("viewAll") != null) {
			response.setContentType("text/html");
			ers = ERSService.getERSService(); 
			PrintWriter pw1 = response.getWriter();
			List<Request> list = ers.viewAllRequests();
			pw1.println("<div>");
			pw1.println("<table><tr><th>RequestID</th><th>Amount</th><th>Description</th><th>Status</th><th>EmployeeID</th></tr>");
			String reqStatus = "";
			for (Request req : list) {
				System.out.println(req.getResolved());
				if (req.getResolved() == 2) {
					reqStatus = "Denied";
				}
				else if (req.getResolved() == 3) {
					reqStatus = "Approved";
				}
				else {
					reqStatus = "Pending";
				}
				pw1.println("<tr><td>" + req.getRequestID() + "</td><td>" + req.getAmount() + "</td><td>" +  
					req.getReason() + "</td><td>" + reqStatus + "</td><td>" + req.getEmpID() + "</td></tr>");
			}
			pw1.println("</div><br><a href='/ERSProject/managerOptions.html'>Go back.</a>");
		} 
		else if (request.getParameter("addEmployee") != null) {
			response.setContentType("text/html");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Employee e = new Employee(0, name, email, username, password, false);
			boolean adder = ERSService.getERSService().addEmployee(e);
			PrintWriter pw1 = response.getWriter();
			if (adder == true) {
				pw1.println("</div><br>Success! <a href='/ERSProject/managerOptions.html'>Go back.</a>");
			}
			else {
				pw1.println("</div><br>Success! <a href='/ERSProject/managerOptions.html'>Go back.</a>");
			}
		}
		else if (request.getParameter("changeReqStatus") != null) {
			response.setContentType("text/html");
			ers = ERSService.getERSService();
			PrintWriter pw1 = response.getWriter();
			int resolved = Integer.parseInt(request.getParameter("resolved"));
			int reqId = Integer.parseInt(request.getParameter("reqID"));
			ers.changeReqStatus(resolved, reqId);
			pw1.println("</div><br>Success! <a href='/ERSProject/managerOptions.html'>Go back.</a>");
		}
		else {
			response.setContentType("text/html");
			Integer id = Integer.parseInt(request.getParameter("empID"));
			ers = ERSService.getERSService(); 
			PrintWriter pw1 = response.getWriter();
			List<Request> list = ers.viewRequests(id);
			pw1.println("<div>");
			pw1.println("<table><tr><th>RequestID</th><th>Amount</th><th>Description</th><th>Status</th><th>EmployeeID</th></tr>");
			String reqStatus = "";	
			for (Request req : list) {
				System.out.println(req.getResolved());
				if (req.getResolved() == 2) {
					reqStatus = "Denied";
				}
				else if (req.getResolved() == 3) {
					reqStatus = "Approved";
				}
				else {
					reqStatus = "Pending";
				}
				pw1.println("<tr><td>" + req.getRequestID() + "</td><td>" + req.getAmount() + "</td><td>" +  
					req.getReason() + "</td><td>" + reqStatus + "</td><td>" +  id + "</td></tr>");
			}
			pw1.println("</div><br><a href='/ERSProject/employeeOptions.html'>Go back.</a>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("empID"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String reason = request.getParameter("reason");
		int resolved = 1;
		Request req = new Request(0, amount, reason, resolved, id);
		ers = ERSService.getERSService();
		ers.submitRequest(req, id);
		response.getWriter().println("Request submitted! <a href='/ERSProject/employeeOptions.html'>"
				+ "Go back.</a>");
	}
}
