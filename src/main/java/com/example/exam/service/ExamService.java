package com.example.exam.service;

import com.example.exam.dao.UserDAO;
import com.example.exam.model.User;

public class ExamService {
    private UserDAO userDAO;

    public ExamService() {
        // Instantiate your DAO(s)
        userDAO = new UserDAO();
    }
    
    // Business method to authenticate user
    public User login(String username, String password) {
        // Additional business logic can be added here, such as logging attempts, etc.
        return userDAO.authenticate(username, password);
    }
    
    // Other business methods (e.g., getExamById, submitExam, calculateScore) would be added similarly.
}
