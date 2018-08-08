package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.service.ERSService;

public class ServletERS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log = Logger.getLogger(ServletERS.class);
       
    public ServletERS() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String us = request.getParameter("username");
		String pw = request.getParameter("password");
		
		Employee ers = ERSService.getERSService().login(us, pw); 
		
		if (ers != null) {
			System.out.println(ERSService.viewInfo(us, pw));
			if (ers.isManager() == true) {
				System.out.println();
				RequestDispatcher rd = request.getRequestDispatcher("managerOptions.html");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("employeeOptions.html");
				rd.forward(request, response);
			}
			
	        BasicConfigurator.configure();
		}
		else {
			response.sendRedirect("/ERSProject/login.html");
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
