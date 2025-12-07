
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class ItemActions implements SessionAware {
	
	private Map<String, Object> session;

    private int itemId;                         
    private String itemName;
    private double itemPrice;
    private double bidAmount;

    private List<Map<String, Object>> items;
    private List<Map<String, Object>> bids;
	
    //getters + setters
    public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public double getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}
	
	public List<Map<String,Object>> getItems() { 
		return items; }
	
	public List<Map<String,Object>> getBids() {
		return bids; }
	
	public void setSession(Map map)
	{
		this.session= map;	
	}
	
	//=================
	// Add item for sale
	//=================
	public String addItem()
	{
		
		String user = (String) session.get("loggedUser");
		
		Connection connection = null;

	    try {
	        // Connect to DB
	        connection = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC",
	            "root",
	            "root"
	        );
	        

	        PreparedStatement check = connection.prepareStatement(
	            "INSERT INTO items (seller, item_name, price) VALUES (?, ?, ?)"
	        );
	        
	        check.setString(1, user);
            check.setString(2, itemName);
            check.setDouble(3, itemPrice);
	        
            check.executeUpdate();
            return "SUCCESS";
	        
	        
	}catch (SQLException e) {
	    e.printStackTrace();
	    return "ERROR";


}
	}
	
	//=================
	// view all items
	//=================
	public String viewAllItems()
	{
	    items = new ArrayList<>();
	    
	    Connection connection =null;

	    try {
	        // Connect to DB
	        connection = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC",
	            "root",
	            "root"
	        );
	        
	        PreparedStatement check = connection.prepareStatement(
		            "SELECT * FROM items"
		        );
	        ResultSet rs = check.executeQuery();
	        
	        while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("seller", rs.getString("seller"));
                row.put("item_name", rs.getString("item_name"));
                row.put("price", rs.getDouble("price"));
                items.add(row);
            }

            return "SUCCESS";
	        
	        
	    
	}catch (SQLException e) {
	    e.printStackTrace();
	    return "ERROR";


}
}
	//=================
	// make bid
	//=================
	public String makeBid()
	{
		String user = (String) session.get("loggedUser");

        if (user == null) {
        
            return "ERROR";
        }
        
        Connection connection =null;
        
        try {
	        // Connect to DB
	        connection = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC",
	            "root",
	            "root"
	        );
	        
	        PreparedStatement check = connection.prepareStatement(
		            "INSERT INTO bids (item_id, bidder, amount) VALUES (?, ?, ?)"
		        );
	        
	        check.setInt(1, itemId);
            check.setString(2, user);
            check.setDouble(3, bidAmount);
            
            
            check.executeUpdate();

            return "SUCCESS";

	        
	        
	        
        
	}catch (SQLException e) {
	    e.printStackTrace();
	    return "ERROR";


}
	
	}
	
	//===============
	//View My Bids
	//===============
	public String viewMyBids()
	{
		 String user = (String) session.get("loggedUser");
	     bids = new ArrayList<>();
	     
	     if (user == null) {
	         
	            return "ERROR";
	        }
	        
	        Connection connection =null;
	        
	        try {
		        // Connect to DB
		        connection = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC",
		            "root",
		            "root"
		        );
		        
		        
		        PreparedStatement check = connection.prepareStatement(
			            "SELECT * FROM bids WHERE bidder=?"
			        );
		        
		        check.setString(1, user);
	            ResultSet rs = check.executeQuery();
	            
	            while (rs.next()) {
	                Map<String, Object> row = new HashMap<>();
	                row.put("item_id", rs.getInt("item_id"));
	                row.put("amount", rs.getDouble("amount"));
	                bids.add(row);
	            }

	            return "SUCCESS";
		        
		        }catch (SQLException e) {
		    	    e.printStackTrace();
		    	    return "ERROR";


		    }
	
}
	//=======================
	//view all bids on an item
	//========================
	public String viewAllBids()
	{
		bids = new ArrayList<>();
		
		Connection connection =null;
		try {
	        // Connect to DB
	        connection = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC",
	            "root",
	            "root"
	        );
	        
	        PreparedStatement check = connection.prepareStatement(
		            "SELECT * FROM bids WHERE item_id=?"
		        );
	        check.setInt(1, itemId);
            ResultSet rs = check.executeQuery();
            
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("bidder", rs.getString("bidder"));
                row.put("amount", rs.getDouble("amount"));
                bids.add(row);
            }

            return "SUCCESS";


		
	}catch (SQLException e) {
	    e.printStackTrace();
	    return "ERROR";


}
	
	
	
}
}