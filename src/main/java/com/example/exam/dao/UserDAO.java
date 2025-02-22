package com.example.exam.dao;

import com.example.exam.model.User;
import com.example.exam.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAO {

    // Method for authenticating a user by username and password
    public User authenticate(String username, String password) {
        User user = null;
        // Open a new session using the SessionFactory
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Create HQL (Hibernate Query Language) query to find user by credentials
            Query<User> query = session.createQuery("FROM User WHERE username = :uname AND password = :pwd", User.class);
            query.setParameter("uname", username);
            query.setParameter("pwd", password);
            // Execute query; uniqueResult() returns a single instance or null
            user = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    // Additional DAO methods (e.g., save, update, delete) can be added here
    public boolean saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
