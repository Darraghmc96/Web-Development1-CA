package Ca1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class creditsServlet  extends HttpServlet {
	
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		
		
		    
		    String gamerTag = request.getParameter("GamerTag");
		    String action = request.getParameter("action"); 
	        int amount = request.getParameter("amount");
	        int credits = 500;
	        
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        
	        int newCredits;
	       
	        if ("earn".equalsIgnoreCase(action)) {
                newCredits = credits + amount; 
            } else if ("spend".equalsIgnoreCase(action)) {
                if (credits - amount < 0) {
                   
                    out.println("<h2 style='color:red;'>Spending  Failed</h2>");
                    out.println("<p>Sorry , you don't have enough credits.</p>");
                    out.println("</body></html>");
                    return;
                } else {
                    newCredits -= amount;
                }
            }
	        
	        //jdbc
	        
	        Connection connection;
			try {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/Register?serverTimezone=UTC","root", "root");
			
			
			
				//PUT SOMETHING INTO THE DATABASE USING A PREPAREDSTATEMENT
				
				PreparedStatement update = connection.prepareStatement("Update Register Set credits = ? Where gamerTag = ?");
				update.setInt(1, newCredits);
	            update.setString(2, gamerTag);
	            update.executeUpdate();

	            update.close();
	            
			 } catch (Exception e) {
		            e.printStackTrace();
		        }
			
			
			out.println("<h2>Transaction Successful!</h2>");
            out.println("<p>Hi <b>" + gamerTag + "</b>");
            out.println("<h3> your new balance is: " + newCredits );
            out.println("</body></html>");
}
	
}
