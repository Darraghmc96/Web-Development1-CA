

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistration {

	private String username;
    private String email;
    private String password;
    
    
    
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
public String registerUser()
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
	    
	    //check if it already exists
	    PreparedStatement check = connection.prepareStatement(
                "SELECT username FROM register WHERE username = ?"
            );
            check.setString(1, username);
            ResultSet rs = check.executeQuery();

            //fix
            if (rs.next()) {
            	
                return "SUCCESS";   // Sends to logIn jsp
            
            }

	    // Insert user into DB
	    ps = connection.prepareStatement(
	        "INSERT INTO register (username, email, password) VALUES (?, ?, ?)"
	    );

	    ps.setString(1, username);
	    ps.setString(2, email);
	    ps.setString(3, password); 

	    int rowsUpdated = ps.executeUpdate();

	    System.out.println("Inserted rows: " + rowsUpdated);
	    return "SUCCESS";

	} catch (SQLException e) {
	    e.printStackTrace();
	    return "ERROR";
	    

	} finally {
	    // ALWAYS close connections
	    try { if (ps != null) ps.close(); } catch (SQLException e) {}
	    try { if (connection != null) connection.close(); } catch (SQLException e) {}
	}
	
}


}
