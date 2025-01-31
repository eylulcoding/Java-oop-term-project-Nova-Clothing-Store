// Import required libraries
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * Retail.java
 * This class represents a GUI based Retail management system
 * Features include managing products, staff, suppliers, customers, and transactions.
 */

public class Retail extends JFrame {

	// GUI component Declarations
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textProductId;
	private JTextField textProductName;
	private JTextField textProductPrice;
	private JTextField textSupplierId;
	private JTextField textSupplierName;
	private JTextField textSupplierAddress;
	private JTextField textSupplierContact;
	private JTextField textSupplierGsm;
	private JTextField textStaffId;
	private JTextField textStaffName;
	private JTextField textStaffAddress;
	private JTextField textStaffGsm;
	private JTextField textStaffRole;
	private JTextField textCustomerId;
	private JTextField textCustomerName;
	private JTextField textCustomerAddress;
	private JTextField textCustomerContact;
	private JTextField textCustomerGsm;
	private JComboBox<?>  comboBoxProductCategory;
	private JTable table;
	private JTextField textPurchasesDate;
	private JTextField textPurchasesSupplierId;
	private JTextField textPurchasesPurchasesId;
	private JTextField textPurchasesProductId;
	private JTextField textPurchasesQuantity;
	private JTextField textPurchasesPrice;
	private JTextField textSellingDate;
	private JTextField textSellingBillId;
	private JTextField textSellingCustomerID;
	private JTextField textSellingProductID;
	private JTextField textSellingQuantity;
	private JTextField textSellingStaffId;
	private JFrame frame;
	private JButton exportProductsButton;
	private JButton exportStaffButton;
	private JButton exportCustomerButton;
	private JButton exportSuppliersButton;
	private JButton exportSalesButton;
	private JButton exportPurchasesButton;
	private JButton importProductButton;

	
	// Database Connection String
	String url="jdbc:sqlite:inventory.db";
	String tableName = "PRODUCTS";
	Connection connection;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Retail frame = new Retail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
     * Loads product data from the database and populates the table.
     * @param productTable The table to populate.
     * This method retrieves product records from the PRODUCTS table in the database
     * and displays them in the provided JTable.
     */
	private void loadProductList(JTable productTable) { //FOR PRODUCTS
	    try (Connection conn = Database.connectDatabase()) {
	        String query = "SELECT * FROM PRODUCTS"; // SQL query to select all columns from the products table
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        // Get the model of the table and clear any existing rows
	        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
	        model.setRowCount(0);  

	        // Iterate through the result set and add rows to the table model
	        while (rs.next()) {
	            model.addRow(new Object[] { // the data in each row is fetched from rs line by line and added to the table
	                rs.getInt("product_id"),
	                rs.getString("product_name"),
	                rs.getDouble("price"),
	                rs.getString("category"),
	                rs.getString("size"),
	                rs.getString("color")
	            });
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error loading product list: " + ex.getMessage()); // Display an error message if the query fails
	    }
	}
	
	/**
     * Loads staff data from the database and populates the table.
     * @param staffTable The table to populate.
     * Retrieves staff records from the STAFF table in the database
     * and fills the JTable with staff details.
     */
	private void loadStaffList(JTable staffTable) { //FOR STAFF
	    try (Connection conn = Database.connectDatabase()) {
	        String query = "SELECT * FROM STAFF";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        
	        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
	        model.setRowCount(0);  

	        
	        while (rs.next()) {
	            model.addRow(new Object[] {
	                rs.getInt("staff_id"),
	                rs.getString("staff_name"),
	                rs.getString("staff_address"),
	                rs.getString("staff_gsm"),
	                rs.getString("staff_role")
	            });
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error loading staff list: " + ex.getMessage());
	    }
	}
	
	/**
     * Loads customer data from the database and populates the table.
     * @param customerTable The table to populate.
     * Retrieves customer records from the CUSTOMERS table in the database
     * and displays them in the provided JTable.
     */
	private void loadCustomerList(JTable customerTable) { //FOR CUSTOMER
	    try (Connection conn = Database.connectDatabase()) {
	        String query = "SELECT * FROM CUSTOMERS";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        
	        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
	        model.setRowCount(0);  

	        
	        while (rs.next()) {
	            model.addRow(new Object[] {
	                rs.getInt("customer_id"),
	                rs.getString("customer_name"),
	                rs.getString("customer_address"),
	                rs.getString("customer_contact_info"),
	                rs.getString("customer_gsm")
	            });
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error loading customer list: " + ex.getMessage());
	    }
	}
	
	/**
     * Loads supplier data from the database and populates the table.
     * @param supplierTable The table to populate.
     * Retrieves supplier records from the SUPPLIERS table in the database
     * and displays them in the provided JTable.
     */
	private void loadSupplierList(JTable supplierTable) { //FOR SUPPLİER
	    try (Connection conn = Database.connectDatabase()) {
	        String query = "SELECT * FROM SUPPLIERS";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	       
	        DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
	        model.setRowCount(0);  

	        
	        while (rs.next()) {
	            model.addRow(new Object[] {
	                rs.getInt("supplier_id"),
	                rs.getString("supplier_name"),
	                rs.getString("supplier_address"),
	                rs.getString("supplier_contact_info"),
	                rs.getString("supplier_gsm")
	            });
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error loading supplier list: " + ex.getMessage());
	    }
	}
	
	
	DefaultTableModel model = new DefaultTableModel();
	JTable tableProducts = new JTable(model);
	JTable tableSales = new JTable(model);
	
	/**
	 * Loads product data from the database and populates the JTable.
	 * Retrieves product details from the PRODUCTS table in the database
	 * and fills the JTable with product information such as product ID, name, color, size, and price.
	 */
	
	public void loadProductsIntoTable() { //FOR SALE
	    try (Connection conn = Database.connectDatabase()) {
	        String query = "SELECT product_id,product_name, color,size,price FROM PRODUCTS";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            model.addRow(new Object[]{rs.getInt("product_id"),rs.getString("product_name"),rs.getString("color"),rs.getString("size"),rs.getDouble("price")});
	        }
	        tableProducts.setModel(model);
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	String[] billColumns = { "Sales Date", "Bill ID", "Customer ID", "Product ID", "Sales Quantity", "Staff ID" };
	DefaultTableModel billTableModel = new DefaultTableModel(billColumns, 0);
	JTable tableBill = new JTable(billTableModel);
	
	
	
	/**
	 * Loads sales data from the database and populates the provided JTable.
	 * Retrieves sales records from the SALES table in the database
	 * and fills the JTable with sales details such as date, bill ID, customer ID, product ID, quantity, and staff ID.
	 * 
	 * @param salesTable The JTable to populate with sales data.
	 */
	private void loadSalesData(JTable salesTable) { //FOR SALE
		
        DefaultTableModel model = (DefaultTableModel) salesTable.getModel();
        model.setRowCount(0);
		
	    try (Connection conn = Database.connectDatabase()) {
	    	String query = "SELECT sales_date, bill_id, customer_id, product_id, sales_quantity, staff_id from SALES";


	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        

	        
	        while (rs.next()) {
	            model.addRow(new Object[] {
	                rs.getString("sales_date"),
	                rs.getInt("bill_id"),
	                rs.getInt("customer_id"),  
	                rs.getInt("product_id"),   
	                rs.getInt("sales_quantity"),
	                rs.getInt("staff_id")
	            });
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error loading sales data: " + ex.getMessage());
	    }
	}
	
	DefaultTableModel model1 = new DefaultTableModel();
	JTable tableProducts1 = new JTable(model1);
	
	String[] purchasesColumns = { "Purchase Date", "Supplier ID", "Purchase ID", "Product ID", "Quantity", "Price" };
	DefaultTableModel purchaseTableModel = new DefaultTableModel(purchasesColumns, 0);
	JTable tablePurchase = new JTable(purchaseTableModel);
	
	
	
	
	/**
	 * Loads purchase data from the database and populates the JTable.
	 * Retrieves purchase records from the PURCHASES table in the database
	 * and fills the JTable with purchase details such as date, supplier ID, purchase ID, product ID, quantity, and price.
	 * 
	 * @param tablePurchases The JTable to populate with purchase data.
	 */
	public void loadPurchasesData(JTable tablePurchases) { //FOR PURCHASE
	    
	    DefaultTableModel model = (DefaultTableModel) tablePurchases.getModel();
	    model.setRowCount(0);

	    try (Connection conn = Database.connectDatabase()) {
	        if (conn != null) {
	            
	            String query = "SELECT * FROM PURCHASES";
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query);

	            
	            while (rs.next()) {
	                
	                
	                String purchaseDate = rs.getString("purchases_date");
	                int supplierId = rs.getInt("supplier_id");
	                int purchasesId = rs.getInt("purchases_id");
	                int productName = rs.getInt("product_id");
	                int quantity = rs.getInt("purchases_quantity");
	                double price = rs.getDouble("purchases_price");

	                
	                model.addRow(new Object[]{
	                        purchaseDate, supplierId, purchasesId, productName, quantity, price
	                });
	            }
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error loading purchase data: " + ex.getMessage());
	    }
	}
	
	
	
	
	/**
	 * Loads product data into the purchases table.
	 * Retrieves product and purchase details from the PURCHASES table in the database
	 * and fills the JTable with details such as product ID, purchase ID, supplier ID, and price.
	 */
	private void loadProductsIntoPurchasesTable() { //FOR PURCHASE
	    
	    DefaultTableModel model1 = (DefaultTableModel) tableProducts1.getModel();
	    model1.setRowCount(0);  

	    
	    try (Connection conn = Database.connectDatabase()) {
	        
	        String query = "SELECT product_id, purchases_id, supplier_id, purchases_price FROM purchases";  
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        
	        while (rs.next()) {
	            int productId = rs.getInt("product_id");
	            int purchasesId = rs.getInt("purchases_id");
	            int supplierId = rs.getInt("supplier_id");
	            double price = rs.getDouble("purchases_price");

	           
	            model1.addRow(new Object[]{productId, purchasesId, supplierId, price});
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error loading product data: " + e.getMessage());
	    }
	}
	DefaultTableModel inventoryModel = new DefaultTableModel();
	

	
	JTable tableInventory = new JTable(inventoryModel);

	/**
	 * Loads inventory data from the database and populates the JTable.
	 * Retrieves inventory details from the INVENTORY table in the database
	 * and fills the JTable with product ID and current stock information.
	 */
	public void loadInventoryIntoTable() { //FOR SALES
	    try (Connection conn = Database.connectDatabase()) {
	        String query = "SELECT product_id, current_stock FROM INVENTORY";  
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        ResultSet rs = pstmt.executeQuery();

	        DefaultTableModel inventoryModel = (DefaultTableModel) tableInventory.getModel();
	        inventoryModel.setRowCount(0); 

	        while (rs.next()) {
	            
	            inventoryModel.addRow(new Object[]{rs.getInt("product_id"), rs.getInt("current_stock")});
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

DefaultTableModel inventoryModel1 = new DefaultTableModel();
	

	
	JTable tableInventory1 = new JTable(inventoryModel);
	
	/**
	 * Loads inventory data from the database into a secondary inventory table.
	 * Retrieves inventory details from the INVENTORY table in the database
	 * and fills the JTable with product ID and current stock information.
	 */
	public void loadInventoryIntoTable1() { //for PURCHASES
	    try (Connection conn = Database.connectDatabase()) {
	        String query = "SELECT product_id, current_stock FROM INVENTORY"; 
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        ResultSet rs = pstmt.executeQuery();

	        DefaultTableModel inventoryModel1 = (DefaultTableModel) tableInventory1.getModel();
	        inventoryModel.setRowCount(0); 

	        while (rs.next()) {
	            
	            inventoryModel.addRow(new Object[]{rs.getInt("product_id"), rs.getInt("current_stock")});
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	
	//FİLE
	// inventory import ve export
		private void exportInventoryToCSV() {
		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setDialogTitle("Export Inventory as CSV");
		    fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
		    int userSelection = fileChooser.showSaveDialog(this);

		    if (userSelection == JFileChooser.APPROVE_OPTION) {
		        File fileToSave = fileChooser.getSelectedFile();
		        if (!fileToSave.getAbsolutePath().endsWith(".csv")) {
		            fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");
		        }

		        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
		            writer.write("Product ID,Current Stock\n");
		            for (int i = 0; i < tableInventory.getRowCount(); i++) {
		                int productId = (int) tableInventory.getValueAt(i, 0);
		                int currentStock = (int) tableInventory.getValueAt(i, 1);
		                writer.write(productId + "," + currentStock + "\n");
		            }
		            JOptionPane.showMessageDialog(null, "Inventory data successfully exported.");
		        } catch (IOException ex) {
		            JOptionPane.showMessageDialog(null, "Error writing file: " + ex.getMessage());
		        }
		    }
		}

		// exportTabletoCSV
				private void exportTableToCSV(String tableName, String[] columns, String fileName) {
				    try (FileWriter writer = new FileWriter(fileName)) {
				        try (Connection conn = Database.connectDatabase()) {
				            String query = "SELECT * FROM " + tableName;
				            Statement stmt = conn.createStatement();
				            ResultSet rs = stmt.executeQuery(query);
				            
				            writer.write(String.join(",", columns));
				            writer.write("\n");
				            
				            while (rs.next()) {
				                for (int i = 0; i < columns.length; i++) {
				                    writer.write(rs.getString(columns[i]));
				                    if (i < columns.length - 1) {
				                        writer.write(",");
				                    }
				                }
				                writer.write("\n");
				            }
				           
				            JOptionPane.showMessageDialog(null, "Data exported successfully to: " + fileName,
				                                          "Export Successful", JOptionPane.INFORMATION_MESSAGE);
				        }
				    } catch (SQLException | IOException ex) {
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(null, "Failed to export data: " + ex.getMessage(),
				                                      "Export Error", JOptionPane.ERROR_MESSAGE);
				    }
				}
				
				String[] columnNames = {"Product ID", "Product Name", "Price", "Category", "Size", "Color"};
				DefaultTableModel model4 = new DefaultTableModel(columnNames, 0);
				JTable productTable = new JTable(model4);


		// file import
				private void importDataFromCSV(String fileName) {
				    // JFileChooser ile dosya seçme
				    JFileChooser fileChooser = new JFileChooser();
				    int returnValue = fileChooser.showOpenDialog(null);
				    if (returnValue == JFileChooser.APPROVE_OPTION) {
				        File selectedFile = fileChooser.getSelectedFile();
				        fileName = selectedFile.getAbsolutePath();  
				        
				        try {
				            
				            BufferedReader br = new BufferedReader(new FileReader(fileName));
				            String line;
				            br.readLine(); 
				            
				            Connection conn = Database.connectDatabase(); 
				            
				            String sql = "INSERT INTO products (product_id, product_name, price, category, size, color) VALUES (?, ?, ?, ?, ?, ?)";
				            PreparedStatement pstmt = conn.prepareStatement(sql);
				            while ((line = br.readLine()) != null) {
				                // Veriyi işlemden geçir
				                String[] values = line.split(","); 
				                
				                int productId = Integer.parseInt(values[0].trim());
				                String productName = values[1].trim();
				                double price = Double.parseDouble(values[2].trim());
				                String category = values[3].trim();
				                String size = values[4].trim();
				                String color = values[5].trim();
				                
				                pstmt.setInt(1, productId);
				                pstmt.setString(2, productName);
				                pstmt.setDouble(3, price);
				                pstmt.setString(4, category);
				                pstmt.setString(5, size);
				                pstmt.setString(6, color);
				                
				                pstmt.executeUpdate();  
				            }
				            br.close();
				            pstmt.close();
				            conn.close();
				            loadProductList(productTable); 
				            JOptionPane.showMessageDialog(null, "Data imported and saved to database successfully.");
				        } catch (IOException | SQLException ex) {
				            JOptionPane.showMessageDialog(null, "Error importing data: " + ex.getMessage());
				        }
				    }
				}



		





	/**
	 * Create the frame.
	 */
	public Retail() {
		
//**** MAIN FORM	*************************
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1253, 719);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 1239, 682);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 60, 1219, 612);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel pan1=new JPanel();
		pan1.setBackground(Color.RED);
		
		JPanel pan3=new JPanel();
		pan3.setBackground(new Color(255, 255, 198));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFocusable(false);
		tabbedPane.setBounds(10, 22, 1199, 580);
		panel_1.add(tabbedPane);
		tabbedPane.add("DESCRIPTIONS",pan1);
		
		//FİLE
		
		exportProductsButton = new JButton("Export");
		exportProductsButton.setBounds(1005, 421, 137, 35);
		exportProductsButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		exportTableToCSV(
		"PRODUCTS",
		new String[]{"product_id", "product_name", "price", "category", "size", "color"},
		"products.csv"
					);
				}
			});
		
		importProductButton = new JButton("Import");
		importProductButton.setBounds(856, 421, 137, 35);
		importProductButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		importDataFromCSV(null);
		loadProductList(productTable);
				}
			});
		  
		
		exportStaffButton = new JButton("Export");
		exportStaffButton.setBounds(992, 426, 137, 35);
		exportStaffButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		exportTableToCSV(
		"STAFF",
		new String[]{"staff_id", "staff_name", "staff_address", "staff_gsm", "staff_role"},
		"staff.csv"
					);
				}
			});
		
		exportCustomerButton = new JButton("Export");
		exportCustomerButton.setBounds(988, 426, 137, 35);
		exportCustomerButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		exportTableToCSV(
		"CUSTOMERS",
		new String[]{"customer_id", "customer_name", "customer_address", "customer_contact_info", "customer_gsm"},
		"customer.csv"
					);
				}
			});
		
		exportSuppliersButton = new JButton("Export");
		exportSuppliersButton.setBounds(991, 426, 137, 35);
		exportSuppliersButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		exportTableToCSV(
		"SUPPLIERS",
		new String[]{"supplier_id", "supplier_name", "supplier_address", "supplier_contact_info", "supplier_gsm"},
		"supplier.csv"
					);
				}
			});

		

		

