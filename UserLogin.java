

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;




public class UserLogin implements SessionAware {
	

	private String username;
	private String password;
	private Map<String, Object> session;
	
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
	/**
	 public void setSession(Map<String, Object> session) {
	        this.session = session;
	    }
	**/
	public void setSession(Map map)
	{
		this.session= map;	
	}
	public String logIn()
	{
		// JDBC
		Connection connection = null;
		PreparedStatement ps = null;

		try {
		    // connect to the DB
		    connection = DriverManager.getConnection(
		        "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC",
		        "root",
		        "root"
		    );
		    
		    //the user should already exist
		    PreparedStatement check = connection.prepareStatement(
	                "SELECT username FROM register WHERE username = ? AND password = ?"
	            );
	            check.setString(1, username);
	            check.setString(2, password);
	            ResultSet rs = check.executeQuery();

	          
	            if (rs.next()) {
	            	
	            	   session.put("loggedUser", username);
	                   return "SUCCESS"; //user found , log in succesfull
	               }else {
	            	
	                   return "ERROR"; //user not found
	               }
	              
	            
	            
	            
		}catch (SQLException e) {
	    e.printStackTrace();
	    return "Error";
	
}
	}


}
