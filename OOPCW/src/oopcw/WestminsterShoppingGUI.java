package oopcw;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class WestminsterShoppingGUI extends JFrame {

    private final ArrayList<Product> productList = new ArrayList<>();
    private boolean newUser;

    public WestminsterShoppingGUI(boolean newUser) {
        this.newUser = newUser;
        
        // Details panel to display selected product information
        JPanel detailsPannel = new JPanel();
        detailsPannel.setBounds(-5, 300, 1000, 300);
        detailsPannel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        detailsPannel.setLayout(null);
        add(detailsPannel);
        
        // Labels to display selected product details
        JLabel selectProduct = new JLabel("Selected Product - Details");
        selectProduct.setBounds(50, 20, 200, 20);
        selectProduct.setFont(new Font("arial", Font.BOLD, 14));
        detailsPannel.add(selectProduct);

        JLabel Id = new JLabel("Product Id :");
        Id.setBounds(50, 70, 200, 20);
        detailsPannel.add(Id);

//        setlabel 1
        JLabel fillId = new JLabel("");
        fillId.setBounds(130, 70, 200, 20);
        detailsPannel.add(fillId);

        JLabel cat = new JLabel("Category :");
        cat.setBounds(50, 100, 200, 20);
        detailsPannel.add(cat);

//        setlabel 2
        JLabel fillCategory = new JLabel("");
        fillCategory.setBounds(120, 100, 200, 20);
        detailsPannel.add(fillCategory);

        JLabel name = new JLabel("Name :");
        name.setBounds(50, 130, 200, 20);
        detailsPannel.add(name);

//        setlabel 3
        JLabel fillName = new JLabel("");
        fillName.setBounds(100, 130, 200, 20);
        detailsPannel.add(fillName);

        JLabel size = new JLabel("Size :");
        size.setBounds(50, 160, 200, 20);
        detailsPannel.add(size);

        JLabel fillBrand = new JLabel("");
        fillBrand.setBounds(95, 160, 200, 20);
        detailsPannel.add(fillBrand);

        JLabel fillWarranty = new JLabel("");
        fillWarranty.setBounds(110, 190, 200, 20);
        detailsPannel.add(fillWarranty);

//        setlabel 4
        JLabel fillSize = new JLabel("");
        fillSize.setBounds(95, 160, 200, 20);
        detailsPannel.add(fillSize);

        JLabel colour = new JLabel("Colour :");
        colour.setBounds(50, 190, 200, 20);
        detailsPannel.add(colour);

//        setlabel 5
        JLabel fillColour = new JLabel("");
        fillColour.setBounds(110, 190, 200, 20);
        detailsPannel.add(fillColour);

        JLabel availableItems = new JLabel("Items Available :");
        availableItems.setBounds(50, 220, 200, 20);
        detailsPannel.add(availableItems);

//        setlabel 6
        JLabel fillItems = new JLabel("");
        fillItems.setBounds(150, 220, 200, 20);
        detailsPannel.add(fillItems);

        JLabel productCategory = new JLabel("Select Product Category");
        productCategory.setBounds(50, 45, 150, 20);
        add(productCategory);
        
        // Button to add the selected product to the shopping cart
        JButton cart1 = new JButton("Add to Shopping Cart");
        cart1.setBounds(400, 250, 200, 35);
        cart1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        detailsPannel.add(cart1);

        String[][] data = loadFromFile();
        
        // JTable and JScrollPane to display product information
        String col[] = {"Product Id", "Name", "Category", "Price (£)", "Info"};
        DefaultTableModel model = new DefaultTableModel(data, col); // Create DefaultTableModel
        JTable table = new JTable(model); // Use DefaultTableModel when creating JTable
        JScrollPane Scroll = new JScrollPane(table);
        Scroll.setBounds(50, 120, 900, 150);
        
        // JComboBox for selecting product category
        JComboBox cb = new JComboBox();
        cb.setBounds(250, 45, 200, 20);
        add(cb);
        
         //Adding items to the JComboBox
        cb.addItem("All");
        cb.addItem("Electronics");
        cb.addItem("Clothing");

        WestminsterShoppingGUI2 cartGUI = new WestminsterShoppingGUI2(newUser);
        
         // Action listener for categoryComboBox
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
            
            // Update the table based on the selected category
            private void updateTable() {
                String selectedCategory = (String) cb.getSelectedItem();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                for (Product product : productList) {
                    if (selectedCategory.equals("All")
                            || (selectedCategory.equals("Electronics") && product instanceof Electronics)
                            || (selectedCategory.equals("Clothing") && product instanceof Clothing)) {

                        model.addRow(new Object[]{
                                product.getProductId(),
                                product.getProductName(),
                                (product instanceof Electronics) ? "Electronics" : "Clothing",
                                product.getPrice(),
                                (product instanceof Electronics)
                                        ? ((Electronics) product).getBrand() + "," + ((Electronics) product).getWarranty() + " months"
                                        : ((Clothing) product).getSize() + "," + ((Clothing) product).getColor()
                        });
                    }
                }
            }
        });
        
        // ActionListener for addToCartButton to add selected product to the shopping cart
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    // Add the selected product to the shopping cart and update details
                    if (selectedRow != -1) {
                        Product selectedProduct = productList.get(selectedRow);
                        String productId = selectedProduct.getProductId();
                        String productName = selectedProduct.getProductName();
                        String category = (selectedProduct instanceof Electronics) ? "Electronics" : "Clothing";
                        String details = (String) table.getValueAt(selectedRow, 4);

                        fillId.setText("<html><font style='font-weight: normal;'>" + productId + "</font></html>");
                        fillCategory.setText("<html><font style='font-weight: normal;'>" + category + "</font></html>");
                        fillName.setText("<html><font style='font-weight: normal;'>" + productName + "</font></html>");

                        String[] detailsArray = details.split(",");
                        if (selectedProduct instanceof Electronics) {
                            size.setText("<html>Brand: <font style='font-weight: normal;'>" + detailsArray[0] + "</font></html>");
                            colour.setText("<html>Warranty: <font style='font-weight: normal;'>" + detailsArray[1] + "</font></html>");
                        } else {
                            size.setText("<html>Size: <font style='font-weight: normal;'>" + detailsArray[0] + "</font></html>");
                            colour.setText("<html>Color: <font style='font-weight: normal;'>" + detailsArray[1] + "</font></html>");
                        }

                        fillItems.setText("<html><font style='font-weight: normal;'>" + String.valueOf(selectedProduct.getNumItems()) + "</font></html>");
                    }
                }
            }
        });
        
        // Button to go to the shopping cart
        cart1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Product selectedProduct = productList.get(selectedRow);
                    if (selectedProduct.getNumItems() > 0) {
                        selectedProduct.setNumItems(selectedProduct.getNumItems() - 1);
                        cartGUI.add(selectedProduct);
                        cartGUI.updateTable();
                        fillItems.setText("<html><font style='font-weight: normal;'>" + String.valueOf(selectedProduct.getNumItems()) + "</font></html>");

                    } else {
                        JOptionPane.showMessageDialog(WestminsterShoppingGUI.this, "No more items available for this product.", "Out of Stock", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        JButton cart = new JButton("Shopping Cart");
        cart.setBounds(925 - 150, 30, 150, 30);
        cart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(cart);
        
        // ActionListener for cartButton to show the shopping cart
        cart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartGUI.setVisible(true);
                cartGUI.updateTable();
                dispose();
            }
        });
        
         //JPanel to hold the JTable
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        add(panel1);

        panel1.add(Scroll);

        setVisible(true);
        setTitle("JTable Example");
        setSize(1000, 630);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Method to load product data from file
    public String[][] loadFromFile() {

        try (Scanner fileScanner = new Scanner(new File("productsData.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] productData = line.split(",");

                if (productData.length >= 5) {
                    String productId = productData[0];
                    String productName = productData[1];
                    int numItems = Integer.parseInt(productData[2]);
                    double price = Double.parseDouble(productData[3]);

                    if (productId.startsWith("E")) {
                        String brand = productData[4];
                        int warranty = Integer.parseInt(productData[5]);
                        Electronics electronics = new Electronics(productId, productName, numItems, price, brand, warranty);
                        productList.add(electronics);
                    } else if (productId.startsWith("C")) {
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
            Collections.sort(productList, (p1, p2) -> p1.getProductId().compareTo(p2.getProductId()));
            String[][] data = new String[productList.size()][5];

            for (int i = 0; i < productList.size(); i++) {
                Product product = productList.get(i);
                data[i][0] = product.getProductId();
                data[i][1] = product.getProductName();
                data[i][2] = (product instanceof Electronics) ? "Electronics" : "Clothing";
                data[i][3] = String.valueOf(product.getPrice());

                if (product instanceof Electronics electronics) {

                    data[i][4] = electronics.getBrand() + "," + electronics.getWarranty() + " months";
                } else if (product instanceof Clothing clothing) {

                    data[i][4] = clothing.getSize() + "," + clothing.getColor();
                }
            }

            System.out.println("Restore Products data successfully!");
            return data;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return new String[0][0];
        } catch (Exception e) {
            System.err.println("Error loading product data from file: " + e.getMessage());
            return new String[0][0];
        }
    }

}
