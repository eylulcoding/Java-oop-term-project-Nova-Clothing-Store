//import requirements
import java.sql.Connection;
import java.sql.SQLException;

//this code shows us that we can manage our inventory in here too but in simple way for learning purposes maybe..
public class Main {

    public static void main(String[] args) {
    	
    	Connection connection = Database.getConnection(); //here we are calling Database.getConnection() method to establish a database connection.
        
    	try {
        //initializing DAO classes. Each one interacts with a specific table in the database.	
        SupplierDAO supplierDAO = Database.getSupplierDAO();
        PurchasesDAO purchasesDAO = Database.getPurchasesDAO();
        ProductDAO productDAO = Database.getProductDAO();
        CustomerDAO customerDAO = Database.getCustomerDAO();
        StaffDAO staffDAO = Database.getStaffDAO();
        InventoryDAO inventoryDAO = Database.getInventoryDAO();
        SaleDAO saleDAO = Database.getSaleDAO();
        
      
        String productName3 = "Pants1";
        //adding products to the database with different size and color combinations.
        productDAO.insertProduct(40,productName3, 59.99, "Pants1", "S", "pink");
        productDAO.insertProduct(41,productName3, 59.99, "Pants1", "S", "orange");
        productDAO.insertProduct(42,productName3, 59.99, "Pants1", "S", "black");
        
        productDAO.insertProduct(43,productName3, 59.99, "Pants1", "M", "pink");
        productDAO.insertProduct(44,productName3, 59.99, "Pants1", "M", "orange");
        productDAO.insertProduct(45,productName3, 59.99, "Pants1", "M", "black");
        
        productDAO.insertProduct(46,productName3, 59.99, "Pants1", "L", "pink");
        productDAO.insertProduct(47,productName3, 59.99, "Pants1", "L", "orange");
        productDAO.insertProduct(48,productName3, 59.99, "Pants1", "L", "black");
        
     
        //getting the product ID using the product name.
        int productIdPants3 = productDAO.getProductIdByName("Pants1");
      

        //inserting customer
        customerDAO.insertCustomer(5, "John D", "123 Main St", "john@example.com", "555-1234");
        
      //getting the product ID using the product name.
        int productId = productDAO.getProductIdByName(productName3);
        
        // Defining the quantity of the product to be purchased.
        int purchaseQuantity = 6; 
        
        //inserting staff
        staffDAO.insertStaff(3, "Jane Doe", "456 Elm St", "555-5678", "Sales Representative");
        
     
        //inserting a inventory record
        inventoryDAO.insertInventory(productId, 20); 

    	} catch (SQLException e) {
    	    e.printStackTrace();
    	} finally {
    	    try {
    	        if (connection != null) connection.close();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}
    	
        
       
    }
}
