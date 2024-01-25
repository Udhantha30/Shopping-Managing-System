package oopcw;

public class Clothing extends Product {

    private String size;
    private String color;

    public Clothing(String productId, String productName, int numItems, double price, String size, String color) {
        super(productId, productName, numItems, price, null);
        this.size = size;
        this.color = color;
    }


    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

}
