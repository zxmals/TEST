package com.nuaa.ec.action;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TeacherLoginInfoDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherCustom;
import com.nuaa.ec.model.TeacherLoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PersonalInfoAction extends ActionSupport implements RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -125454097584030605L;

	/*
	 * alter password
	 */
	public void alterPassword() throws Exception {
		Teacher teacherLogin = (Teacher) session.get("teacher");
		TeacherLoginInfoDAO teacherLoginInfoDao = new TeacherLoginInfoDAO();
		TeacherLoginInfo teacherLoginInfo = teacherLoginInfoDao
				.getPasswordByTeacherId(teacherLogin.getTeacherId());
		if (teacherLoginInfo.getPassword().equals(oriPassword)) {
			teacherLoginInfo.setPassword(newPassword);
			teacherLoginInfoDao.update(teacherLoginInfo);
			ServletActionContext.getResponse().getWriter().write("succ");
		} else {
			ServletActionContext.getResponse().getWriter()
					.write("oriPassError！");
		}
	}

	/*
	 * get personal info
	 */
	public String getPersonalInfo() throws Exception {
		Teacher teacherLogin = (Teacher) session.get("teacher");
		Teacher teacher = new TeacherDAO()
				.findById(teacherLogin.getTeacherId());
		TeacherCustom teacherCustom = new TeacherCustom();
		BeanUtils.copyProperties(teacherCustom, teacher);
		/*
		 * 判断用户角色
		 */
		StringBuilder role = new StringBuilder("");
		if (teacher.getDepartAdmin().equals("1")) {
			// teacherCustom.setRole("系主任");
			role.append("系主任|");
		}
		if (teacher.getResearchLabAdmin().equals("1")) {
			// teacherCustom.setRole("研究所所长");
			role.append("研究所所长|");
		}
		if (teacher.getVaadmin().equals("1")) {
			// teacherCustom.setRole("公益管理员");
			role.append("公益管理员|");
		}
		if (new TeacherLoginInfoDAO().findLevelByTeacherId(
				teacher.getTeacherId()).equals("2")) {
			// teacherCustom.setRole("管理员");
			role.append("管理员|");
		}
		if (teacher.getVaadmin().equals("0")
				&& teacher.getDepartAdmin().equals("0")
				&& teacher.getResearchLabAdmin().equals("0")
				&& !new TeacherLoginInfoDAO().findLevelByTeacherId(
						teacher.getTeacherId()).equals("2")) {
			//这里管理员的teacher表对应的的记录三个角色的字段的值也都是0，所以要排除管理员
			// teacherCustom.setRole("普通教师");
			role.append("普通教师|");
		}
		teacherCustom.setRole(role.substring(0, role.length() - 1));
		request.put("teacherCustom", teacherCustom);
		return "success";
	}

	@Override
	public String execute() throws Exception {
		return "success";
	}

	private String newPassword;
	private String oriPassword;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private Map<String, Object> request;

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOriPassword() {
		return oriPassword;
	}

	public void setOriPassword(String oriPassword) {
		this.oriPassword = oriPassword;
	}
}
