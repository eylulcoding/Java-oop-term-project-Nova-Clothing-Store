
//Purchase class
public class Purchase {
    private int purchaseId;
    private int productId;
    private int supplierId;
    private int quantity;
    private double price;  
    private String purchaseDate;

    // Constructor
    public Purchase(int purchaseId, int productId, int supplierId, int quantity, double price, String purchaseDate) {
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    // Getters
    public int getPurchaseId() {
        return purchaseId;
    }

    public int getProductId() {
        return productId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }
}
