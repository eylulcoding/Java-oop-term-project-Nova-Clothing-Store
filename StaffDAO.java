//import requirements
import java.sql.*;

public class StaffDAO {

    private Connection connection; //connection instance

    //constructor to initialize the DAO with a database connection.
    public StaffDAO(Connection connection) {
        this.connection = connection; 
    }

    //method to insert a new staff member into the STAFF table.
    public void insertStaff(int staffId, String name, String address, String gsm, String role) {
        String query = "INSERT INTO STAFF (staff_id, staff_name, staff_address, staff_gsm, staff_role) VALUES (?, ?, ?, ?, ?)"; //sql query to insert a staff record into the database.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	//bind the provided parameters to the query placeholders.
            statement.setInt(1, staffId); //first ?
            statement.setString(2, name); //second ?
            statement.setString(3, address); //third ?
            statement.setString(4, gsm); //fourth ?
            statement.setString(5, role); //fifth ?
            int result = statement.executeUpdate(); //execute the query and get the number of rows affected.
            if (result > 0) {
                System.out.println("Staff inserted successfully"); //success message
            } else {
                System.out.println("Problem inserting staff"); //failure message
            }
        } catch (SQLException e) {
            e.printStackTrace(); //print the stack trace
        }
    }

    //method to delete a staff member by their ID.
    public void deleteStaffById(int id) throws SQLException {
        String query = "DELETE FROM STAFF WHERE staff_id = ?"; //sql query to delete a staff record based on the staff ID.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	//bind the staff ID to the query placeholder.
            statement.setInt(1, id);
            int result = statement.executeUpdate(); //execute the query and get the number of rows affected.
            if (result == 0)
                System.out.println("No staff deleted"); //failure message
            else
                System.out.println("Staff with ID: " + id + " deleted"); //success message
        }
    }

    //method to retrieve a staff member's details by their ID.
    public String getStaffById(int id) throws SQLException {
        String query = "SELECT * FROM STAFF WHERE staff_id = ?"; //sql query to fetch a staff record based on the staff ID.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); // Bind the staff ID to the query placeholder.
            ResultSet rs = statement.executeQuery(); //execute the query and store the result in a ResultSet.
            if (rs.next()) { //check if the ResultSet contains a record.
                String name = rs.getString("staff_name"); //fetch staff name.
                String address = rs.getString("staff_address"); //fetch staff address.
                String gsm = rs.getString("staff_gsm"); //fetch staff gsm
                String role = rs.getString("staff_role"); //fetch staff role
                return "ID: " + id + ", Name: " + name + ", Address: " + address + ", GSM: " + gsm + ", Role: " + role;
            }
        }
        return "No staff with ID: " + id + " exists"; //return a message if no record is found
    }
    
    //method to update a staff member's details.
    public void updateStaff(int staffId, String name, String address, String gsm, String role) {
        String query = "UPDATE STAFF SET staff_name = ?, staff_address = ?, staff_gsm = ?, staff_role = ? WHERE staff_id = ?";  //sql query to update staff details based on the staff ID.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	//bind the provided parameters to the query placeholders.
            statement.setString(1, name); //set new staff name 
            statement.setString(2, address); //set new addressss
            statement.setString(3, gsm); //set new gsm
            statement.setString(4, role); //set new role
            statement.setInt(5, staffId); //set new staffID
            int rowsUpdated = statement.executeUpdate(); //execute the query and get the number of rows affected.
            if (rowsUpdated > 0) {
                System.out.println("Staff updated successfully."); //success message
            } else {
                System.out.println("No staff found with ID: " + staffId); //failure message
            }
        } catch (SQLException e) {
            e.printStackTrace(); //print the stack trace
        }
    }
}
