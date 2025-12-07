package Default;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ItemActions {
	
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
		
	}
}
