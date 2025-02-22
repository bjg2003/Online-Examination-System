package com.example.exam.ui;

import com.example.exam.dao.ResultDAO;
import com.example.exam.model.Result;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardFrame extends JFrame {

    public LeaderboardFrame() {
        setTitle("Leaderboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create header label
        JLabel headerLabel = new JLabel("Leaderboard", SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(headerLabel, BorderLayout.NORTH);

        // Create table model with column names
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Rank", "Username", "Score", "Time Taken (s)"}, 0);
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch results using ResultDAO
        ResultDAO resultDAO = new ResultDAO();
        List<Result> results = resultDAO.getAllResults();

        // Sort the results:
        // First, sort by score in descending order.
        // If scores are equal, sort by timeTaken in ascending order (faster finish is better).
        if (results != null) {
            Collections.sort(results, new Comparator<Result>() {
                @Override
                public int compare(Result r1, Result r2) {
                    int scoreCompare = Double.compare(r2.getScore(), r1.getScore());
                    if (scoreCompare == 0) {
                        return Integer.compare(r1.getTimeTaken(), r2.getTimeTaken());
                    }
                    return scoreCompare;
                }
            });

            // Populate the table model with sorted results.
            int rank = 1;
            for (Result r : results) {
                tableModel.addRow(new Object[]{
                    rank++,
                    r.getUser().getUsername(),
                    r.getScore(),
                    r.getTimeTaken()
                });
            }
        }
    }

    // For testing LeaderboardFrame independently
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LeaderboardFrame().setVisible(true));
    }
}