//**************************************************************************************		
		JPanel pan2=new JPanel();
		pan2.setBackground(new Color(255, 255, 198));
		tabbedPane.add("SALES",pan2);
		tabbedPane.setBackgroundAt(1, Color.green);
		pan2.setLayout(null);
		
		
		

		
		
		JLabel lblSelling = new JLabel("SELLING");
		lblSelling.setBounds(523, 5, 224, 60);
		lblSelling.setForeground(Color.BLACK);
		lblSelling.setFont(new Font("Poppins ExtraLight", Font.BOLD, 40));
		pan2.add(lblSelling);
		
		JLabel lblSellingDate = new JLabel("Date");
		lblSellingDate.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblSellingDate.setBounds(36, 75, 110, 25);
		pan2.add(lblSellingDate);
		
		textSellingDate = new JTextField();
		textSellingDate.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textSellingDate.setColumns(10);
		textSellingDate.setBounds(212, 75, 190, 25);
		pan2.add(textSellingDate);
		
		textSellingBillId = new JTextField();
		textSellingBillId.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textSellingBillId.setColumns(10);
		textSellingBillId.setBounds(212, 115, 50, 25);
		pan2.add(textSellingBillId);
		
		JLabel lblSellingBillId = new JLabel("Bill Id");
		lblSellingBillId.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblSellingBillId.setBounds(36, 115, 190, 25);
		pan2.add(lblSellingBillId);
		
		JLabel lblSellingCustomerId = new JLabel("Customer Id");
		lblSellingCustomerId.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblSellingCustomerId.setBounds(36, 165, 179, 25);
		pan2.add(lblSellingCustomerId);
		
		textSellingCustomerID = new JTextField();
		textSellingCustomerID.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textSellingCustomerID.setColumns(10);
		textSellingCustomerID.setBounds(212, 165, 251, 25);
		pan2.add(textSellingCustomerID);
		
		textSellingProductID = new JTextField();
		textSellingProductID.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textSellingProductID.setColumns(10);
		textSellingProductID.setBounds(212, 211, 251, 25);
		pan2.add(textSellingProductID);
		
		JLabel lblSellingProductID = new JLabel("Product Id");
		lblSellingProductID.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblSellingProductID.setBounds(36, 211, 179, 25);
		pan2.add(lblSellingProductID);
		
		JLabel lblSellingQuantity = new JLabel("Quantity");
		lblSellingQuantity.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblSellingQuantity.setBounds(36, 257, 166, 25);
		pan2.add(lblSellingQuantity);
		
		textSellingQuantity = new JTextField();
		textSellingQuantity.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textSellingQuantity.setColumns(10);
		textSellingQuantity.setBounds(212, 257, 50, 25);
		pan2.add(textSellingQuantity);
		
		textSellingStaffId = new JTextField();
		textSellingStaffId.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textSellingStaffId.setColumns(10);
		textSellingStaffId.setBounds(212, 303, 50, 25);
		pan2.add(textSellingStaffId);
		
		JLabel lblSellingStaffId = new JLabel("Staff Id");
		lblSellingStaffId.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblSellingStaffId.setBounds(36, 303, 154, 25);
		pan2.add(lblSellingStaffId);
		
		JButton btnSellingClear = new JButton("Clear");
		btnSellingClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSellingDate.setText(""); //in gui, set all fields to "" empty string.
		        textSellingBillId.setText("");
		        textSellingCustomerID.setText("");
		        textSellingProductID.setText("");
		        textSellingQuantity.setText("");
		        textSellingStaffId.setText("");
			}
		});
		btnSellingClear.setForeground(new Color(91, 255, 39));
		btnSellingClear.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnSellingClear.setFocusable(false);
		btnSellingClear.setBackground(new Color(0, 204, 0));
		btnSellingClear.setBounds(36, 355, 138, 35);
		pan2.add(btnSellingClear);
		
		loadSalesData(tableBill); //It is done so that the inventory list is automatically loaded into the table when the application is started.
		loadInventoryIntoTable(); //It is done so that the inventory list is automatically loaded into the table when the application is started.
		JButton btnSellingAddBill = new JButton(" Add Bill");
		
		//ADD BILL
		btnSellingAddBill.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            if (conn != null) {
		                
		                SaleDAO saleDAO = new SaleDAO(conn); // Initialize DAO objects
		                InventoryDAO inventoryDAO1 = new InventoryDAO(conn);  

		                
		                String saleDate = textSellingDate.getText();  // Retrieve input 
		                int billId = Integer.parseInt(textSellingBillId.getText());  
		                int customerId = Integer.parseInt(textSellingCustomerID.getText());  
		                int productId = Integer.parseInt(textSellingProductID.getText());  
		                int quantity = Integer.parseInt(textSellingQuantity.getText());     
		                int staffId = Integer.parseInt(textSellingStaffId.getText());       
		                
		                int currentStock = inventoryDAO1.getCurrentStock(productId, conn); // Get the current stock of the product
		                
		                
		                if (currentStock <= 0) {
		                    JOptionPane.showMessageDialog(null, "Sorry, the product is out of stock!");
		                    return; // Stop execution
		                } else if (quantity > currentStock) {
		                    JOptionPane.showMessageDialog(null, "Not enough stock available! Current stock: " + currentStock);
		                    return; // Stop execution
		                }
		                
		                
		                
		                // Insert sale data into the database and update inventory for after sale by using DAO methods
		                saleDAO.insertSale(saleDate, billId, customerId, productId, quantity, staffId);
		                //inventoryDAO1.insertInventory(productId,quantity);
		                inventoryDAO1.updateInventoryAfterSale(productId, quantity, conn); 

		                // Show success message and reload relevant tables
		                JOptionPane.showMessageDialog(null, "Bill Added Successfully!");
		                loadInventoryIntoTable();
		                loadSalesData(tableBill);
		                
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error adding bill: " + ex.getMessage()); //error 
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid data."); //error
		        }
		    }
		});


		btnSellingAddBill.setForeground(new Color(91, 255, 39));
		btnSellingAddBill.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnSellingAddBill.setFocusable(false);
		btnSellingAddBill.setBackground(new Color(0, 204, 0));
		btnSellingAddBill.setBounds(212, 355, 166, 35);
		pan2.add(btnSellingAddBill);
		
		//DELETE BİLL
		JButton btnSellingDelete = new JButton("Delete");

		btnSellingDelete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            if (conn != null) {
		               
		                SaleDAO saleDAO = new SaleDAO(conn); //initializin dao objects
		                InventoryDAO inventoryDAO = new InventoryDAO(conn);  

		                int billId = Integer.parseInt(textSellingBillId.getText()); //getting entered values

		               
		                saleDAO.deleteSaleById(billId); //from saleDAO class call it's delete method
		                int productId = Integer.parseInt(textSellingProductID.getText()); 
		                int quantity = Integer.parseInt(textSellingQuantity.getText()); 

		                
		                inventoryDAO.updateInventoryAfterSaleRollback(productId, quantity, conn); //from inventoryDAO class call it's after sale rollback method
		                // Show success message and reload relevant tables after modification
		                JOptionPane.showMessageDialog(null, "Bill deleted successfully.");
		                loadInventoryIntoTable();
		                loadSalesData(tableBill);
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error deleting bill: " + ex.getMessage()); //error
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid Bill ID."); //error
		        }
		    }
		});

		btnSellingDelete.setForeground(new Color(1, 3, 1));
		btnSellingDelete.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnSellingDelete.setFocusable(false);
		btnSellingDelete.setBackground(new Color(0, 204, 0));
		btnSellingDelete.setBounds(390, 355, 166, 35);
		pan2.add(btnSellingDelete);
		
		
		JLabel lblInventoryList = new JLabel("Inventory List");
		lblInventoryList.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblInventoryList.setBounds(36, 402, 179, 25);  
		pan2.add(lblInventoryList);
		inventoryModel.addColumn("Product ID");
		inventoryModel.addColumn("Current Stock");
		JScrollPane scrollPaneInventory = new JScrollPane(tableInventory);
		scrollPaneInventory.setBounds(36, 428, 520, 73); 
		pan2.add(scrollPaneInventory);

		
		//FİLE
		exportSalesButton = new JButton("Export");
		exportSalesButton.setBounds(50, 500, 137, 35);
		exportSalesButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		exportTableToCSV(
		"Inventory List",
		new String[]{"product_id", "current_stock"},
		"sales.csv"
					);
				}
			});
		
		pan2.add(exportSalesButton);
		

		
		
		
		JLabel lblSellingProductList = new JLabel("Product List");
		lblSellingProductList.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblSellingProductList.setBounds(670, 61, 179, 25);
		pan2.add(lblSellingProductList);
		
		
		model.addColumn("Product ID"); //adding columns
		model.addColumn("Product Name");
		model.addColumn("Color");
		model.addColumn("Size");
		model.addColumn("Price");
		
		JScrollPane scrollPaneSales = new JScrollPane(tableProducts);
		scrollPaneSales.setBounds(674, 98, 400, 150);
		pan2.add(scrollPaneSales);

		//this code is done so that when you click a row, it will automatically sets values to the text fields - additional 
		tableProducts.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = tableProducts.getSelectedRow(); //getting row values
		        if (selectedRow != -1) {
		            String selectedProductName = tableProducts.getValueAt(selectedRow, 0).toString();
		            textSellingProductID.setText(selectedProductName); //setting fields for that row
		        }
		        loadProductsIntoPurchasesTable();
		    }
		});


		
		loadProductsIntoTable(); //It is done so that the information is automatically loaded into the table when the application is started.
		
		
		JLabel lblSellingBill = new JLabel("Bill List");
		lblSellingBill.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblSellingBill.setBounds(589, 257, 110, 25);
		pan2.add(lblSellingBill);
		

		
		JScrollPane scrollPaneBill = new JScrollPane(tableBill);
		scrollPaneBill.setBounds(572, 295, 600, 200); 
		pan2.add(scrollPaneBill);
			
		
	
