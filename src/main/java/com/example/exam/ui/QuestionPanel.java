package com.example.exam.ui;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {
    private String question;
    private String[] options;
    private int correctAnswerIndex;
    private ButtonGroup group;

    public QuestionPanel(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // Display the question text
        JLabel questionLabel = new JLabel("<html><h2>" + question + "</h2></html>");
        add(questionLabel, BorderLayout.NORTH);

        // Create radio buttons for each option
        JPanel optionsPanel = new JPanel(new GridLayout(options.length, 1, 5, 5));
        group = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            JRadioButton radioButton = new JRadioButton(options[i]);
            // Use the index as the action command
            radioButton.setActionCommand(String.valueOf(i));
            group.add(radioButton);
            optionsPanel.add(radioButton);
        }
        add(optionsPanel, BorderLayout.CENTER);
    }

    // Returns true if the candidate selected the correct option
    public boolean isAnswerCorrect() {
        ButtonModel selected = group.getSelection();
        if (selected == null) {
            return false; // No answer selected is treated as incorrect.
        }
        int selectedIndex = Integer.parseInt(selected.getActionCommand());
        return selectedIndex == correctAnswerIndex;
    }
}
