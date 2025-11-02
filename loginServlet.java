package Ca1;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class loginServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		
		// Get form parameters
        String gamerTag = request.getParameter("gamerTag");
        String password = request.getParameter("password");
        String password2 = request.getParamter("password2");
        int credits =500;
        
		
     // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
  
        
        if (!password.equals(password2)) {
            out.println("<html><body>");
            out.println("<h2 style='color:red;'>Error: Passwords do not match. Please try again.</h2>");
            out.println("</body></html>");
            return; // Stop execution here
        }
        
        
        
        
        
        
        
        //jdbc
        Connection connection;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Register?serverTimezone=UTC","root", "root");
		
		
		
			//PUT SOMETHING INTO THE DATABASE USING A PREPAREDSTATEMENT
			//Use a preparedStatement (parameterised SQL statement) to add a word
			PreparedStatement createWord = connection.prepareStatement(
					"INSERT into register "
					+ "(user)" +" VALUES (?)");
					//pass in the values as parameters
					createWord.setString(1, user);
					//insert into register (word =  x)
					int rowsUpdated = createWord.executeUpdate();
					createWord.close();
					
					
			//PUT SOMETHING IN USING A STATEMENT
			String sql = "INSERT INTO register VALUES ('sad')";
			Statement insertWord = connection.createStatement();
			int rowsChanged = insertWord.executeUpdate(sql);
			
			//GET SOMETHING OUT OF THE DATABASE	USING A STATEMENT
					Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery("select * from register");

				while(rs.next()) {
					System.out.println("Column 1 in ResultSet : "+rs.getString(1));
				}
		
		
		
		
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
	      
	     // Output confirmation
	        out.println("<html><body>");
	        out.println("<h2>You have succesfully registered!" + "!</h2>");
	        out.println("<p>GamerTag: " + gamerTag + "</p>");
	        out.println("<p>password:" + password + "<p>");
	        out.println("<p>Credits:" + credits + "</p>");
            out.println("</ol>");
            out.println("</body></html>");
		
		
		
	}
	
	

}
