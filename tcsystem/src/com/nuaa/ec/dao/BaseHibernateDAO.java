package com.nuaa.ec.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return SessionFactory.getSession();
	}

	public void closeSession() {
		SessionFactory.closeSession();
	}

	public void closeSession(Session session) {
		session.beginTransaction().commit();
		SessionFactory.closeSession();
	}
	
}