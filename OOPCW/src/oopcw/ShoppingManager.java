package oopcw;

import java.util.ArrayList;
import java.util.Scanner;

public interface ShoppingManager {
    
    void addProducts(Scanner scanner, ArrayList<Product> productList);

    Electronics AddElectronicsProduct(Scanner scan);

    Clothing AddClothingProduct(Scanner scan);

    void printProducts(ArrayList<Product> productList);

    void deleteProducts(Scanner scanner, ArrayList<Product> productList);

    void savetofile();

    void loadFromFile();
}
