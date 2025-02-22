package com.example.exam.ui;

import com.example.exam.dao.ExamDAO;  // Ensure this import exists
import com.example.exam.model.Exam;
import com.example.exam.model.User;
import java.time.LocalDateTime;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private User user;
    
    public Dashboard(User user) {
        this.user = user;
        setTitle("Exam Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // North panel: Welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.NORTH);
        
        // Center panel: List of exams (placeholder)
        DefaultListModel<String> examListModel = new DefaultListModel<>();
        // In a full implementation, load exam titles from the database
        examListModel.addElement("Exam 1: Java Fundamentals");
//        examListModel.addElement("Exam 2: Advanced Java");
        JList<String> examList = new JList<>(examListModel);
        examList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(examList), BorderLayout.CENTER);
        
        // South panel: Start Exam button
        JButton startExamButton = new JButton("Start Exam");
        startExamButton.addActionListener(e -> {
            // Instead of directly creating a new exam, first persist it.
            ExamDAO examDAO = new ExamDAO();
            // Create a new Exam object (using dummy values here)
            Exam exam = new Exam("Exam 1: Java Fundamentals", 10, LocalDateTime.now());
            // Save the exam to the database to make it persistent
            Exam savedExam = examDAO.saveExam(exam);
            if(savedExam != null) {
                new ExamFrame(user, savedExam).setVisible(true);  // Use 'user' instead of currentUser
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error saving exam. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JPanel southPanel = new JPanel();
        southPanel.add(startExamButton);
        add(southPanel, BorderLayout.SOUTH);
    }
}
