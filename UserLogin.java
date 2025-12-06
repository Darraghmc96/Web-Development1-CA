package webDevCa2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin {
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String logIn()
	{
		// JDBC
		Connection connection = null;
		PreparedStatement ps = null;

		try {
		    // connect to the DB
		    connection = DriverManager.getConnection(
		        "jdbc:mysql://localhost:3306/register?serverTimezone=UTC",
		        "root",
		        "root"
		    );
		    
		    //the user should already exist
		    PreparedStatement check = connection.prepareStatement(
	                "SELECT username FROM register WHERE username = ?"
	            );
	            check.setString(1, username);
	            ResultSet rs = check.executeQuery();

	          
	            if (rs.next()) {
	            	   String message = "Login successful!";
	                   return "SUCCESS"; //user found , log in succesfull
	               }else {
	            	   String message = "Invalid username or Password";
	                   return "Error"; //user not found
	               }
	              
	            
	            
	            
		}catch (SQLException e) {
	    e.printStackTrace();
	    return "Error";
	
}
	}
}
