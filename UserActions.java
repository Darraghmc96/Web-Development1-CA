

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class UserActions implements SessionAware {
	

	 private Map<String, Object> session;
	    private String username;        
	    private Map<String, String> profileData;   
	    private List<Map<String, String>> userList;
	    
	    
	    public void setSession(Map map)
		{
			this.session= map;	
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Map<String, String> getProfileData() {
			return profileData;
		}
		public void setProfileData(Map<String, String> profileData) {
			this.profileData = profileData;
		}
		public List<Map<String, String>> getUserList() {
			return userList;
		}
		public void setUserList(List<Map<String, String>> userList) {
			this.userList = userList;
		}
		
		//===============
		//View My Profile
		//===============
	
		public String viewMyProfile() {

		    Connection connection = null;

		    try {

		        // jdbc
		        connection = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC",
		            "root",
		            "root"
		        );

		        // Check session for logged in user
		        String loggedUser = (String) session.get("loggedUser");
		        if (loggedUser == null) {
		            return "ERROR";
		        }

		      
		        profileData = new HashMap<>();

		      
		        PreparedStatement check = connection.prepareStatement(
		            "SELECT username, email FROM register WHERE username = ?"
		        );
		        check.setString(1, loggedUser);

		        ResultSet rs = check.executeQuery();

		        if (rs.next()) {
		            profileData.put("username", rs.getString("username"));
		            profileData.put("email", rs.getString("email"));
		            return "SUCCESS";
		        } else {
		            return "ERROR";
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        return "ERROR";

		    } finally {
		        try { if (connection != null) connection.close(); } 
		        catch (SQLException ignored) {}
		    }
		}
		
		//=============
		//View other profile
		//=================
		
		
		public String viewOtherProfile() {

		  
		    if (username == null || username.isEmpty()) {
		       
		        return "ERROR";
		    }

		    Connection connection = null;

		    try {
		        // Connect to DB
		        connection = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC",
		            "root",
		            "root"
		        );

		       
		        profileData = new HashMap<>();

		     
		        PreparedStatement ps = connection.prepareStatement(
		            "SELECT username, email FROM register WHERE username = ?"
		        );
		        ps.setString(1, username);

		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            profileData.put("username", rs.getString("username"));
		            profileData.put("email", rs.getString("email"));
		            return "SUCCESS";
		        } else {
		          
		            return "ERROR";
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        	
		        return "ERROR";

		    } finally {
		        try { if (connection != null) connection.close(); } 
		        catch (SQLException ignored) {}
		    }
		}
		
		//==============
		//View all users
		//==============
		
		public String viewAllUsers()
		{
			
			userList = new java.util.ArrayList<>();  
		 Connection connection = null;

		    try {
		        // Connect to DB
		        connection = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC",
		            "root",
		            "root"
		        );
		        
		        PreparedStatement ps = connection.prepareStatement(
		                "SELECT username,email FROM register"
		            );
		        
		        ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Map<String, String> user = new HashMap<>();
	                user.put("username", rs.getString("username"));
	                user.put("email", rs.getString("email"));
	                userList.add(user);
	            }
	            
	            if (userList.isEmpty()) {
	              
	                return "ERROR";
	            }

	            return "SUCCESS";
	            
		    }catch (SQLException e) {
			    e.printStackTrace();
			    return "ERROR";
		    
		} finally {
		    // ALWAYS close connections
		   
		    try { if (connection != null) connection.close(); } catch (SQLException e) {}
		}
		
		
		



}
}
