/**
 * 
 */
package com.bp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author BrijeshwarS
 *
 */
public class TestJdbc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?userSSL=false";
		String user = "hbstudent";
		String password = "hbstudent";
		
		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			
			Connection con = DriverManager.getConnection(jdbcUrl, user, password);
			
			System.out.println("Connection succesfull!!");
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
