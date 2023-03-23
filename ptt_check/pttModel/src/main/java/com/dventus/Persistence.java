package com.dventus;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dventus.main.HibernateUtil;

public class Persistence {
	public void persistData(Persistence Tb) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.persist(Tb);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