//*********************************************************************************************************		
		
		
		tabbedPane.add("PURCHASES",pan3);
		pan3.setLayout(null);
		
		JLabel lblPurchases = new JLabel("PURCHASES");
		lblPurchases.setBounds(488, 10, 334, 60);
		lblPurchases.setForeground(Color.BLACK);
		lblPurchases.setFont(new Font("Poppins ExtraLight", Font.BOLD, 40));
		pan3.add(lblPurchases);
		
		JLabel lblPurchasesDate = new JLabel("Date");
		lblPurchasesDate.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblPurchasesDate.setBounds(58, 87, 110, 25);
		pan3.add(lblPurchasesDate);
		
		textPurchasesDate = new JTextField();
		textPurchasesDate.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textPurchasesDate.setColumns(10);
		textPurchasesDate.setBounds(234, 87, 190, 25);
		pan3.add(textPurchasesDate);
		
		textPurchasesSupplierId = new JTextField();
		textPurchasesSupplierId.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textPurchasesSupplierId.setColumns(10);
		textPurchasesSupplierId.setBounds(234, 127, 251, 25);
		pan3.add(textPurchasesSupplierId);
		
		JLabel lblPurchasesSupplierId = new JLabel("Supplier ID");
		lblPurchasesSupplierId.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblPurchasesSupplierId.setBounds(58, 127, 190, 25);
		pan3.add(lblPurchasesSupplierId);
		
		JLabel lblPurchasesId = new JLabel("Purchases ID");
		lblPurchasesId.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblPurchasesId.setBounds(58, 177, 166, 25);
		pan3.add(lblPurchasesId);
		
		textPurchasesPurchasesId = new JTextField();
		textPurchasesPurchasesId.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textPurchasesPurchasesId.setColumns(10);
		textPurchasesPurchasesId.setBounds(234, 177, 50, 25);
		pan3.add(textPurchasesPurchasesId);
		
		textPurchasesProductId= new JTextField();
		textPurchasesProductId.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textPurchasesProductId.setColumns(10);
		textPurchasesProductId.setBounds(234, 223, 251, 25);
		pan3.add(textPurchasesProductId);
		
		JLabel lblPurchasesProducName = new JLabel("Product ID");
		lblPurchasesProducName.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblPurchasesProducName.setBounds(58, 223, 179, 25);
		pan3.add(lblPurchasesProducName);
		
		JLabel lblPurchasesQuantity = new JLabel("Quantity");
		lblPurchasesQuantity.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblPurchasesQuantity.setBounds(58, 269, 166, 25);
		pan3.add(lblPurchasesQuantity);
		
		textPurchasesQuantity = new JTextField();
		textPurchasesQuantity.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textPurchasesQuantity.setColumns(10);
		textPurchasesQuantity.setBounds(234, 269, 50, 25);
		pan3.add(textPurchasesQuantity);
		
		textPurchasesPrice = new JTextField();
		textPurchasesPrice.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textPurchasesPrice.setColumns(10);
		textPurchasesPrice.setBounds(234, 315, 50, 25);
		pan3.add(textPurchasesPrice);
		
		JLabel lblPurchasesPrice = new JLabel("Price");
		lblPurchasesPrice.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblPurchasesPrice.setBounds(58, 315, 154, 25);
		pan3.add(lblPurchasesPrice);
		
		//CLEAR PURCHASE
		JButton btnPurchasesClear = new JButton("Clear");
		btnPurchasesClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPurchasesDate.setText(""); //in gui, set all fields to "" empty string.
		        textPurchasesSupplierId.setText("");
		        textPurchasesPurchasesId.setText("");
		        textPurchasesProductId.setText("");
		        textPurchasesQuantity.setText("");
		        textPurchasesPrice.setText("");
			}
		});
		btnPurchasesClear.setForeground(new Color(91, 255, 39));
		btnPurchasesClear.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnPurchasesClear.setFocusable(false);
		btnPurchasesClear.setBackground(new Color(0, 204, 0));
		btnPurchasesClear.setBounds(58, 367, 138, 35);
		pan3.add(btnPurchasesClear);
		

		loadPurchasesData(tablePurchase); //It is done so that the purchase list is automatically loaded into the table when the application is started.
		loadInventoryIntoTable(); //It is done so that the inventory list is automatically loaded into the table when the application is started.
		
		//ADD PURCHASE
		
		JButton btnPurchasesAdd = new JButton(" Add");


		btnPurchasesAdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            if (conn != null) {
		                
		                PurchasesDAO purchasesDAO = new PurchasesDAO(conn);//initializing dao objects

		                
		                String purchaseDate = textPurchasesDate.getText();  //getting entered values
		                int supplierId = Integer.parseInt(textPurchasesSupplierId.getText());   
		                int purchasesId = Integer.parseInt(textPurchasesPurchasesId.getText());  
		                int productId = Integer.parseInt(textPurchasesProductId.getText());     
		                int quantity = Integer.parseInt(textPurchasesQuantity.getText());        
		                double price = Double.parseDouble(textPurchasesPrice.getText());         

		               

		                InventoryDAO inventoryDAO2 = new InventoryDAO(conn);//initializing dao objects
		                
		                
		                
		                
		                
		                purchasesDAO.insertPurchase(purchaseDate, supplierId, purchasesId, productId, quantity, price); //from purchaseDAO class insert purchase
		                
		                if(inventoryDAO2.checkInventory(productId) == false) {
		                	inventoryDAO2.insertInventory(productId, quantity); //if we enter the first time
		                }
		                else {
		                	inventoryDAO2.updateInventoryAfterPurchase(productId, quantity,conn); //from inventoryDAO class update inventory for after purchase
		                }
		                
		                JOptionPane.showMessageDialog(null, "Purchase Added Successfully!"); //success message
		                loadProductsIntoPurchasesTable(); //reload relevant tables after modification
		                loadPurchasesData(tablePurchase);
		                loadInventoryIntoTable1();

		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error adding purchase: " + ex.getMessage()); //error 
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid data."); //error
		        }
		    }
		});

		btnPurchasesAdd.setForeground(new Color(91, 255, 39));
		btnPurchasesAdd.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnPurchasesAdd.setFocusable(false);
		btnPurchasesAdd.setBackground(new Color(0, 204, 0));
		btnPurchasesAdd.setBounds(208, 367, 166, 35);
		pan3.add(btnPurchasesAdd);
		
		//DELETE PURCHASE
		JButton btnPurchasesDelete = new JButton("Delete");


		btnPurchasesDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            if (conn != null) {
		                
		                PurchasesDAO purchasesDAO = new PurchasesDAO(conn); //initializing dao objects
		                InventoryDAO inventoryDAO3 = new InventoryDAO(conn);
		                
		                int purchaseId = Integer.parseInt(textPurchasesPurchasesId.getText()); //getting entered values

		                
		                Purchase purchase = purchasesDAO.getPurchaseById(purchaseId); //retrieve purchase details by ID
		                
		                if (purchase != null) { //if purchase is found
		                    int productId = purchase.getProductId(); //get the product id
		                    int quantity = purchase.getQuantity(); //get the purchase quantity
		                    double price = purchase.getPrice();  //get the purchase price
		                    
		                    // Rollback inventory update for the deleted purchase
		                    inventoryDAO3.updateInventoryAfterPurchaseRollback(productId, quantity, conn);

		                    // Delete the purchase record
		                    purchasesDAO.deletePurchaseById(purchaseId);

		                    // Show success message and reload relevant tables
		                    JOptionPane.showMessageDialog(null, "Purchase deleted successfully.");
		                    loadProductsIntoPurchasesTable();
		                    loadPurchasesData(tablePurchase);
		                    loadInventoryIntoTable1();
		                } else {
		                    JOptionPane.showMessageDialog(null, "No purchase found with the provided ID."); //if no purchase is found
		                }
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error deleting purchase: " + ex.getMessage()); //error
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid Purchase ID."); //error
		        }
		    }
		});


		btnPurchasesDelete.setForeground(new Color(1, 3, 1));
		btnPurchasesDelete.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnPurchasesDelete.setFocusable(false);
		btnPurchasesDelete.setBackground(new Color(0, 204, 0));
		btnPurchasesDelete.setBounds(386, 367, 166, 35);
		pan3.add(btnPurchasesDelete);
		
		
		
		JLabel lblInventoryList1 = new JLabel("Inventory List");
		lblInventoryList1.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblInventoryList1.setBounds(36, 402, 179, 25); 
		pan3.add(lblInventoryList1);
		inventoryModel1.addColumn("Product ID");
		inventoryModel1.addColumn("Current Stock");
		JScrollPane scrollPaneInventory1 = new JScrollPane(tableInventory1);
		scrollPaneInventory1.setBounds(36, 428, 520, 60);  
		pan3.add(scrollPaneInventory1);
		
		//FİLE
		exportPurchasesButton = new JButton("Export");
		exportPurchasesButton.setBounds(36, 493, 137, 35);
		exportPurchasesButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		exportTableToCSV(
		"Inventory List",
		new String[]{"product_id", "current_stock"},
		"purchases.csv"
					);
				}
			});
		
		pan3.add(exportPurchasesButton);
		

		
		
		JLabel lblPurchasesProductList = new JLabel("Product List");
		lblPurchasesProductList.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblPurchasesProductList.setBounds(625, 87, 179, 25);
		pan3.add(lblPurchasesProductList);
		pan1.setLayout(null);
		
		
		model1.addColumn("Product ID"); //adding columns
		model1.addColumn("Purchases ID");
		model1.addColumn("Supplier ID");
		model1.addColumn("Purchases Price");

		JScrollPane scrollPaneProducts1 = new JScrollPane(tableProducts1); 
		scrollPaneProducts1.setBounds(625, 115, 547, 150);
		pan3.add(scrollPaneProducts1);

		//this code is done so that when you click a row, it will automatically sets values to the text fields - additional 
		tableProducts1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = tableProducts1.getSelectedRow(); 
		        if (selectedRow != -1) {
		            
		            String selectedProductId = tableProducts1.getValueAt(selectedRow, 0).toString();  //getting row values
		            String selectedSupplierId = tableProducts1.getValueAt(selectedRow, 2).toString(); 

		           
		            textPurchasesProductId.setText(selectedProductId); //setting fields for that row
		            textPurchasesSupplierId.setText(selectedSupplierId); 
		        }
		    }
		});
		
		loadProductsIntoPurchasesTable(); //It is done so that the information is automatically loaded into the table when the application is started.
		

		
		JLabel lblPurchasesInfo = new JLabel("Purchase List");
		lblPurchasesInfo.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblPurchasesInfo.setBounds(594, 274, 159, 25);
		pan3.add(lblPurchasesInfo);
		
		JScrollPane scrollPanePurchases = new JScrollPane(tablePurchase);
		scrollPanePurchases.setBounds(572, 311, 600, 184); 
		pan3.add(scrollPanePurchases);
		
		//this code is done so that when you click a row, it will automatically sets values to the text fields - additional 
		tablePurchase.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = tablePurchase.getSelectedRow(); 
		        if (selectedRow != -1) {
		            
		            String purchaseDate = tablePurchase.getValueAt(selectedRow, 0).toString(); //getting row values 
		            String purchasesId = tablePurchase.getValueAt(selectedRow, 2).toString();
		            String supplierId = tablePurchase.getValueAt(selectedRow, 1).toString();
		            String productId = tablePurchase.getValueAt(selectedRow, 3).toString();
		            String quantity = tablePurchase.getValueAt(selectedRow, 4).toString();
		            String price = tablePurchase.getValueAt(selectedRow, 5).toString();

		            
		            textPurchasesDate.setText(purchaseDate); //setting fields for that row
		            textPurchasesPurchasesId.setText(purchasesId);
		            textPurchasesSupplierId.setText(supplierId);
		            textPurchasesProductId.setText(productId);
		            textPurchasesQuantity.setText(quantity);
		            textPurchasesPrice.setText(price);
		        }
		    }
		});


			
	
