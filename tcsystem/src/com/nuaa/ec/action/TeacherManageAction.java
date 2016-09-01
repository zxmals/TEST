package com.nuaa.ec.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.nuaa.ec.dao.BaseHibernateDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TeacherLoginInfoDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherLoginInfo;

public class TeacherManageAction implements SessionAware {
	private String teacherid;
	private String password;
	private String loginstatus;
	private Map<String, Object> session;
	private Teacher teacher;
	private TeacherLoginInfoDAO teacherlgdao = new TeacherLoginInfoDAO();
	private TeacherDAO teacherdao = new TeacherDAO();

	public String execute() {
		return "success";
	}
/**
 * teacher log-in method/教师登陆方法
 * @return
 */
	public String login() throws Exception{
		
		TeacherLoginInfo teacherlg = null;
		// Teacher teacher = null;
		String loginresult = "500";
		/*
		 * 验证登录者是否为管理员或已经不是第一次登录 check user be or not fisrt log in check user be
		 * or not admin
		 */
		teacherlg = teacherlgdao.findById(teacherid);
		if (teacherlg != null&&"1".equals(teacherlg.getSpareTire())) {
			/*
			 * 不是第一次登录。验证密码/ not fisrt login --check pwd
			 */
			if (password.equals(teacherlg.getPassword())) {
				teacherlg.setLastLoginDate(new java.sql.Timestamp(
						new java.util.Date().getTime()));
				teacherlg.setLoginTime(teacherlg.getLoginTime() + 1);
				teacherlgdao.save(teacherlg);
				this.setTeacher(teacherdao.findById(teacherid));
				session.put("teacher", teacher);
				/*
				 * 验证身份/check user level
				 */
				/* 普通教师登录/general teacher login */
				if ("1".equals(teacherlg.getLevel())){
					loginresult = "general-t";
					session.put("teacherLevel", "GT");
				}
				else /* 管理员教师登录/admin-teacher login */
				if ("2".equals(teacherlg.getLevel())){
					loginresult = "admin-t";
					session.put("teacherLevel", "AT");
				}
				System.out.println("log in");
			} else {
				/* 登录密码错误/error pwd */
				this.setLoginstatus("error password");
				loginresult = "relogin";
				System.out.println("error password");
			}
		} else {
			/*
			 * 教师第一次登录/teacher first login
			 */
			teacher = teacherdao.findById(teacherid);
			/* 查找教师/find teacher */
			if (teacher != null&&"1".equals(teacher.getSpareTire())) {
				/* 密码验证/check pwd */
				if (password.equals(teacher.getTeacherId())) {
					System.out.println("first login,welcome!");
					this.setLoginstatus("first login,welcome!");
					/*
					 * 第一次登录 初始默认信息: level:1(普通教师) logintime: 1(登录次数)/ first
					 * login default-info:level:1(general-teacher),logintime:1
					 */
					teacherlg = new TeacherLoginInfo(teacherid, teacher,
							password, "1", "1", 1, new java.sql.Timestamp(
									new java.util.Date().getTime()));
					teacherlgdao.save(teacherlg);
					session.put("teacher", teacher);
					session.put("teacherLevel", "GT");
					loginresult = "general-t";
				} else {
					System.out
							.println("error password.first login password==ID");
					this.setLoginstatus("error password.first login password==ID");
					loginresult = "relogin";
				}
			} else {
				System.out.println("user not found");
				this.setLoginstatus("user not found");
				loginresult = "relogin";
			}
		}
		// TODO NOTE: close Session / 重要: 使用完session后必须关闭Session
		new BaseHibernateDAO()
				.closeSession(new BaseHibernateDAO().getSession());
		return loginresult;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getLoginstatus() {
		return loginstatus;
	}

	public void setLoginstatus(String loginstatus) {
		this.loginstatus = loginstatus;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
