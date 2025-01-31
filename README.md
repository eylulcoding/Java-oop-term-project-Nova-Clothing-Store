1.	Project Overview
   
1.1.	Objective
Our project is to create an Inventory Management System for a clothing store. The system will help manage inventory, sales, and supplier information easily. We plan to create a user-friendly interface by using object-oriented programming to make inventory tracking more efficient.

1.2.	Store Type
We chose a clothing store because it has a variety of items like sizes, colors and seasonal products, making it ideal for implementing features like category-based management and data organization.


2.	Design and Architecture

The database schema is designed to manage the operations of a clothing store efficiently by organizing data into seven main tables: Products, Suppliers, Purchases, Inventory, Sales, Customers, and Staff. The schema follows a relational model, ensuring data consistency and integrity. Below is a detailed description of the tables and their relationships:

2.1.	Class Diagram

Below is the class diagram for the clothing store management system:

Product
Attributes: productID, productName, category, size, color, price, isSeasonalDiscount
Methods: calculateDiscount(), updatePrice()
Interface: IProduct

Supplier
Attributes: supplierID, supplierName, contactInfo, address
Methods: addSupplier(), updateSupplier()
Interface: ISupplier

Purchase
Attributes: purchaseID, productID, supplierID, quantityPurchased, purchaseDate, totalCost
Methods: recordPurchase(), calculateTotalCost()
Interface: IPurchase

Inventory
Attributes: productID, currentStock
Methods: updateStock(), checkStockLevel()
Interface: IInventory

Sale
Attributes: saleID, productID, customerID, staffID, quantitySold, saleDate, totalAmount
Methods: recordSale(), calculateTotalAmount()
Interface: ISale

Customer
Attributes: customerID, customerName, address
Methods: addCustomer(), updateCustomerInfo()
Interface: ICustomer


Staff
Attributes: staffID, staffName, role, address
Methods: addStaff(), updateStaffRole()
Interface: IStaff

1. Products Table
Purpose: Stores information about the clothing products available in the store.
Attributes:
ProductID (Primary Key)
ProductName
Category
Size
Color
Price
IsSeasonalDiscount: A boolean indicating if the product has a seasonal discount.
SupplierID (Foreign Key)

2. Suppliers Table
Purpose: Stores information about suppliers who provide products to the store.
Attributes:
SupplierID (Primary Key)
SupplierName
ContactInfo
Address

3. Purchases Table
Purpose: Tracks the purchases made by the store from suppliers.
Attributes:
PurchaseID (Primary Key)
ProductID (Foreign Key)
SupplierID (Foreign Key)
QuantityPurchased
PurchaseDate
TotalCost

4. Inventory Table
Purpose: Maintains the current stock levels of products in the store.
Attributes:
ProductID (Primary Key)
CurrentStock

5. Sales Table
Purpose: Records sales transactions made with customers.
Attributes:
SaleID (Primary Key)
ProductID (Foreign Key)
CustomerID (Foreign Key)
StaffID (Foreign Key)
QuantitySold
SaleDate
TotalAmount

6. Customers Table
Purpose: Stores information about customers.
Attributes:
CustomerID (Primary Key)
CustomerName
Address

7. Staff Table
Purpose: Stores information about the store's staff.
Attributes:
StaffID (Primary Key)
StaffName
Role
Address

Relationships
Products ↔ Suppliers:
Each product is supplied by one supplier, but a supplier can provide multiple products (One-to-Many relationship).
Purchases ↔ Products, Suppliers:
Each purchase references one product and one supplier (Many-to-One relationship).
Inventory ↔ Products:
Each product has a corresponding inventory entry (One-to-One relationship).
Sales ↔ Products, Customers, Staff: 
Each sale involves one product, one customer, and one staff member (Many-to-One relationship). 








 



2.2.	Database Schema

 

 
3.	Features and Functionality
3.1.	Key Features
The main features we used in the application are:

1.	Adding, Updating, and Deleting Inventory Items:
We can add new products to the inventory, update existing product details (like price, quantity, category), and delete items that are no longer available. Users can do these operations via buttons and input fields on the “Product Management” screen.

2.	Importing and Exporting Inventory Data:
Our application allows to import product or inventory data from CSV files. This helps in bulk uploading information and saves time.

3.	Managing Supplier Information:
A dedicated section for suppliers allows us to add/edit supplier details like name, contact info, and address.

4.	Tracking Sales Data:
The sales section includes features to enter transactions, calculate total amounts, and store customer and product details for each sale. It also allows us to export the sales data.

5.	Error Handling and Validation:
Our design includes features to handle errors, such as preventing empty fields and detecting corrupted files during importation.

3.2.	Customization
We made the system for a clothing store by adding specific product attributes like size, color, and category. The user interface was set for easy navigation, and the export/import features were updated to manage these attributes. These changes make the system more efficient and user-friendly for managing clothing inventory.


4.	Object-Oriented Principles

Our project uses classes like Product, Customer, Sales, and Supplier to organize related data and methods. For example, the Product class stores details like productName and price, and objects of this class represent individual products.



5.	File I/O
5.1.	Import/Export Functionality
We use CSV files for importing and exporting data. For export, the data from tables like Product or Customer is written into a CSV file. For import, the application reads data from a CSV file and adds it to the database.




5.2.	Error Handling
Our checks for errors like missing fields, incorrect formats, or corrupted files. If there’s an issue, it shows an error message without crashing the system.



6.	Challenges and Solutions
We faced challenges like debugging code errors, integrating the database with the GUI, and making the import/export buttons work. We solved these by collaborating, researching solutions online, going through our class notes and trying different codes to fix these errors.


7.	Future Improvements
More advanced features like real-time inventory tracking, detailed sales analytics can be added. We can also improve the project by adding payment options.


8.	Conclusion
Working on this project helped us improve our teamwork, problem-solving, and coding skills. We gained experience in applying OOP principles and learned how to integrate databases with a GUI.


Appendix
A.	User Manual
1.	Launching the Program: When users launch the program, the first window will show tabbed panels like "Products," "Staff," "Customers," "Suppliers" and Export &Import buttons.
2.	  Using the Export Button: When users click the Export button, the data will be exported in the current tab with the name “[panel_name].csv”
Using the Import Button: When users click the Import button, it allows to import data from an external file into the system. The required file format is .csv.
3.	Navigating Between Tabs: Users can switch between tabs by clicking. The tabs we have on our app are:
•	Products: Information related to products.
•	Staff: Information related to employees.
•	Customers: Customer information.
•	Suppliers: Supplier information.
We also have tabs to show Sales and Purchases informations.

B.	References
•	Class notes
•	Youtube
•	W3Schools

