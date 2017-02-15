package com.nuaa.ec.action;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TeacherLoginInfoDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherCustom;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PersonalInfoAction extends ActionSupport implements RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -125454097584030605L;

	public String getPersonalInfo() throws Exception {
		Teacher teacherLogin = (Teacher) session.get("teacher");
		Teacher teacher = new TeacherDAO()
				.findById(teacherLogin.getTeacherId());
		TeacherCustom teacherCustom = new TeacherCustom();
		BeanUtils.copyProperties(teacherCustom, teacher);
		/*
		 * 判断用户角色
		 */
		if (teacher.getDepartAdmin().equals("1")) {
			teacherCustom.setRole("系主任");
		} else if (teacher.getResearchLabAdmin().equals("1")) {
			teacherCustom.setRole("研究所所长");
		} else if (teacher.getVaadmin().equals("1")) {
			teacherCustom.setRole("公益管理员");
		} else if (new TeacherLoginInfoDAO().findLevelByTeacherId(
				teacher.getTeacherId()).equals("2")) {
			teacherCustom.setRole("管理员");
		} else {
			teacherCustom.setRole("普通教师");
		}
		request.put("teacherCustom", teacherCustom);
		return "success";
	}

	@Override
	public String execute() throws Exception {
		return "success";
	}

	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private Map<String, Object> request;

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
}
