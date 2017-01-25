package com.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.Test;

import com.nuaa.ec.dao.BaseHibernateDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.model.Teacher;

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
	@Test
	public void testTeacherFazzyQuery(){
		TeacherDAO teacherDAO = new TeacherDAO();
		List<Teacher> teachers = teacherDAO.findTeacherByFuzzyQuery("0913");
		System.out.println(teachers.size());
	}
}
