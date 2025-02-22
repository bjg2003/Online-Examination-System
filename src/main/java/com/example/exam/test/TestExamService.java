package com.example.exam.test;

import com.example.exam.model.User;
import com.example.exam.service.ExamService;

public class TestExamService {
    public static void main(String[] args) {
        ExamService service = new ExamService();
        User user = service.login("user1", "Pass@321");
        if (user != null) {
            System.out.println("Login Successful! Welcome " + user.getUsername());
        } else {
            System.out.println("Login Failed!");
        }
    }
}
