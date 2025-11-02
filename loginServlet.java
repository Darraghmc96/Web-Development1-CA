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
      
        
		
     // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
  
       
        //jdbc
        Connection connection;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Register?serverTimezone=UTC","root", "root");
		
		
		
			//PUT SOMETHING INTO THE DATABASE USING A PREPAREDSTATEMENT
			//Use a preparedStatement (parameterised SQL statement) to add a word
			PreparedStatement createWord = connection.prepareStatement(
					"SELECT credits FROM register WHERE gamerTag=? AND password=?");
					//pass in the values as parameters
					createWord.setString(1, gamerTag);
					createWord.setString(2, password);
				
					
					ResultSet rs = createWord.executeQuery();
        
        
        
        
       
        
        
		    if (rs.next()) {
		    int credits = rs.getInt("credits");
	     // Output confirmation
	        out.println("<html><body>");
	      
	        out.println("<p>Welcome back! " + gamerTag + "</p>");
	        out.println("<p>Current Credits:" + credits + "</p>");
            out.println("</ol>");
            out.println("</body></html>");
		
		
            rs.close();
            createWord.close();
            connection.close();
		    }
	
		    } catch (Exception e) {
	            e.printStackTrace();
	        }
	

	}
}
