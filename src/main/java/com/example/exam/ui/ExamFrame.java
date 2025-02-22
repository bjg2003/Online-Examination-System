package com.example.exam.ui;

import com.example.exam.dao.ResultDAO;
import com.example.exam.model.Exam;
import com.example.exam.model.Result;
import com.example.exam.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ExamFrame extends JFrame implements ActionListener {
	private JLabel timerLabel;
	private javax.swing.Timer swingTimer;
	private int timeRemaining;
	private LocalDateTime startTime;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JButton prevButton, nextButton, submitButton;
	private List<QuestionPanel> questionPanels;

	private User currentUser;
	private Exam currentExam;

	public ExamFrame(User currentUser, Exam currentExam) {
		this.currentUser = currentUser;
		this.currentExam = currentExam;
		setTitle("Online Exam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);

		startTime = LocalDateTime.now();
		timeRemaining = 10 * 60;
		timerLabel = new JLabel(formatTime(timeRemaining), SwingConstants.CENTER);
		timerLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		timerLabel.setForeground(Color.RED);
		add(timerLabel, BorderLayout.NORTH);

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		questionPanels = new ArrayList<>();
		initializeQuestions();
		add(cardPanel, BorderLayout.CENTER);

		JPanel navPanel = new JPanel(new FlowLayout());
		prevButton = new JButton("Previous");
		nextButton = new JButton("Next");
		submitButton = new JButton("Submit");
		prevButton.addActionListener(this);
		nextButton.addActionListener(this);
		submitButton.addActionListener(this);
		navPanel.add(prevButton);
		navPanel.add(nextButton);
		navPanel.add(submitButton);
		add(navPanel, BorderLayout.SOUTH);

		swingTimer = new javax.swing.Timer(1000, e -> updateTimer());
		swingTimer.start();
	}

	private void initializeQuestions() {
		questionPanels.clear();
		String[][] questions = {
				{ "1. What is Java?", "Programming Language", "Coffee", "Island", "All of the above", "0" },
				{ "2. Which company developed Java?", "Sun Microsystems", "Microsoft", "Apple", "Google", "0" },
				{ "3. What does JVM stand for?", "Java Virtual Machine", "Java Variable Machine", "Just Virtual Machine",
						"None of the above", "0" },
				{ "4. Which of the following is not a feature of Java?", "Object-Oriented", "Platform Independent",
						"Use of Pointers", "Multithreaded", "2" },
				{ "5. Which keyword is used to inherit a class in Java?", "implements", "extends", "inherits",
						"instanceof", "1" },
				{ "6. Which statement is used to handle exceptions in Java?", "try-catch", "throw-catch", "try-throw",
						"catch-throw", "0" },
				{ "7. Which collection class maintains insertion order?", "HashSet", "LinkedHashSet", "TreeSet",
						"ArrayList", "1" },
				{ "8. Which operator is used to compare two values?", "=", "==", "===", "<>", "1" },
				{ "9. Which statement is used to exit a loop in Java?", "exit", "return", "break", "stop", "2" },
				{ "10. Which of the following is not a valid access modifier in Java?", "public", "private", "protected",
						"friendly", "3" } };

		for (String[] q : questions) {
			QuestionPanel qp = new QuestionPanel(q[0], new String[] { q[1], q[2], q[3], q[4] }, Integer.parseInt(q[5]));
			questionPanels.add(qp);
			cardPanel.add(qp, "q" + (questionPanels.size()));
		}
	}

	private String formatTime(int totalSeconds) {
		int minutes = totalSeconds / 60;
		int seconds = totalSeconds % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	private void updateTimer() {
		timeRemaining--;
		timerLabel.setText(formatTime(timeRemaining));
		if (timeRemaining <= 0) {
			swingTimer.stop();
			JOptionPane.showMessageDialog(this, "Time is up! Exam will be submitted automatically.");
			submitExam();
		}
	}

	private double computeScore() {
		int total = questionPanels.size();
		int correct = 0;
		for (QuestionPanel qp : questionPanels) {
			if (qp.isAnswerCorrect()) {
				correct++;
			}
		}
		System.out.println("Correct answers: " + correct + " out of " + total);
		return ((double) correct / total) * 100;
	}

	private void submitExam() {
		double score = computeScore();
		int timeTaken = (int) Duration.between(startTime, LocalDateTime.now()).getSeconds();
		Result result = new Result(currentUser, currentExam, score, LocalDateTime.now(), timeTaken);
		ResultDAO resultDAO = new ResultDAO();
		resultDAO.saveResult(result);
		JOptionPane.showMessageDialog(this,
				"Exam submitted successfully!\nYour score: " + score + "\nTime taken: " + timeTaken + " seconds");
		new LeaderboardFrame().setVisible(true);
		dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == prevButton) {
			cardLayout.previous(cardPanel);
		} else if (e.getSource() == nextButton) {
			cardLayout.next(cardPanel);
		} else if (e.getSource() == submitButton) {
			swingTimer.stop();
			submitExam();
		}
	}
}