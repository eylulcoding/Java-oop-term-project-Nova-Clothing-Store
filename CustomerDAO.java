//import requirements
import java.sql.*;

public class CustomerDAO {

    private Connection connection; //connection instance

    //constructor to initialize the DAO with a database connection
    public CustomerDAO(Connection connection) {
        this.connection = connection; 
    }

    //a method to retrieve a customer by their ID
    public String getCustomerById(int id) {
        String query = "SELECT * FROM CUSTOMERS WHERE customer_id = ?"; //sql query to fetch customer details by ID
        String result = "";
        try (PreparedStatement statement = connection.prepareStatement(query)) {  //prepared statement enables us to connect parameters dynamically
            statement.setInt(1, id); //the first number represents the position for ? and the second argument SETS the value of the given to that ?
            ResultSet rs = statement.executeQuery();
            if (rs.next()) { //check if a result is found
                int customerId = rs.getInt("customer_id"); //retrieve customer ID
                String name = rs.getString("customer_name"); //retrieve customer name
                String address = rs.getString("customer_address"); //retrieve customer address
                result = "ID: " + customerId + ", Name: " + name + ", Address: " + address; //format the result
            } else {
                result = "No customer with ID: " + id + " exists"; //message if no customer is found
            }
        } catch (SQLException e) {
            e.printStackTrace(); //prints sql exception details
        }
        return result; //return final result
    }

  //a method to insert a customer with their values
    public void insertCustomer(int id, String name, String address, String contactInfo, String gsm) {
        String query = "INSERT INTO CUSTOMERS (customer_id, customer_name, customer_address, customer_contact_info, customer_gsm) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            //sets the entered values into the query and this query inserts values to the customers table.
        	statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, address);
            statement.setString(4, contactInfo);
            statement.setString(5, gsm);
            int result = statement.executeUpdate(); //execute the insert operation and this method returns the number of effected rows.
            if (result > 0) { //checks if rows were affected
                System.out.println("Customer inserted successfully"); //success message
            } else {
                System.out.println("Problem inserting customer"); //failure message
            }
        } catch (SQLException e) {
            e.printStackTrace(); //prints sql exception details
        }
    }

  //a method to delete a customer by their ID
    public void deleteCustomerById(int id) {
        String query = "DELETE FROM CUSTOMERS WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); //the first number represents the position for ? and the second argument SETS the value of the given to that ?
            int result = statement.executeUpdate(); //execute the delete operation and this method returns the number of effected rows.
            if (result > 0) { //checks if rows were affected
                System.out.println("Customer with ID: " + id + " deleted"); //success message
            } else {
                System.out.println("No customer deleted (ID not found)"); //failure message
            }
        } catch (SQLException e) {
            e.printStackTrace(); //prints sql exception details
        }
    }
  //a method to update a customer informations by their ID
    public void updateCustomer(int id, String name, String address, String contactInfo, String gsm) {
        String query = "UPDATE CUSTOMERS SET customer_name = ?, customer_address = ?, customer_contact_info = ?, customer_gsm = ? WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	//sets the entered values into the query and this query updates values for the given id record
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, contactInfo);
            statement.setString(4, gsm);
            statement.setInt(5, id);
            int result = statement.executeUpdate(); //execute the update operation and this method returns the number of effected rows.
            if (result > 0) { //checks if rows were affected
                System.out.println("Customer updated successfully"); //success message
            } else {
                System.out.println("No customer updated (ID not found)"); //failure message
            }
        } catch (SQLException e) {
            e.printStackTrace(); //prints sql exception details
        }
    }
}