//******************************************	
	 	
//***  PANELS DEFINATIONS  ****
		JPanel panProduct=new JPanel();
		panProduct.setBackground(new Color(255, 255, 198));
		panProduct.add(exportProductsButton);
		panProduct.add(importProductButton);
		
		JPanel panStaff=new JPanel();
		panStaff.setBackground(new Color(255, 255, 198));
		panStaff.add(exportStaffButton);
		
		JPanel panCustomer=new JPanel();
		panCustomer.setBackground(new Color(255, 255, 198));
		panCustomer.add(exportCustomerButton);
		
		JPanel panSuppliers=new JPanel();
		panSuppliers.setBackground(new Color(255, 255, 198));
		panSuppliers.add(exportSuppliersButton);
		
		
		JTabbedPane tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane1.setFocusable(false);
		tabbedPane1.setBounds(10, 20, 1174, 513);
		
		pan1.add(tabbedPane1);
	
//** PRODUCT PANEL ************************************************************************************************	
		tabbedPane1.add("PRODUCTS",panProduct);
		panProduct.setLayout(null);
		
		JLabel lblPeoductManagement = new JLabel("PRODUCT MANAGEMENT");
		lblPeoductManagement.setBounds(348, 0, 515, 60);
		lblPeoductManagement.setForeground(Color.BLACK);
		lblPeoductManagement.setFont(new Font("Poppins ExtraLight", Font.BOLD, 40));
		panProduct.add(lblPeoductManagement);
		
		JButton btnProductClear = new JButton("Clear");
		btnProductClear.setForeground(new Color(91, 255, 39));
		btnProductClear.setBounds(1009, 80, 111, 35);
		btnProductClear.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnProductClear.setBackground(new Color(0, 204, 0));
		btnProductClear.setFocusable(false);
		panProduct.add(btnProductClear);
		

		
