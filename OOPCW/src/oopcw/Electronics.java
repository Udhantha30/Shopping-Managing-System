package oopcw;

public class Electronics extends Product {

    private String brand;
    private int warranty;

    public Electronics(String productId, String productName, int numItems, double price, String brand, int warranty) {
        super(productId, productName, numItems, price, null);
        this.brand = brand;
        this.warranty = warranty;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }
    
}
