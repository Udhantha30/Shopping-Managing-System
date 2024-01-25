package oopcw;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class User extends JFrame {

    private String userName;
    private JButton login;
    private JTextField textField;
    private boolean newUser = false;

    public User(String userName, String password) {
        this.userName = userName;
    }

    User() {
        setLayout(null);
        
        // JLabel for the login form title
        JLabel form = new JLabel("Login");
        form.setBounds(50, 20, 200, 35);
        form.setFont(new Font("arial", Font.BOLD, 28));
        add(form);
        
        JLabel usernameLabel = new JLabel("Enter User Name");
        usernameLabel.setBounds(50, 90, 200, 20);
        add(usernameLabel);
        
        // JTextField for entering the username
        textField = new JTextField();
        textField.setBounds(50, 120, 230, 30);
        add(textField);
        
        // JButton for initiating the login process
        login = new JButton("Login");
        login.setBounds(50, 200, 120, 30);
        login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(login);
        
         // JButton for creating a new user
        JButton createNewUser = new JButton("Create New User");
        createNewUser.setBounds(180, 200, 150, 30);
        createNewUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(createNewUser);

        setVisible(true);
        setTitle("Login");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Action listener for the login button
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        
        // Action listener for the create new user button
        createNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewUser();
            }
        });
    }
    
    //Method to user login 
    private void loginUser() {
        String enteredUsername = textField.getText().trim();
        if (isUserInFile(enteredUsername)) {
            WestminsterShoppingGUI cart = new WestminsterShoppingGUI(newUser);
            dispose();
        } else {
            System.out.println("Username not found. Please create a new user.");
        }
    }
    
    //Method to check the user name is available in the file
    private boolean isUserInFile(String enteredUsername) {
        try (BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equalsIgnoreCase("Username: " + enteredUsername)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //Method to create a new user
    private void createNewUser() {
        String enteredUsername = textField.getText().trim();
        if (!enteredUsername.isEmpty()) {
            if (!isUserInFile(enteredUsername)) {
                JTextField additionalField = new JTextField();
                Object[] message = {
                    "Username:", textField,};

                int option = JOptionPane.showConfirmDialog(null, message, "Create New User",
                        JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    String additionalInfo = additionalField.getText().trim();
                    saveToFile(enteredUsername, additionalInfo);
                    newUser = true;
                    WestminsterShoppingGUI cart = new WestminsterShoppingGUI(newUser);
                    dispose();
                }
            } else {
                System.out.println("Username already exists. Please choose a different username.");
            }
        } else {
            System.out.println("Please enter a valid username.");
        }
    }
    
    //Method to save the user name in file
    private void saveToFile(String enteredUsername, String additionalInfo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userdata.txt", true))) {
            writer.write("Username: " + enteredUsername);
            writer.newLine();
            if (!additionalInfo.isEmpty()) {
                writer.write("AdditionalInfo: " + additionalInfo);
                writer.newLine();
            }
            System.out.println("User data saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