//**********************************************		
				
		JButton btnProductAdd = new JButton("Add");
		btnProductAdd.setBounds(1009, 125, 111, 35);
		btnProductAdd.setForeground(new Color(91, 255, 39));
		btnProductAdd.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnProductAdd.setBackground(new Color(0, 204, 0));
		btnProductAdd.setFocusable(false);
		panProduct.add(btnProductAdd);
					
		JButton btnProductUpdate = new JButton("Update");
		btnProductUpdate.setForeground(new Color(91, 255, 39));
		btnProductUpdate.setBounds(1009, 170, 111, 35);
		btnProductUpdate.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnProductUpdate.setBackground(new Color(0, 204, 0));
		btnProductUpdate.setFocusable(false);
		panProduct.add(btnProductUpdate);
		
		JButton btnProductDelete = new JButton("Delete");
		btnProductDelete.setBounds(1009, 215, 111, 35);
		btnProductDelete.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnProductDelete.setBackground(new Color(255, 0, 0));
		btnProductDelete.setFocusable(false);
		panProduct.add(btnProductDelete);
				
		JLabel lblProductCategory = new JLabel("Category");
		lblProductCategory.setBounds(507, 80, 111, 25);
		lblProductCategory.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panProduct.add(lblProductCategory);
		
		String Category[]= {"Pants","Sweater","T-Shirt","Short","Shirt","Socks"};
		JComboBox<?> comboBoxProductCategory = new JComboBox<>(Category);
		comboBoxProductCategory.setModel(new DefaultComboBoxModel(new String[] {"Pants", "Sweater", "T-Shirt", "Short"}));
		comboBoxProductCategory.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		comboBoxProductCategory.setBounds(628, 80, 235, 25);
		panProduct.add(comboBoxProductCategory);
				
		JLabel lblProductSize = new JLabel("Size");
		lblProductSize.setBounds(507, 126, 111, 25);
		lblProductSize.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panProduct.add(lblProductSize);
		
		String Size[]= {"Extra Small", "Small","Medium","Large","Extra Large"};		
		JComboBox<?> comboBoxProductSize = new JComboBox<>(Size);
		comboBoxProductSize.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		comboBoxProductSize.setBounds(628, 126, 235, 25);
		panProduct.add(comboBoxProductSize);
		
		JLabel lblProductColor = new JLabel("Color");
		lblProductColor.setBounds(507, 172, 111, 25);
		lblProductColor.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panProduct.add(lblProductColor);
		
		String Color1[]= {"White","Black","Red","Blue","Pink","Green","Dark Blue"};
		JComboBox<?> comboBoxProductColor = new JComboBox<>(Color1);
		comboBoxProductColor.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		comboBoxProductColor.setBounds(628, 172, 235, 25);
		panProduct.add(comboBoxProductColor);
	
		JLabel lblProductPrice = new JLabel("Price");
		lblProductPrice.setBounds(43, 172, 75, 25);
		lblProductPrice.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panProduct.add(lblProductPrice);
		
		textProductPrice = new JTextField();
		textProductPrice.setBounds(219, 172, 176, 25);
		textProductPrice.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textProductPrice.setColumns(10);
		panProduct.add(textProductPrice);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(43, 126, 166, 25);
		lblProductName.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panProduct.add(lblProductName);
		
		textProductName = new JTextField();
		textProductName.setBounds(219, 126, 176, 25);
		textProductName.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textProductName.setColumns(10);
		panProduct.add(textProductName);
				
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(43, 80, 110, 25);
		lblProductId.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panProduct.add(lblProductId);
		
		textProductId = new JTextField();
		textProductId.setBounds(219, 80, 50, 25);
		textProductId.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textProductId.setColumns(10);
		panProduct.add(textProductId);
				
		JLabel lblProductList = new JLabel("Product  List");
		lblProductList.setForeground(Color.BLACK);
		lblProductList.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblProductList.setBounds(43, 219, 146, 25);
		panProduct.add(lblProductList);
		
		
		
		
		
		
		
		/*		
		//Defines the column headers for the table in GUI so we can see interactively
		String[] columnNames = {"Product ID", "Product Name", "Price", "Category", "Size", "Color"};
		//Creates a DefaultTableModel with column headers and no initial data
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		//Creates a JTable using the model to display data and headers
		JTable productTable = new JTable(model);
		*/

		//Wraps the JTable in a JScrollPane to allow scrolling when the content exceeds the visible area
		JScrollPane scrollPane = new JScrollPane(productTable);
		scrollPane.setBounds(43, 260, 1077, 149);  
		panProduct.add(scrollPane); //so it appears in the GUI
		
		panProduct.add(importProductButton);
		panProduct.add(exportProductsButton);

		
		loadProductList(productTable); //It is done so that the product list is automatically loaded into the table when the application is started.


		
		
		//****** PRESS PRODUCT CLEAR BUTTON  *******		
		
		btnProductClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
										
				textProductId.setText(""); //in gui, set all fields to "" empty string.
				textProductName.setText("");
				textProductPrice.setText("");
								
		}});


		//** PRESS PRODUCT ADD BUTTON  *****************			
		btnProductAdd.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            ProductDAO productDAO = new ProductDAO(conn);

		            
		            int productId = Integer.parseInt(textProductId.getText()); //getting entered values
		            String name = textProductName.getText();
		            double price = Double.parseDouble(textProductPrice.getText());
		            String category = comboBoxProductCategory.getSelectedItem().toString();
		            String size = comboBoxProductSize.getSelectedItem().toString();
		            String color = comboBoxProductColor.getSelectedItem().toString();

		            
		            productDAO.insertProduct(productId, name, price, category, size, color); //from productDAO class, it's insert method is called 

		            
		            JOptionPane.showMessageDialog(null, "Product added successfully."); //success message
		            loadProductList(productTable); //show product informations into the provided table after modification
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error adding product: " + ex.getMessage()); //error
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid data."); //error
		        }
		    }
		});

		
		////** PRESS PRODUCT UPDATE BUTTON  *****************			
		btnProductUpdate.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            ProductDAO productDAO = new ProductDAO(conn);

		            
		            int productId = Integer.parseInt(textProductId.getText()); //getting entered values
		            String name = textProductName.getText();
		            double price = Double.parseDouble(textProductPrice.getText());
		            String category = comboBoxProductCategory.getSelectedItem().toString();
		            String size = comboBoxProductSize.getSelectedItem().toString();
		            String color = comboBoxProductColor.getSelectedItem().toString();

		            
		            productDAO.updateProduct(productId, name, price, category, size, color); //from productDAO class, it's update method is called 

		            
		            JOptionPane.showMessageDialog(null, "Product updated successfully.");
		            loadProductList(productTable); //show product informations into the provided table after modification
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error updating product: " + ex.getMessage());
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid data.");
		        }
		    }
		});
		


		//** PRESS PRODUCT DELETE BUTTON  *****************			
		btnProductDelete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            ProductDAO productDAO = new ProductDAO(conn);

		            
		            int productId = Integer.parseInt(textProductId.getText());//getting entered values

		            
		            productDAO.deleteProductById(productId); //from productDAO class, it's delete method is called 

		            
		            JOptionPane.showMessageDialog(null, "Product deleted successfully."); //success message
		            loadProductList(productTable); //show product informations into the provided table after modification
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error deleting product: " + ex.getMessage()); //error
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid Product ID."); //error
		        }
		    }
		});

						
