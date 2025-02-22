package com.example.exam.ui;

import com.example.exam.dao.UserDAO;
import com.example.exam.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton;
    
    public LoginPage() {
        setTitle("Online Examination System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Center on screen

        // Create a panel with GridBagLayout for the form
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);

        // Password label and password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        // Create a button panel for actions
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        btnPanel.add(loginButton);
        btnPanel.add(cancelButton);
     // In your LoginPage constructor, after creating login and cancel buttons:
        JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(e -> new SignupPage().setVisible(true));
        // Assuming you are using a panel to hold the buttons, add this button to that panel:
        btnPanel.add(signupButton);
        
        // Add action listeners
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        // Add the panels to the frame
        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            UserDAO userDAO = new UserDAO();
            String uname = usernameField.getText().trim();
            String pwd = new String(passwordField.getPassword()).trim();
            User user = userDAO.authenticate(uname, pwd);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                // Open the Dashboard or next screen (e.g., new Dashboard(user).setVisible(true))
                new Dashboard(user).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        try {
            // Set a modern look and feel (e.g., Nimbus)
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
