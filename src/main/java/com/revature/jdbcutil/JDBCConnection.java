package com.revature.jdbcutil;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
	
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = null;
			String endpoint = "jdbc:oracle:thin:@huangdb.cw68epmgbpw6.us-east-1.rds.amazonaws.com:1521:ORCL";
			String username = "levin";
			String password = "Asdfjkl;123";
			conn = DriverManager.getConnection(endpoint, username, password);
			return conn;
		}
		catch (Exception e){
			e.getMessage();
		}
		return null;
	}

}
