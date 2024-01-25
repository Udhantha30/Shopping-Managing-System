package oopcw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    
    private static final Scanner scan = new Scanner(System.in);

    // List to store products
    private final ArrayList<Product> productList = new ArrayList<>();

    
    public WestminsterShoppingManager() {
        // Display the main menu for the shopping system
        System.out.println("**Welcome to Westminster Shopping Menu**");
        System.out.println("\tPlease Select your option");
        System.out.println(" ");
        System.out.println("\t01: Customers");
        System.out.println("\t02: Manager");
        System.out.println("\t99: Exit the Program");
        System.out.println();

        // select user for option
        System.out.print("Enter Your Option :");
        String number = scan.nextLine();

        // Check user's selected option
        if (number.equals("99")) {
            System.out.println("Your Program is End........");
            System.exit(0);
        }

        switch (number) {
            case "01", "1":
                // If customer option is selected, load the GUI
                User gui = new User();
                break;
            case "02", "2":
                // If manager option is selected, load the manager menu
                managerPart();
                break;
            default:
                System.out.println("Invalid number..");
        }
    }

    // Manager's menu
    public void managerPart() {
        
        // Load existing products from file
        loadFromFile();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a Product");
            System.out.println("2. Print Products");
            System.out.println("3. Delete Products");
            System.out.println("4. Save to file");
            System.out.println("5. Exit");

            
            System.out.print("Enter your choice: ");
            String choice = scan.nextLine();

            
            switch (choice) {
                case "1":
                    // Add a product
                    addProducts(scan, productList);
                    break;
                case "2":
                    // Print list of products
                    printProducts(productList);
                    break;
                case "3":
                    // Delete a product
                    deleteProducts(scan, productList);
                    break;
                case "4":
                    // Save products to file
                    savetofile();
                    break;
                case "5":
                    // Exit the program
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    // Implementation of addProducts method
    @Override
    public void addProducts(Scanner scanner, ArrayList<Product> productList) {
        // Check if the maximum limit of 50 products is reached
        if (productList.size() >= 50) {
            System.out.println("Cannot add more products. Maximum limit 50 reached.");
        } else {
            
            System.out.println("Select product category:");
            System.out.println("1. Electronics");
            System.out.println("2. Clothes");
            System.out.print("Enter your choice: ");
            int categoryChoice = scanner.nextInt();
            scanner.nextLine();

            // Check category choice and add the corresponding product
            switch (categoryChoice) {
                case 1:
                    Electronics electronics = this.AddElectronicsProduct(scan);
                    if (electronics != null) {
                        this.productList.add(electronics);
                        System.out.println("Electronics product added successfully.");
                    }
                    break;
                case 2:
                    Clothing clothing = this.AddClothingProduct(scanner);
                    if (clothing != null) {
                        this.productList.add(clothing);
                        System.out.println("Clothing product added successfully.");
                    }
                    break;
                default:
                    System.out.println("Invalid category choice.");
                    break;
            }
        }
    }

    //AddElectronicsProduct method
    @Override
    public Electronics AddElectronicsProduct(Scanner scan) {
        System.out.print("Enter product ID: ");
        String productId = scan.nextLine();
        System.out.print("Enter product name: ");
        String productName = scan.nextLine();
        System.out.print("Enter number of items: ");
        int numItems = scan.nextInt();
        System.out.print("Enter price: ");
        double price = scan.nextDouble();
        scan.nextLine();
        System.out.print("Enter brand: ");
        String brand = scan.nextLine();
        System.out.print("Enter warranty period: ");
        int warranty = scan.nextInt();
        return new Electronics(productId, productName, numItems, price, brand, warranty);
    }

    //AddClothingProduct method
    @Override
    public Clothing AddClothingProduct(Scanner scan) {
        System.out.print("Enter product ID: ");
        String productId = scan.nextLine();
        System.out.print("Enter product name: ");
        String productName = scan.nextLine();
        System.out.print("Enter number of items: ");
        int numItems = scan.nextInt();
        System.out.print("Enter price: ");
        double price = scan.nextDouble();
        scan.nextLine();
        System.out.print("Enter size: ");
        String size = scan.nextLine();
        System.out.print("Enter color: ");
        String color = scan.nextLine();
        return new Clothing(productId, productName, numItems, price, size, color);
    }

    //printProducts method
    @Override
    public void printProducts(ArrayList<Product> productList) {
        // Sort products by ID
        productList.sort(Comparator.comparing(Product::getProductId));

        // Check if there are products to display
        if (productList.isEmpty()) {
            System.out.println("No products available.");
        } else {
            // Display details for each product
            for (Product product : productList) {
                if (product instanceof Electronics) {
                    // Display details for Electronics product
                    Electronics electronics = (Electronics) product;
                    System.out.println("Electronic ProductID = "+electronics.getProductId() + ", "
                            + "Product Name = "+electronics.getProductName() + ", "
                            + "No Of Product = "+electronics.getNumItems() + ", "
                            + "Product Price = "+electronics.getPrice() + ", "
                            + "Product Brand = "+electronics.getBrand() + ", "
                            + "Product Warranty (months) = "+electronics.getWarranty());
                } else if (product instanceof Clothing) {
                    // Display details for Clothing product
                    Clothing clothing = (Clothing) product;
                    System.out.println("Clothing ProductID = "+clothing.getProductId() + ", "
                            + "Product Name = "+clothing.getProductName() + ", "
                            + "No Of Product = "+clothing.getNumItems() + ", "
                            + "Product Price = "+clothing.getPrice() + ", "
                            + "Product Size = "+clothing.getSize() + ", "
                            + "Product Color = "+clothing.getColor());
                }
            }
        }
    }

    //deleteProducts method
    @Override
    public void deleteProducts(Scanner scanner, ArrayList<Product> productList) {
        // Prompt user for the Product ID to delete
        System.out.print("Enter the Product ID to delete: ");
        String productIdToDelete = scanner.nextLine();

        // Find the index of the product with the specified ID
        int indexToDelete = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId().equals(productIdToDelete)) {
                indexToDelete = i;
                break;
            }
        }

        // Check if the product was found and delete it
        if (indexToDelete != -1) {
            productList.remove(indexToDelete);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product with ID " + productIdToDelete + " not found.");
        }
    }

    //savetofile method
    @Override
    public void savetofile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("productsData.txt"))) {
            // Write product data to the file
            for (Product product : productList) {
                if (product instanceof Electronics) {
                    // Write data for Electronics product
                    Electronics electronics = (Electronics) product;
                    writer.println(electronics.getProductId() + ","
                            + electronics.getProductName() + ","
                            + electronics.getNumItems() + ","
                            + electronics.getPrice() + ","
                            + electronics.getBrand() + ","
                            + electronics.getWarranty());
                } else if (product instanceof Clothing) {
                    // Write data for Clothing product
                    Clothing clothing = (Clothing) product;
                    writer.println(clothing.getProductId() + ","
                            + clothing.getProductName() + ","
                            + clothing.getNumItems() + ","
                            + clothing.getPrice() + ","
                            + clothing.getSize() + ","
                            + clothing.getColor());
                }
            }
            System.out.println("Product data saved to file successfully!");
        } catch (IOException e) {
            // Handle file I/O exception
            System.err.println("Error saving product data to file: " + e.getMessage());
        }
    }

    //loadFromFile method
    @Override
    public void loadFromFile() {
        try (Scanner fileScanner = new Scanner(new File("productsData.txt"))) {
            // Read product data from the file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] productData = line.split(",");

                if (productData.length >= 5) {
                    // Extract data and create product instances
                    String productId = productData[0];
                    String productName = productData[1];
                    int numItems = Integer.parseInt(productData[2]);
                    double price = Double.parseDouble(productData[3]);

                    if (productId.startsWith("E")) {
                        // Create and add Electronics product
                        String brand = productData[4];
                        int warranty = Integer.parseInt(productData[5]);
                        Electronics electronics = new Electronics(productId, productName, numItems, price, brand, warranty);
                        productList.add(electronics);
                    } else if (productId.startsWith("C")) {
                        // Create and add Clothing product
                        String size = productData[4];
                        Clothing clothing = new Clothing(productId, productName, numItems, price, size, productData[5]);
                        productList.add(clothing);
                    } else {
                        System.out.println("Invalid product data in the file.");
                    }
                } else {
                    System.out.println("Invalid product data in the file.");
                }
            }
            System.out.println("Restore Products data successfully!");
        } catch (FileNotFoundException e) {
            // Handle file not found exception
        } catch (Exception e) {
            // Handle other exceptions
        }
    }
    
    public static void main(String[] args) {
        WestminsterShoppingManager managerpart = new WestminsterShoppingManager();
    }
}
