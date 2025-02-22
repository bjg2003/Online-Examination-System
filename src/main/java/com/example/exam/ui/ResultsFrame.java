package com.example.exam.ui;

import com.example.exam.dao.ResultDAO;
import com.example.exam.model.Result;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ResultsFrame extends JFrame {
    
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    
    public ResultsFrame() {
        setTitle("Exam Results");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        initComponents();
        loadResults();
    }
    
    // Step 2a: Initialize the UI components
    private void initComponents() {
        // Create a main panel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        
        // North panel: Header label
        JLabel headerLabel = new JLabel("Exam Results", SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(headerLabel, BorderLayout.NORTH);
        
        // Center panel: Table to display results
        String[] columnNames = {"Result ID", "User ID", "Exam ID", "Score", "Taken On"};
        tableModel = new DefaultTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel);
        resultsTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // South panel: Refresh button to reload results
        JButton refreshButton = new JButton("Refresh Results");
        refreshButton.addActionListener(e -> loadResults());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(panel);
    }
    
    // Step 2b: Load results from the database and populate the table
    private void loadResults() {
        // Clear the table first
        tableModel.setRowCount(0);
        
        ResultDAO resultDAO = new ResultDAO();
        List<Result> results = resultDAO.getAllResults();
        
        if (results != null && !results.isEmpty()) {
            for (Result result : results) {
                Object[] rowData = new Object[] {
                    result.getId(),
                    result.getUser().getId(),    // Assuming User has getId()
                    result.getExam().getId(),    // Assuming Exam has getId()
                    result.getScore(),
                    result.getTakenOn().toString()
                };
                tableModel.addRow(rowData);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No results found.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Step 2c: Main method for testing the ResultsFrame independently
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResultsFrame().setVisible(true));
    }
}
