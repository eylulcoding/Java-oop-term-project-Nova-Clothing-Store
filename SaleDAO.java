//import requirements
import java.sql.*;

public class SaleDAO {

    private Connection connection; //connection instance

    //constructor to initialize SaleDAO with a database connection.
    public SaleDAO(Connection connection) {
        this.connection = connection;
    }

    //method to insert a new sale into the SALES table.
    public void insertSale(String saleDate, int billId, int customerId, int productId, int quantity, int staffId) throws SQLException {
        String query = "INSERT INTO SALES (sales_date, bill_id, customer_id, product_id, sales_quantity, staff_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	//bind the provided parameters to the query placeholders.
            statement.setString(1, saleDate);    // first ?
            statement.setInt(2, billId);          // second ?
            statement.setInt(3, customerId);      // third ?
            statement.setInt(4, productId);       // fourth ?
            statement.setInt(5, quantity);        // fifth ? 
            statement.setInt(6, staffId);         // sixth ? 

            int result = statement.executeUpdate(); //execute the query, which returns the number of rows affected.
            if (result > 0) {
                System.out.println("Sale inserted successfully"); //success message
            } else {
                System.out.println("Problem inserting sale"); //failure message
            }
        } catch (SQLException e) {
            e.printStackTrace(); //print the stack trace
        }
    }
    
    //method to delete a sale by its bill ID.
    public void deleteSaleById(int id) throws SQLException {
        String query = "DELETE FROM SALES WHERE bill_id = ?"; //sql query to delete a sale record based on the bill ID.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); //bind the bill_id to the first placeholder
            int result = statement.executeUpdate(); //execute the query, which returns the number of rows affected.
            if (result == 0)
                System.out.println("No sale deleted"); //failure message
            else
                System.out.println("Sale with ID: " + id + " deleted"); //success message
        }
    }

    //method to retrieve a sale record by its sale ID.
    public String getSaleById(int id) throws SQLException {
        String query = "SELECT * FROM SALES WHERE sale_id = ?";  //sql query to fetch a sale record based on the sale ID.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); //bind the sale_id to the first placeholder
            ResultSet rs = statement.executeQuery(); //execute the query and store the result in a ResultSet.
            if (rs.next()) { //checks if the ResultSet contains a record.
                int productId = rs.getInt("product_id"); //fetch product ID.
                int quantity = rs.getInt("sales_quantity"); //fetch the quantity sold
                String saleDate = rs.getString("sales_date"); //fetch sale date.
                return "Sale ID: " + id + ", Product ID: " + productId + ", Quantity: " + quantity + ", Date: " + saleDate;
            }
        }
        return "No sale with ID: " + id + " exists"; //return a message if no record is found
    }
}
