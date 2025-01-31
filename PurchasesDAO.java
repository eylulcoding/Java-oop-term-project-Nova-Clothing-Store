//import requirements
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PurchasesDAO {

    private Connection connection; //connection instance

   //connection to the database
    public PurchasesDAO(Connection connection) {
        super();
        this.connection = Database.getConnection(); //gets the database connection
    }

    

    //retrieves a purchase record by its ID.
    public Purchase getPurchaseById(int purchaseId) {
        String query = "SELECT * FROM PURCHASES WHERE purchases_id = ?;"; //sql query to get purchase by ID
        Purchase purchase = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, purchaseId); //set the purchase ID in the query
            ResultSet rs = statement.executeQuery(); //execute the SELECT query
            if (rs.next()) {
            	//retrieve column values from the result set
                int productId = rs.getInt("product_id");
                int supplierId = rs.getInt("supplier_id");
                int quantity = rs.getInt("purchases_quantity");
                double price = rs.getDouble("purchases_price"); 
                String purchaseDate = rs.getString("purchases_date");
                //create a new purchase object with the retrieved data
                purchase = new Purchase(purchaseId, productId, supplierId, quantity, price, purchaseDate);
            }
        } catch (SQLException e) {
            e.printStackTrace(); //print SQL exception details

        }
        return purchase; //return the purchase object
    }



   //inserts a new purchase record into the database.
    public void insertPurchase(String purchaseDate, int supplierId, int purchasesId, int productId, int quantity, double price) throws SQLException {
        String query = "INSERT INTO PURCHASES (purchases_date, supplier_id, purchases_id, product_id, purchases_quantity, purchases_price) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, purchaseDate);    // set purchaseDate to first ?
            statement.setInt(2, supplierId);         // second ? 
            statement.setInt(3, purchasesId);        // third ? 
            statement.setInt(4, productId);          // fourth ?
            statement.setInt(5, quantity);           // fifth ?
            statement.setDouble(6, price);           // sixth ?

            int result = statement.executeUpdate(); //execute the insert query
            if (result > 0) {
                System.out.println("Purchase inserted successfully"); //success message
            } else {
                System.out.println("Problem inserting purchase"); //failure message
            }
        } catch (SQLException e) {
            e.printStackTrace(); //print SQL exception details
        }
    }


    //deletes a purchase record by its ID.
    public void deletePurchaseById(int id) throws SQLException {
        Statement statement = connection.createStatement(); //create a Statement object
        String query = "DELETE FROM PURCHASES WHERE purchases_id = " + id + ";"; //sql DELETE query
        int result = statement.executeUpdate(query); //executes the DELETE query
        if (result == 0) {
            System.out.println("No purchase deleted."); //a message if no rows were deleted
        } else {
            System.out.println("Purchase with ID: " + id + " deleted."); //a message if a row was deleted
        }
        }
    }

