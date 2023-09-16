package com.dbConnection;

import java.sql.*;

public class DBConnection {

	
		 public static Connection getConnection() throws Exception {
		      
		        Class.forName("com.mysql.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
		        return con;
		    }
	
	
}
