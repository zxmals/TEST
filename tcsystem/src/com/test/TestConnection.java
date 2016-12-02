package com.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.Test;

import com.nuaa.ec.dao.BaseHibernateDAO;

public class TestConnection extends BaseHibernateDAO{
	Session session=super.getSession();
	@Test
	public void testCon(){
		session.doWork(new Work() {
			
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection);
			}
		});
	}
}
