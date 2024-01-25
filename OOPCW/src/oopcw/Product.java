package oopcw;

public abstract class  Product{
    
    private String productId;
    private String productName;
    private int numItems;
    private double price;
    
    public void decrementNumItems() {
        if (numItems > 0) {
            numItems--;
        }
    }

    

    public Product(String productId, String productName, int numItems, double price, Object par4) {
        this.productId = productId;
        this.productName = productName;
        this.numItems = numItems;
        this.price = price;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getNumItems() {
        return numItems;
    }

    public double getPrice() {
        return price;
    }
    
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", numItems=" + numItems + ", price=" + price + '}';
    }

    void add(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
