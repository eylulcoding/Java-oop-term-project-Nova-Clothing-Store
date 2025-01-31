//import requirements
import java.sql.*;

public class SupplierDAO {

    private Connection connection; //connection instance  

    //constructor to initialize the DAO with a database connection
    public SupplierDAO(Connection connection) {
        this.connection = connection;
    }
    
    //method to insert a new supplier record into the database
    public void insertSupplier(int supplierId, String name, String address, String contactInfo, String gsm) {
        String query = "INSERT INTO SUPPLIERS (supplier_id, supplier_name, supplier_address, supplier_contact_info, supplier_gsm) VALUES (?, ?, ?, ?, ?)"; // SQL query to insert a new supplier into the SUPPLIERS table
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	// Set the parameters for the query using the values provided
            statement.setInt(1, supplierId); //first ?
            statement.setString(2, name);    //second ?
            statement.setString(3, address); //third ?
            statement.setString(4, contactInfo); // fourth ?
            statement.setString(5, gsm);          // fifth ? 
            int result = statement.executeUpdate();  //execute the query and check how many rows were affected.
            if (result > 0) { // if there is a change
                System.out.println("Supplier inserted successfully"); //success message 
            } else {
                System.out.println("Problem inserting supplier"); //failure message
            }
        } catch (SQLException e) {
            e.printStackTrace(); //print error details
        }
    }


    //method to delete a supplier by their ID
    public void deleteSupplierById(int id) throws SQLException {
        String query = "DELETE FROM SUPPLIERS WHERE supplier_id = ?"; //sql query to delete a supplier from the database based on their ID
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); //sets the supplier ID to be deleted in the query.
            int result = statement.executeUpdate(); //execute the query and check how many rows were affected.
            if (result == 0) // if no rows are affected
                System.out.println("No supplier deleted"); //failure message
            else
                System.out.println("Supplier with ID: " + id + " deleted"); //success message 
        }
    }

    //method to retrieve a supplier's details by their ID
    public String getSupplierById(int id) throws SQLException {
        String query = "SELECT * FROM SUPPLIERS WHERE supplier_id = ?"; //sql query to fetch a supplier's details based on their ID
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); // Set the supplier ID to fetch in the query.
            ResultSet rs = statement.executeQuery();  //execute the query and get the results in a ResultSet
            if (rs.next()) { //--check if a record is found --
            	//retrieve column values from the ResultSet.
                String name = rs.getString("supplier_name");
                String address = rs.getString("supplier_address");
                String contactInfo = rs.getString("supplier_contact_info");
                String gsm = rs.getString("supplier_gsm");
                return "ID: " + id + ", Name: " + name + ", Address: " + address + ", Contact Info: " + contactInfo + ", GSM: " + gsm;
            }
        }
        return "No supplier with ID: " + id + " exists"; //return a message if no supplier is found 
    }
    
    //method to update an existing supplier's details in the database.
    public void updateSupplier(int supplierId, String name, String address, String contactInfo, String gsm) {
        String query = "UPDATE SUPPLIERS SET supplier_name = ?, supplier_address = ?, supplier_contact_info = ?, supplier_gsm = ? WHERE supplier_id = ?"; //sql query to update supplier details based on their ID.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	//Sets the new values for the supplier in the query.
            statement.setString(1, name); //first ?
            statement.setString(2, address); //second ?
            statement.setString(3, contactInfo); //third ?
            statement.setString(4, gsm); //fourth ?
            statement.setInt(5, supplierId); //fifth ?
            int result = statement.executeUpdate(); //execute the query and check how many rows were affected.s
            if (result > 0) { // if there is a change
                System.out.println("Supplier updated successfully."); //success message 
            } else {
                System.out.println("Supplier not found for update."); //failure message
            }
        } catch (SQLException e) {
            System.err.println("Error updating supplier: " + e.getMessage()); //print error details
        }
    }

}
