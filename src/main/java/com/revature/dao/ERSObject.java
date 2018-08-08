package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.jdbcutil.JDBCConnection;
import com.revature.model.*;

public class ERSObject implements ERSObjectInt {
	public static ERSObject ersObject;
	
	private ERSObject() {
		
	}
	
	public static ERSObject getERSObject() {
		if (ersObject == null) {
			ersObject = new ERSObject();
		}
		
		return ersObject;
	}
	
	public boolean submitRequest(Request r, int id) {
		try {
			Connection conn = JDBCConnection.getConnection();
			String sql = "call add_requests(?, ?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.setInt(2, r.getAmount());
			cs.setString(3, r.getReason());
			cs.setInt(4, r.getResolved());
			cs.registerOutParameter(5, java.sql.Types.INTEGER);
			cs.execute();
			if (cs.getInt(5) == 1){
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Request> viewRequests(int id) {
		try {
			Connection conn = JDBCConnection.getConnection();
			String sql = "select * from requests where employeeid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			List<Request> requests = new ArrayList<Request>();
			
			while (rs.next()) {
				requests.add(new Request(rs.getInt("requestid"),
						rs.getInt("amount"),
						rs.getString("reason"), rs.getInt("resolved"), rs.getInt("employeeid")));
			}
			System.out.println(requests.size());
			return requests;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Request> viewAllRequests() {
		try {
			Connection conn = JDBCConnection.getConnection();
			String sql = "select * from requests";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Request> requests = new ArrayList<Request>();
			
			while (rs.next()) {
				requests.add(new Request(rs.getInt("requestid"),
						rs.getInt("amount"),
						rs.getString("reason"),
						rs.getInt("resolved"), rs.getInt("employeeid")));
				
			}
			return requests;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Employee viewInfo(int id) {
		try {
			Connection conn = JDBCConnection.getConnection();
			String sql = "select * from employees where userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Employee emp = null;
			
			while (rs.next()) {
				boolean manager = false;
				
				if (rs.getString("MANAGER").equals("true")) {
					manager = true;
				}
				
				emp = new Employee(rs.getInt("userid"),
						rs.getString("NAME"),
						rs.getString("EMAIL"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getBoolean("MANAGER"));
			}
			
			return emp;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Employee viewInfo(String us, String pw) {
		try {
			Connection conn = JDBCConnection.getConnection();
			String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, us);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				boolean manager = false;
				if (rs.getString("MANAGER").equals("true")) {
					manager = true;
				}
				return new Employee(
						rs.getInt("userid"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("username"),
						rs.getString("password"),
						manager);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void changeReqStatus(int res, int id) {
		try {
			Connection conn = JDBCConnection.getConnection();
			String sql = "update requests set resolved = ? where requestid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, res);
			ps.setInt(2, id);
				
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	
	public List<Employee> viewAllEmployees() {
		try {
			Connection conn = JDBCConnection.getConnection();
			String sql = "select * from employees";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Employee> emps = new ArrayList<Employee>();
			Employee emp = null;
			
			while (rs.next()) {
				boolean manager = false;
				
				if (rs.getString("MANAGER").equals("true")) {
					manager = true;
				}
				
				emp = new Employee(rs.getInt("userid"),
						rs.getString("NAME"),
						rs.getString("EMAIL"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getBoolean("MANAGER"));
				emps.add(emp);
			}
			
			return emps;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void updateInfo(String name, String email, String us, String pw, int id) {
		try {
			Connection conn = JDBCConnection.getConnection();
			String sql = "update employees set name = ?, email = ?, username = ?, password = ? where userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, us);
			ps.setString(4, pw);
			ps.setInt(5, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean addEmployee(Employee employee) {
		try{
			Connection conn = JDBCConnection.getConnection();
			String sql = "call add_employees(?, ?, ?, ?, ?, ?)";
			
			CallableStatement cs = conn.prepareCall(sql);
			String employeeRole = "false";
			if (employee.isManager()) {
				employeeRole = "true";
			}
			else {
				employeeRole = "false";
			}
			cs.setString(1, employee.getName());
			cs.setString(2,  employee.getEmail());
			cs.setString(3,  employee.getUsername());
			cs.setString(4,  employee.getPassword());
			cs.setString(5,  employeeRole);
			cs.registerOutParameter(6, java.sql.Types.INTEGER);
			cs.execute();
			if (cs.getInt(6) == 1) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