//*************************************************************************************************************				
				
//** STAFF PANEL    *****************************************
		tabbedPane1.add("STAFF",panStaff);
		panStaff.setLayout(null);
		
		JLabel lblStaffManage = new JLabel("STAFF MANAGEMENT");
		lblStaffManage.setForeground(Color.BLACK);
		lblStaffManage.setFont(new Font("Poppins ExtraLight", Font.BOLD, 40));
		lblStaffManage.setBounds(394, 0, 457, 60);
		panStaff.add(lblStaffManage);
		
		JButton btnStaffClear = new JButton("Clear");
		btnStaffClear.setForeground(new Color(91, 255, 39));
		btnStaffClear.setBounds(1009, 80, 111, 35);
		btnStaffClear.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnStaffClear.setBackground(new Color(0, 204, 0));
		btnStaffClear.setFocusable(false);
		panStaff.add(btnStaffClear);

		JButton btnStaffAdd = new JButton("Add");
		btnStaffAdd.setBounds(1009, 125, 111, 35);
		btnStaffAdd.setForeground(new Color(91, 255, 39));
		btnStaffAdd.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnStaffAdd.setBackground(new Color(0, 204, 0));
		btnStaffAdd.setFocusable(false);
		panStaff.add(btnStaffAdd);
		
		JButton btnStaffUpdate = new JButton("Update");
		btnStaffUpdate.setForeground(new Color(91, 255, 39));
		btnStaffUpdate.setBounds(1009, 170, 111, 35);
		btnStaffUpdate.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnStaffUpdate.setBackground(new Color(0, 204, 0));
		btnStaffUpdate.setFocusable(false);
		panStaff.add(btnStaffUpdate);
		
		JButton btnStaffDelete = new JButton("Delete");
		btnStaffDelete.setBounds(1009, 215, 111, 35);
		btnStaffDelete.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnStaffDelete.setBackground(new Color(255, 0, 0));
		btnStaffDelete.setFocusable(false);
		panStaff.add(btnStaffDelete);
				
		JLabel lblStaffId = new JLabel("Staff ID");
		lblStaffId.setBounds(43, 80, 109, 25);
		lblStaffId.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panStaff.add(lblStaffId);
		
		textStaffId = new JTextField();
		textStaffId.setBounds(219, 80, 50, 25);
		textStaffId.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textStaffId.setColumns(10);
		panStaff.add(textStaffId);
		
		JLabel lblStaffName = new JLabel("Staff Name");
		lblStaffName.setBounds(43, 126, 152, 25);
		lblStaffName.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panStaff.add(lblStaffName);
		
		textStaffName = new JTextField();
		textStaffName.setBounds(219, 126, 206, 25);
		textStaffName.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textStaffName.setColumns(10);
		panStaff.add(textStaffName);
				
		JLabel lblStaffAddress = new JLabel("Address");
		lblStaffAddress.setBounds(43, 172, 109, 25);
		lblStaffAddress.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panStaff.add(lblStaffAddress);
		
		textStaffAddress = new JTextField();
		textStaffAddress.setBounds(219, 172, 646, 25);
		textStaffAddress.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textStaffAddress.setColumns(10);
		panStaff.add(textStaffAddress);
		
		JLabel lblStaffGsm = new JLabel("GSM");
		lblStaffGsm.setBounds(590, 80, 46, 25);
		lblStaffGsm.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panStaff.add(lblStaffGsm);
		
		textStaffGsm = new JTextField();
		textStaffGsm.setBounds(659, 80, 206, 25);
		textStaffGsm.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textStaffGsm.setColumns(10);
		panStaff.add(textStaffGsm);
		
		JLabel lblStaffRole = new JLabel("Role");
		lblStaffRole.setBounds(590, 128, 124, 25);
		lblStaffRole.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panStaff.add(lblStaffRole);
				
		textStaffRole = new JTextField();
		textStaffRole.setBounds(659, 125, 206, 25);
		textStaffRole.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textStaffRole.setColumns(10);
		panStaff.add(textStaffRole);
				
		JLabel lblSatffList = new JLabel("Staff List");
		lblSatffList.setBounds(43, 218, 131, 25);
		lblSatffList.setForeground(Color.BLACK);
		lblSatffList.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panStaff.add(lblSatffList);
		
		//Defines the column headers for the table in GUI so we can see interactively
		String[] staffColumnNames = {"Staff ID", "Staff Name", "Address", "GSM", "Role"};
		//Creates a DefaultTableModel with column headers and no initial data
		DefaultTableModel staffModel = new DefaultTableModel(staffColumnNames, 0);
		//Creates a JTable using the staffModel to display data and headers
		JTable staffTable = new JTable(staffModel);

		//Wraps the JTable in a JScrollPane to allow scrolling when the content exceeds the visible area
		JScrollPane staffScrollPane = new JScrollPane(staffTable);
		staffScrollPane.setBounds(43, 260, 1077, 167);  
		panStaff.add(staffScrollPane); //so it appears in the GUI.

		
		loadStaffList(staffTable); //It is done so that the staff list is automatically loaded into the table when the application is started.


	
		//****** PRESS STAFF CLEAR BUTTON  *******		
		
		btnStaffClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
											
				textStaffId.setText(""); //in gui, set all fields to "" empty string.
				textStaffName.setText("");
				textStaffAddress.setText("");
				textStaffGsm.setText("");
				textStaffRole.setText("");
										
			}});
		
		//****** PRESS STAFF ADD BUTTON  *******	
		btnStaffAdd.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            if (conn != null) {
		                StaffDAO staffDAO = new StaffDAO(conn);
		                int staffId = Integer.parseInt(textStaffId.getText()); //getting entered values
		                String name = textStaffName.getText();
		                String address = textStaffAddress.getText();
		                String gsm = textStaffGsm.getText();
		                String role = textStaffRole.getText();

		                staffDAO.insertStaff(staffId, name, address, gsm, role); //from staffDAO class, it's insert method is called
		                JOptionPane.showMessageDialog(null, "Staff added successfully."); //success message 
		                loadStaffList(staffTable); //show staff informations into the provided table after modification
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error adding staff: " + ex.getMessage()); //error
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid data."); //error
		        }
		    }
		});

     	//** PRESS STAFF UPDATE BUTTON  *****************
		btnStaffUpdate.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            if (conn != null) {
		                StaffDAO staffDAO = new StaffDAO(conn);
		                int staffId = Integer.parseInt(textStaffId.getText()); //getting entered values
		                String name = textStaffName.getText();
		                String address = textStaffAddress.getText();
		                String gsm = textStaffGsm.getText();
		                String role = textStaffRole.getText();

		                staffDAO.updateStaff(staffId, name, address, gsm, role); //from staffDAO class, it's update method is called 
		                JOptionPane.showMessageDialog(null, "Staff updated successfully."); //success message 
		                loadStaffList(staffTable); //show staff informations into the provided table after modification
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error updating staff: " + ex.getMessage()); //error
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid data."); //error
		        }
		    }
		});



		//** PRESS STAFF DELETE BUTTON  *****************
		btnStaffDelete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            if (conn != null) {
		                StaffDAO staffDAO = new StaffDAO(conn); //getting entered values
		                int staffId = Integer.parseInt(textStaffId.getText());

		                staffDAO.deleteStaffById(staffId); //from staffDAO class, it's delete method is called 
		                JOptionPane.showMessageDialog(null, "Staff deleted successfully."); //success message 
		                loadStaffList(staffTable); //show staff informations into the provided table after modification
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error deleting staff: " + ex.getMessage()); //error
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid Staff ID."); //error
		        }
		    }
		});
		

		
		
		
		
