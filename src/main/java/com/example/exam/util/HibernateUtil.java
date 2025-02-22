package com.example.exam.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    // Create a SessionFactory using the configuration file (hibernate.cfg.xml)
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Loads configuration from hibernate.cfg.xml in the resources folder
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Getter to retrieve the SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Shutdown method to close caches and connection pools
    public static void shutdown() {
        getSessionFactory().close();
    }
}
