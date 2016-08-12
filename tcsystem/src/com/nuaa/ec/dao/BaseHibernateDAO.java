package com.nuaa.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;


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
	
	public org.hibernate.SessionFactory getSessionFactory(){
		return SessionFactory.getSessionFactory();
	}

	public void closeSqlAttr(PreparedStatement ps, ResultSet rs) {
		// TODO Auto-generated method stub
		if(ps!=null)
			try {
				ps.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		if(rs!=null)
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}

	public Connection getConn() {
		// TODO Auto-generated method stub
		try {
			return ((SessionFactoryImpl)getSessionFactory()).getConnectionProvider().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}