//** CUSTOMER PANE  *************************************************************************************************	
		tabbedPane1.add("CUSTOMER",panCustomer);
		panCustomer.setLayout(null);
		
		JLabel lblCustomerManage = new JLabel("CUSTOMER MANAGEMENT");
		lblCustomerManage.setForeground(Color.BLACK);
		lblCustomerManage.setFont(new Font("Poppins ExtraLight", Font.BOLD, 40));
		lblCustomerManage.setBounds(350, 0, 553, 60);
		panCustomer.add(lblCustomerManage);
		
		JButton btnCustomerClear = new JButton("Clear");
		btnCustomerClear.setForeground(new Color(91, 255, 39));
		btnCustomerClear.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnCustomerClear.setFocusable(false);
		btnCustomerClear.setBackground(new Color(0, 204, 0));
		btnCustomerClear.setBounds(1009, 80, 111, 35);
		panCustomer.add(btnCustomerClear);
		
		JButton btnCustomerAdd = new JButton("Add");
		btnCustomerAdd.setForeground(new Color(91, 255, 39));
		btnCustomerAdd.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnCustomerAdd.setFocusable(false);
		btnCustomerAdd.setBackground(new Color(0, 204, 0));
		btnCustomerAdd.setBounds(1009, 125, 111, 35);
		panCustomer.add(btnCustomerAdd);
		
		JButton btnCustomerUpdate = new JButton("Update");
		btnCustomerUpdate.setForeground(new Color(91, 255, 39));
		btnCustomerUpdate.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnCustomerUpdate.setFocusable(false);
		btnCustomerUpdate.setBackground(new Color(0, 204, 0));
		btnCustomerUpdate.setBounds(1009, 170, 111, 35);
		panCustomer.add(btnCustomerUpdate);
		
		JButton btnCustomerDelete = new JButton("Delete");
		btnCustomerDelete.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnCustomerDelete.setFocusable(false);
		btnCustomerDelete.setBackground(Color.RED);
		btnCustomerDelete.setBounds(1009, 215, 111, 35);
		panCustomer.add(btnCustomerDelete);
			
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(43, 80, 153, 25);
		lblCustomerId.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panCustomer.add(lblCustomerId);
			
		textCustomerId = new JTextField();
		textCustomerId.setBounds(219, 80, 50, 25);
		textCustomerId.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textCustomerId.setColumns(10);
		panCustomer.add(textCustomerId);
				
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(43, 126, 187, 25);
		lblCustomerName.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panCustomer.add(lblCustomerName);
		
		textCustomerName = new JTextField();
		textCustomerName.setBounds(219, 126, 206, 25);
		textCustomerName.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textCustomerName.setColumns(10);
		panCustomer.add(textCustomerName);
		
		JLabel lblCustomerAddress = new JLabel("Address");
		lblCustomerAddress.setBounds(43, 172, 122, 25);
		lblCustomerAddress.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panCustomer.add(lblCustomerAddress);
			
		textCustomerAddress = new JTextField();
		textCustomerAddress.setBounds(219, 172, 646, 25);
		textCustomerAddress.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textCustomerAddress.setColumns(10);
		panCustomer.add(textCustomerAddress);
		
		JLabel lblCustomerContact = new JLabel("Contactinfo");
		lblCustomerContact.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblCustomerContact.setBounds(483, 80, 166, 25);
		panCustomer.add(lblCustomerContact);
				
		textCustomerContact = new JTextField();
		textCustomerContact.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textCustomerContact.setColumns(10);
		textCustomerContact.setBounds(659, 80, 206, 25);
		panCustomer.add(textCustomerContact);
		
		JLabel lblCustomerGsm = new JLabel("GSM");
		lblCustomerGsm.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblCustomerGsm.setBounds(483, 126, 166, 25);
		panCustomer.add(lblCustomerGsm);
				
		textCustomerGsm = new JTextField();
		textCustomerGsm.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textCustomerGsm.setColumns(10);
		textCustomerGsm.setBounds(659, 126, 206, 25);
		panCustomer.add(textCustomerGsm);
		
		JLabel lblCustomerList = new JLabel("Customers List");
		lblCustomerList.setForeground(Color.BLACK);
		lblCustomerList.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblCustomerList.setBounds(43, 218, 156, 25);
		panCustomer.add(lblCustomerList);
		
		//Defines the column headers for the table in GUI so we can see interactively
		String[] customerColumnNames = {"Customer ID", "Customer Name", "Customer Address", "Customer Contact Info", "Customer GSM"};
		//Creates a DefaultTableModel with column headers and no initial data
		DefaultTableModel customerModel = new DefaultTableModel(customerColumnNames, 0);
		//Creates a JTable using the customerModel to display data and headers
		JTable customerTable = new JTable(customerModel);

		//Wraps the JTable in a JScrollPane to allow scrolling when the content exceeds the visible area
		JScrollPane customerScrollPane = new JScrollPane(customerTable);
		customerScrollPane.setBounds(43, 260, 1077, 158);  
		panCustomer.add(customerScrollPane); //so it appears in the GUI.

		
		loadCustomerList(customerTable); //It is done so that the customer list is automatically loaded into the table when the application is started.



				//****** PRESS CUSTOMER CLEAR BUTTON  *******		
		
				btnCustomerClear.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
													
						textCustomerId.setText(""); //in gui, set all fields to "" empty string.
						textCustomerName.setText("");
						textCustomerAddress.setText("");
						textCustomerContact.setText("");
						textCustomerGsm.setText("");
							
					}});

				//****** PRESS CUSTOMER ADD BUTTON  *******	
				btnCustomerAdd.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        try (Connection conn = Database.connectDatabase()) {
				            if (conn != null) {
				                CustomerDAO customerDAO = new CustomerDAO(conn);
				                int id = Integer.parseInt(textCustomerId.getText()); //getting entered values
				                String name = textCustomerName.getText();
				                String address = textCustomerAddress.getText();
				                String contactInfo = textCustomerContact.getText();
				                String gsm = textCustomerGsm.getText();
				                customerDAO.insertCustomer(id, name, address, contactInfo, gsm); //from customerDAO class, it's insert method is called 
				                JOptionPane.showMessageDialog(null, "Customer added successfully."); //success message
				                loadCustomerList(customerTable); //show customer informations into the provided table after modification 
				            }
				        } catch (SQLException ex) {
				            JOptionPane.showMessageDialog(null, "Error adding customer: " + ex.getMessage()); //error
				        } catch (NumberFormatException ex) {
				            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid data."); //error
				        }
				    }
				});				
				


				//****** PRESS CUSTOMER UPDATE BUTTON  *******	
				btnCustomerUpdate.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        try (Connection conn = Database.connectDatabase()) {
				            if (conn != null) {
				                CustomerDAO customerDAO = new CustomerDAO(conn);
				                int id = Integer.parseInt(textCustomerId.getText()); //getting entered values
				                String name = textCustomerName.getText();
				                String address = textCustomerAddress.getText();
				                String contactInfo = textCustomerContact.getText();
				                String gsm = textCustomerGsm.getText();
				                customerDAO.updateCustomer(id, name, address, contactInfo, gsm); //from customerDAO class, it's update method is called 
				                JOptionPane.showMessageDialog(null, "Customer updated successfully."); //success message
				                loadCustomerList(customerTable); //show customer informations into the provided table after modification 
				            }
				        } catch (SQLException ex) {
				            JOptionPane.showMessageDialog(null, "Error updating customer: " + ex.getMessage()); //error message
				        } catch (NumberFormatException ex) {
				            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid data."); //error message
				        }
				    }
				});



				//****** PRESS CUSTOMER DELETE BUTTON  *******	
				btnCustomerDelete.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        try (Connection conn = Database.connectDatabase()) {
				            if (conn != null) {
				                CustomerDAO customerDAO = new CustomerDAO(conn);
				                int id = Integer.parseInt(textCustomerId.getText()); //getting entered values
				                customerDAO.deleteCustomerById(id); //from customerDAO class, it's delete method is called 
				                JOptionPane.showMessageDialog(null, "Customer deleted successfully."); //success message
				                loadCustomerList(customerTable); //show customer informations into the provided table after modification 
				            }
				        } catch (SQLException ex) {
				            JOptionPane.showMessageDialog(null, "Error deleting customer: " + ex.getMessage()); //error
				        } catch (NumberFormatException ex) {
				            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid Customer ID."); //error
				        }
				    }
				});

				
