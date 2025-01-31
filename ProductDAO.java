//import rerquirements
import java.sql.*;

public class ProductDAO {

    private Connection connection; //connection instance

    // Constructor to initialize the ProductDAO with a database connection.
    public ProductDAO(Connection connection) {
        this.connection = connection;
    }
    //method to insert a new product into the PRODUCTS table.
    public void insertProduct(int productId, String name, double price, String category, String size, String color) {
        String query = "INSERT INTO PRODUCTS (product_id, product_name, price, category, size, color) VALUES (?, ?, ?, ?, ?, ?)"; //sql query to insert a product.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId); //first ?
            statement.setString(2, name); //second ?
            statement.setDouble(3, price); //third ? 
            statement.setString(4, category); //fourth ?
            statement.setString(5, size); // fifth ?
            statement.setString(6, color); //sixth ?
            
            //executing the query and checking if the product is inserted successfully.
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Product inserted successfully");
            } else {
                System.out.println("Problem inserting product");
            }
        } catch (SQLException e) {
            e.printStackTrace(); //print any SQLException
        }
    }


    //method to delete a product from the PRODUCTS table by its ID.
    public void deleteProductById(int id) throws SQLException {
        String query = "DELETE FROM PRODUCTS WHERE product_id = ?"; //sql query to delete a product by its ID.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); //first ?
            
            //executing the delete query and checking the result.
            int result = statement.executeUpdate();
            if (result == 0)
                System.out.println("No product deleted");
            else
                System.out.println("Product with ID: " + id + " deleted");
        }
    }

    //method to get the details of a product by its ID.
    public String getProductById(int id) throws SQLException {
        String query = "SELECT * FROM PRODUCTS WHERE product_id = ?"; //sql query to select a product by its ID.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); //setting the product name for ?
            
            //executing the query and checking if the product exists
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	//extracting product details from the result set.
                String name = rs.getString("product_name");
                double price = rs.getDouble("price");
                return "ID: " + id + ", Name: " + name + ", Price: " + price;
            }
        }
        return "No product with ID: " + id + " exists"; //If no product is found
    }
    //method to get the product ID by its name.
    public int getProductIdByName(String productName) {
        String query = "SELECT product_id FROM PRODUCTS WHERE product_name = ?"; //sql query to select the product ID by product name.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productName); //setting the product name. first ? 
            //executing the query to find the product ID
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("product_id"); //returning the product ID if found.
                } else {
                    System.out.println("No product found with name: " + productName); //if no product is found return -1
                    return -1; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); //print any SQLException
            return -1; //return -1 if an exception occurs.
        }
    }
    //method to update the details of an existing product by its ID.
    public void updateProduct(int productId, String name, double price, String category, String size, String color) throws SQLException {
        String query = "UPDATE PRODUCTS SET product_name = ?, price = ?, category = ?, size = ?, color = ? WHERE product_id = ?"; //sql query to update a product by its ID.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	//setting the parameters for the SQL query.
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, category);
            statement.setString(4, size);
            statement.setString(5, color);
            statement.setInt(6, productId);
            int result = statement.executeUpdate(); //executing the update query and checking the result.
            if (result > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("No product found with the given ID.");
            }
        }
    }


}
