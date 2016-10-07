package com.nuaa.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;


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
	/**
	 * factory for sql search
	 * @return
	 */
	public org.hibernate.SessionFactory getSessionFactory();
	/**
	 *release area & over the Sql search 
	 * @param ps
	 * @param rs
	 * @param provider
	 */
	public void closeSqlAttr(PreparedStatement ps,ResultSet rs);
	/**
	 * gain the connection to use sql-select
	 * @return
	 * @throws SQLException 
	 */
	public Connection getConn() throws SQLException;
}