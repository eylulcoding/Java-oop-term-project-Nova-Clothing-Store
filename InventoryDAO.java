//import requirements
import java.sql.*;

public class InventoryDAO {

    private Connection connection; //connection instance

  //constructor to initialize the DAO with a database connection
    public InventoryDAO(Connection connection) {
        this.connection = connection;
    }

  //a method to insert a inventory with their values
    public void insertInventory(int productId, int stock) throws SQLException {
        String query = "INSERT INTO INVENTORY (product_id, current_stock) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	//sets the entered values into the query and this query inserts values to the inventories table.
            statement.setInt(1, productId);
            statement.setInt(2, stock);
            int result = statement.executeUpdate(); //execute the insert operation and this method returns the number of effected rows.
            if (result > 0) {
                System.out.println("Inventory entry inserted successfully"); //success message
            } else {
                System.out.println("Problem inserting inventory entry"); //failure message
            }
            
        }
        catch (SQLException e) {
            e.printStackTrace(); //prints sql exception details
        }
    }

    //a method to get current stock for a given product id
    public int getCurrentStock(int productId, Connection conn) throws SQLException {
        String query = "SELECT current_stock FROM INVENTORY WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId); //sets the given product ID to the query
            ResultSet rs = pstmt.executeQuery();

            
            if (rs.next()) { //moves the cursor to the next row in the result set. this initially points to first row
            	
                return rs.getInt("current_stock"); //returns the stock value in a current row if the product exists
            } else {
                return 0; //return 0 if no stock is found for the product
            }
        }
    }
    
    //method to check if an inventory entry exists for a specific product ID
    public boolean checkInventory(int productId) throws SQLException {
        String query = "SELECT COUNT(*) FROM INVENTORY WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId); //sets the given product ID to the query
            ResultSet rs = statement.executeQuery(); //execute the query
            if (rs.next()) {
                return rs.getInt(1) > 0; //return true if the count is greater than 0
            }
        }
        return false; //return false if no entry exists
    }

    

    //method to update the inventory stock after a sale
    public void updateInventoryAfterSale(int productId, int quantity, Connection conn) throws SQLException {
        String query = "UPDATE INVENTORY SET current_stock = current_stock - ? WHERE product_id = ?"; //this query decreases the stock because there is a sale from customer.
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        	//sets the entered sales values to the query
            pstmt.setInt(1, quantity); // first ?
            pstmt.setInt(2, productId); //second ?
            pstmt.executeUpdate(); // Execute the update query
        }
    }
    
    //method to update the inventory stock after a purchase
    public void updateInventoryAfterPurchase(int productId, int quantity, Connection conn) throws SQLException {
        String query = "UPDATE INVENTORY SET current_stock = current_stock + ? WHERE product_id = ?"; //this query increases the stock because there is a purchase from supplier.
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        	//sets the entered sales values to the query
            pstmt.setInt(1, quantity); //first ?
            pstmt.setInt(2, productId); //second ?
            pstmt.executeUpdate(); // Execute the update query
        }
    }
    
    //method to insert a new inventory entry or update an existing one
    public void insertOrUpdateInventory(int productId, int quantity,Connection conn) throws SQLException {
        if (checkInventory(productId)) {
            updateInventoryAfterPurchase(productId, quantity,conn); //if the product exists in inventory, update the stock
        } else {
            insertInventory(productId, quantity); //if the product does not exist, insert a new entry
        }
    }
    
    //rollback method to revert stock after a failed sale transaction
    public void updateInventoryAfterSaleRollback(int productId, int quantity, Connection conn) throws SQLException {
        String query = "UPDATE INVENTORY SET current_stock = current_stock + ? WHERE product_id = ?"; //there is no successful sale so add that saled amount back 
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, quantity);  //add back the quantity
            pstmt.setInt(2, productId); //specify the product ID
            pstmt.executeUpdate(); //execute the rollback query
        }
    }

    //rollback method to revert stock after a failed purchase transaction
    public void updateInventoryAfterPurchaseRollback(int productId, int quantity, Connection conn) throws SQLException {
        String query = "UPDATE INVENTORY SET current_stock = current_stock - ? WHERE product_id = ?"; //there is no successful purchase so delete that purchased amount. 
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, quantity);  //subtract the reverted quantity
            pstmt.setInt(2, productId); //specify the product ID
            pstmt.executeUpdate(); //execute the rollback query
        }
    }

    //method to get inventory details for a specific product ID
    public String getInventoryByProductId(int productId) throws SQLException {
        String query = "SELECT * FROM INVENTORY WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId); //first ?
            ResultSet rs = statement.executeQuery(); //execute the query and get the result set
            if (rs.next()) {
                int stock = rs.getInt("current_stock"); //retrieve the stock value
                return "Product ID: " + productId + ", Stock: " + stock;
            }
        }
        return "No inventory for Product ID: " + productId; //return this if no result is found
    }
    

}
