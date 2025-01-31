//import requirements
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:sqlite:/Users/eylulakboga/Documents/inventory.db"; //database url for sql connection
    private static Connection connection; //connection instance
    private static SupplierDAO supplierDAO; //Data access object for suppliers
    private static PurchasesDAO purchasesDAO; //Data access object for purchases
    private static ProductDAO productDAO; //Data access object for products
    private static CustomerDAO customerDAO; //Data access object for customers
    private static StaffDAO staffDAO; //Data access object for staff
    private static InventoryDAO inventoryDAO; //Data access object for inventory
    private static SaleDAO saleDAO; //Data access object for sale

    //a method for connect to the database and return the connection
    public static Connection connectDatabase() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:/Users/eylulakboga/Documents/inventory.db");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
            return null;
        }
    }
    //a method to get connection
    public static Connection getConnection() {
        if (connection == null) { //if the connection is not already established
            try {
                connection = DriverManager.getConnection(URL); //initialize the connection with defined url and set up the database 
                setupDatabase(); 
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection; //return the connection
    }

    //create database tables if they don't already exist
    public static void setupDatabase() {
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement(); //this line creates a statement object from the connection instance.

            //for products table
            String createProductTable = "CREATE TABLE IF NOT EXISTS PRODUCTS (" +
                    "product_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "product_name TEXT NOT NULL, " +
                    "price REAL NOT NULL, " +
                    "category TEXT, " +
                    "size TEXT, " +
                    "color TEXT)";
statement.executeUpdate(createProductTable);  //executeUpdate is used to execute SQL statements that modify the database

		//for sales table
		String createSalesTable = "CREATE TABLE IF NOT EXISTS SALES (" +
		                  "sales_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
		                  "product_id INTEGER, " +
		                  "sales_quantity INTEGER, " +
		                  "sales_date TEXT, " +
		                  "customer_name TEXT, " +
		                  "staff_id INTEGER, " +
		                  "FOREIGN KEY(product_id) REFERENCES PRODUCTS(product_id))";
		statement.executeUpdate(createSalesTable);
		
		//for suppliers table
		String createSupplierTable = "CREATE TABLE IF NOT EXISTS SUPPLIERS (" +
		                     "supplier_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
		                     "supplier_name TEXT NOT NULL, " +
		                     "supplier_address TEXT, " +
		                     "supplier_contact_info TEXT, " +
		                     "supplier_gsm TEXT)";
		statement.executeUpdate(createSupplierTable);
		
		//for customer table
		String createCustomerTable = "CREATE TABLE IF NOT EXISTS CUSTOMERS (" +
		                     "customer_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
		                     "customer_name TEXT, " +
		                     "customer_address TEXT, " +
		                     "customer_contact_info TEXT, " +
		                     "customer_gsm TEXT)";
		statement.executeUpdate(createCustomerTable);
		
		//for purchases table
		String createPurchasesTable = "CREATE TABLE IF NOT EXISTS PURCHASES (" +
		                      "purchase_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
		                      "staff_id INTEGER, " +
		                      "purchase_date TEXT, " +
		                      "product_id INTEGER, " +
		                      "purchases_price REAL, " +
		                      "purchases_quantity INTEGER, " +
		                      "supplier_name TEXT, " +
		                      "FOREIGN KEY(product_id) REFERENCES PRODUCTS(product_id))";
		statement.executeUpdate(createPurchasesTable);
		
		//for inventory table
		String createInventoryTable = "CREATE TABLE IF NOT EXISTS INVENTORY (" +
		                      "inventory_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
		                      "product_id INTEGER, " +
		                      "current_stock INTEGER, " +
		                      "FOREIGN KEY(product_id) REFERENCES PRODUCTS(product_id))";
		statement.executeUpdate(createInventoryTable);
		
	

        } catch (SQLException e) {
            e.printStackTrace(); //print the error stack trace if something goes wrong
           
        } finally {
            if (statement != null) { //ensures the statement is closed
                try {
                    statement.close(); //statement is closed
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

 //close the connection when the application is finished
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
               
            } catch (SQLException e) {
                e.printStackTrace();
               
            }
        }
    }

    // SupplierDAO
    public static SupplierDAO getSupplierDAO() {
        if (supplierDAO == null) {
            supplierDAO = new SupplierDAO(getConnection());
        }
        return supplierDAO;
    }

    // PurchasesDAO
    public static PurchasesDAO getPurchasesDAO() {
        if (purchasesDAO == null) {
            purchasesDAO = new PurchasesDAO(getConnection());
        }
        return purchasesDAO;
    }

    // ProductDAO
    public static ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = new ProductDAO(getConnection());
        }
        return productDAO;
    }

    // CustomerDAO
    public static CustomerDAO getCustomerDAO() {
        if (customerDAO == null) {
            customerDAO = new CustomerDAO(getConnection());
        }
        return customerDAO;
    }

    // StaffDAO
    public static StaffDAO getStaffDAO() {
        if (staffDAO == null) {
            staffDAO = new StaffDAO(getConnection());
        }
        return staffDAO;
    }

    // InventoryDAO
    public static InventoryDAO getInventoryDAO() {
        if (inventoryDAO == null) {
            inventoryDAO = new InventoryDAO(getConnection());
        }
        return inventoryDAO;
    }

    // SaleDAO
    public static SaleDAO getSaleDAO() {
        if (saleDAO == null) {
            saleDAO = new SaleDAO(getConnection());
        }
        return saleDAO;
    }
}
