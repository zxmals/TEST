package com.nuaa.ec.dao;

import org.hibernate.Session;


/**
 * Data access interface for domain model
 * @author MyEclipse Persistence Tools
 */
public interface IBaseHibernateDAO {
	/***
	 * get session
	 * @return
	 */
	public Session getSession();
	/***
	 * close session
	 * 
	 * commit transaction
	 */
	public void closeSession(Session session);
}