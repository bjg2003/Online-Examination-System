package com.example.exam.ui;

import com.example.exam.dao.UserDAO;
import com.example.exam.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupPage extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> roleCombo;
    private JButton signupButton, cancelButton;

    public SignupPage() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(15);
        panel.add(confirmPasswordField, gbc);

        // Role
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        roleCombo = new JComboBox<>(new String[] {"candidate", "instructor", "admin"});
        panel.add(roleCombo, gbc);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout());
        signupButton = new JButton("Sign Up");
        cancelButton = new JButton("Cancel");
        signupButton.addActionListener(this);
        cancelButton.addActionListener(this);
        btnPanel.add(signupButton);
        btnPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(btnPanel, gbc);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String username = usernameField.getText().trim();
            String pwd = new String(passwordField.getPassword());
            String confirmPwd = new String(confirmPasswordField.getPassword());

            if (!pwd.equals(confirmPwd)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String role = (String) roleCombo.getSelectedItem();
            User user = new User(username, pwd, role);
            UserDAO userDAO = new UserDAO();
            if (userDAO.saveUser(user)) {
                JOptionPane.showMessageDialog(this, "Sign up successful! Please log in.");
                this.dispose(); // Close the sign-up window
            } else {
                JOptionPane.showMessageDialog(this, "Error during sign up. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            this.dispose();
        }
    }

    // Optional: main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignupPage().setVisible(true));
    }
}
