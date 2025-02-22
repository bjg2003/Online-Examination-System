package com.example.exam.dao;

import com.example.exam.model.Exam;
import com.example.exam.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ExamDAO {
    public Exam saveExam(Exam exam) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(exam); // This will assign an ID to exam
            transaction.commit();
            return exam; // Return the now persistent exam (with a generated ID)
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
}