//**************************************************************************************************************		
	
		
		
//** SUPPLIERS PANE  **********************************************	
		tabbedPane1.add("SUPPLIERS",panSuppliers);
		tabbedPane1.setBackgroundAt(3, Color.green);
		panSuppliers.setLayout(null);
		
		JLabel lblSuppliersManagement = new JLabel("SUPPLIERS MANAGEMENT");
		lblSuppliersManagement.setBounds(349, 0, 550, 60);
		lblSuppliersManagement.setForeground(Color.BLACK);
		lblSuppliersManagement.setFont(new Font("Poppins ExtraLight", Font.BOLD, 40));
		panSuppliers.add(lblSuppliersManagement);
		
		JButton btnSupplierClear = new JButton("Clear");
		btnSupplierClear.setForeground(new Color(91, 255, 39));
		btnSupplierClear.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnSupplierClear.setFocusable(false);
		btnSupplierClear.setBackground(new Color(0, 204, 0));
		btnSupplierClear.setBounds(1009, 80, 111, 35);
		panSuppliers.add(btnSupplierClear);
			
		JButton btnSupplierAdd = new JButton("Add");
		btnSupplierAdd.setForeground(new Color(91, 255, 39));
		btnSupplierAdd.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnSupplierAdd.setFocusable(false);
		btnSupplierAdd.setBackground(new Color(0, 204, 0));
		btnSupplierAdd.setBounds(1009, 125, 111, 35);
		panSuppliers.add(btnSupplierAdd);
			
		JButton btnSupplierUpdate = new JButton("Update");
		btnSupplierUpdate.setForeground(new Color(91, 255, 39));
		btnSupplierUpdate.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnSupplierUpdate.setFocusable(false);
		btnSupplierUpdate.setBackground(new Color(0, 204, 0));
		btnSupplierUpdate.setBounds(1009, 170, 111, 35);
		panSuppliers.add(btnSupplierUpdate);
			
		JButton btnSupplierDelete = new JButton("Delete");
		btnSupplierDelete.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		btnSupplierDelete.setFocusable(false);
		btnSupplierDelete.setBackground(Color.RED);
		btnSupplierDelete.setBounds(1009, 215, 111, 35);
		panSuppliers.add(btnSupplierDelete);
						
		JLabel lblSupplierId = new JLabel("Supplier ID");
		lblSupplierId.setBounds(43, 80, 153, 25);
		lblSupplierId.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panSuppliers.add(lblSupplierId);
			
		textSupplierId = new JTextField();
		textSupplierId.setBounds(219, 80, 50, 25);
		textSupplierId.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textSupplierId.setColumns(10);
		panSuppliers.add(textSupplierId);
			
		JLabel lblSuplierName = new JLabel("Supplier Name");
		lblSuplierName.setBounds(43, 126, 187, 25);
		lblSuplierName.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panSuppliers.add(lblSuplierName);
		
		textSupplierName = new JTextField();
		textSupplierName.setBounds(219, 126, 206, 25);
		textSupplierName.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textSupplierName.setColumns(10);
		panSuppliers.add(textSupplierName);
						
		JLabel lblSuplierAdres = new JLabel("Adress");
		lblSuplierAdres.setBounds(43, 172, 122, 25);
		lblSuplierAdres.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		panSuppliers.add(lblSuplierAdres);
			
		textSupplierAddress = new JTextField();
		textSupplierAddress.setBounds(219, 172, 646, 25);
		textSupplierAddress.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textSupplierAddress.setColumns(10);
		panSuppliers.add(textSupplierAddress);
							
		JLabel lblSuplierContact = new JLabel("Contactinfo");
		lblSuplierContact.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblSuplierContact.setBounds(483, 80, 166, 25);
		panSuppliers.add(lblSuplierContact);
				
		textSupplierContact = new JTextField();
		textSupplierContact.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textSupplierContact.setColumns(10);
		textSupplierContact.setBounds(659, 80, 206, 25);
		panSuppliers.add(textSupplierContact);
				
		JLabel lblSuplierGsm = new JLabel("GSM");
		lblSuplierGsm.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblSuplierGsm.setBounds(483, 126, 166, 25);
		panSuppliers.add(lblSuplierGsm);
				
		textSupplierGsm = new JTextField();
		textSupplierGsm.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		textSupplierGsm.setColumns(10);
		textSupplierGsm.setBounds(659, 126, 206, 25);
		panSuppliers.add(textSupplierGsm);
				
		JLabel lblSuppliersList = new JLabel("Suppliers List");
		lblSuppliersList.setForeground(Color.BLACK);
		lblSuppliersList.setFont(new Font("Poppins ExtraLight", Font.BOLD, 20));
		lblSuppliersList.setBounds(43, 218, 146, 25);
		panSuppliers.add(lblSuppliersList);
		
		//Defines the column headers for the table in GUI so we can see interactively
		String[] supplierColumnNames = {"Supplier ID", "Supplier Name", "Supplier Address", "Supplier Contact Info", "Supplier GSM"};
		//Creates a DefaultTableModel with column headers and no initial data
		DefaultTableModel supplierModel = new DefaultTableModel(supplierColumnNames, 0);
		//Creates a JTable using the supplierModel to display data and headers
		JTable supplierTable = new JTable(supplierModel);

		//Wraps the JTable in a JScrollPane to allow scrolling when the content exceeds the visible area
		JScrollPane supplierScrollPane = new JScrollPane(supplierTable);
		supplierScrollPane.setBounds(43, 260, 1077, 167);  
		panSuppliers.add(supplierScrollPane); //so it appears in the GUI.

		
		loadSupplierList(supplierTable); //It is done so that the supplier list is automatically loaded into the table when the application is started.


//****** PRESS SUPPLIERS CLEAR BUTTON  *******		
		
		btnSupplierClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
											
				textSupplierId.setText(""); //in gui, set all fields to "" empty string.
				textSupplierName.setText("");
				textSupplierAddress.setText("");
				textSupplierContact.setText("");
				textSupplierGsm.setText("");
					
			}});

//****** PRESS SUPPLIERS ADD BUTTON  *******	
		btnSupplierAdd.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            SupplierDAO supplierDAO = new SupplierDAO(conn);
		            int supplierId = Integer.parseInt(textSupplierId.getText());
		            String name = textSupplierName.getText();
		            String address = textSupplierAddress.getText();
		            String contactInfo = textSupplierContact.getText();
		            String gsm = textSupplierGsm.getText();
		            supplierDAO.insertSupplier(supplierId, name, address, contactInfo, gsm);  //from supplierDAO class, it's insert method is called 
		            JOptionPane.showMessageDialog(null, "Supplier added successfully."); //success message 
		            loadSupplierList(supplierTable); //show supplier informations into the provided table after modification 
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error adding supplier: " + ex.getMessage()); //error
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid data."); //error
		        }
		    }
		});


		//****** PRESS SUPPLIERS UPDATE BUTTON  *******	
		btnSupplierUpdate.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            SupplierDAO supplierDAO = new SupplierDAO(conn);
		            int supplierId = Integer.parseInt(textSupplierId.getText());
		            String name = textSupplierName.getText();
		            String address = textSupplierAddress.getText();
		            String contactInfo = textSupplierContact.getText();
		            String gsm = textSupplierGsm.getText();
		            supplierDAO.updateSupplier(supplierId, name, address, contactInfo, gsm); //from supplierDAO class, it's update method is called 
		            JOptionPane.showMessageDialog(null, "Supplier updated successfully."); //success
		            loadSupplierList(supplierTable);  //show supplier informations into the provided table after modification 
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error updating supplier: " + ex.getMessage());
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid data.");
		        }
		    }
		});


		//****** PRESS SUPPLIERS DELETE BUTTON  *******	
		btnSupplierDelete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (Connection conn = Database.connectDatabase()) {
		            SupplierDAO supplierDAO = new SupplierDAO(conn);
		            int supplierId = Integer.parseInt(textSupplierId.getText());
		            supplierDAO.deleteSupplierById(supplierId); //from supplierDAO class, it's delete method is called 
		            JOptionPane.showMessageDialog(null, "Supplier deleted successfully."); //success
		            loadSupplierList(supplierTable);  //show supplier informations into the provided table after modification 
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error deleting supplier: " + ex.getMessage()); //error 
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid Supplier ID."); //error
		        }
		    }
		});


								
//***********************************************************************************************************		
		
			
		tabbedPane1.setFont( new Font( "Poppins ExtraLight", Font.BOLD|Font.ITALIC, 15 ) );
		tabbedPane1.setBackgroundAt(0, Color.red);
		tabbedPane1.setBackgroundAt(1,new Color(255, 255, 128));
		tabbedPane1.setBackgroundAt(2,new Color(218, 165, 32));		

		
		tabbedPane.setFont( new Font( "Poppins ExtraLight", Font.BOLD|Font.ITALIC, 15 ) );
		tabbedPane.setBackgroundAt(0, new Color(218, 165, 32));
		tabbedPane.setBackgroundAt(2, Color.red);

			
			
		
//******************************************************************************	
			
		JLabel lblNewLabel = new JLabel("RETAIL SYSTEM");
		lblNewLabel.setBounds(425, 10, 420, 51);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Poppins ExtraLight", Font.BOLD, 50));
	}
}
