package com.example.exam.dao;

import com.example.exam.model.Result;
import com.example.exam.util.HibernateUtil;

import java.util.Collections;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ResultDAO {

	public void saveResult(Result result) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Begin transaction
			transaction = session.beginTransaction();
			// Save the result object
			session.save(result);
			// Commit the transaction to persist changes
			transaction.commit();
		} catch (Exception e) {
			// If there is an error, roll back the transaction
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Result> getAllResults() {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        // Use join fetch to eagerly load associated user and exam objects.
	        Query<Result> query = session.createQuery(
	            "select distinct r from Result r join fetch r.user join fetch r.exam", 
	            Result.class
	        );
	        return query.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}

}
