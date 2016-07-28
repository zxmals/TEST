package com.nuaa.ec.action;

import com.nuaa.ec.model.Teacher;

public class TeacherManageAction {
	private String teacherid;
	private String password;
	private Teacher teacher;

	public String execute() {
		return "success";
	}

	public String login() {
		return "success";
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

}
