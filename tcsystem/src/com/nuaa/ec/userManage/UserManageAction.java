package com.nuaa.ec.userManage;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.Teacher;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserManageAction extends ActionSupport implements RequestAware{
	/**
	 * 删除用户信息
	 */
	public void delete(){
		Transaction tx=null;
		try{
			teacher=this.teacherDAO.findById(teacher.getTeacherId());
			teacher.setSpareTire("0");
			teacherDAO.merge(teacher);
			tx=this.teacherDAO.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
			try {
				ServletActionContext.getResponse().getWriter().write("succ");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 更新用户信息
	 */
	public void update() {
		Transaction tx=null;
		try{
			/*
			 * 获取完整的研究所信息
			 */
			researchLab=this.researchLabDAO.findById(researchLab.getResearchLabId());
			/*
			 * 获取完整的系别信息
			 */
			department=this.departmentDAO.findById(department.getDepartmentId());
			/*
			 * 设置研究所信息
			 */
			this.teacher.setResearchLab(researchLab);
			/*
			 * 设置系别信息
			 */
			this.teacher.setDepartment(department);
			/*
			 * 设置spareTire
			 */
			this.teacher.setSpareTire("1");
			/*
			 * 设置教师身份
			 */
			if(role!=null){
				if(role.trim().equals("0")){
					teacher.setResearchLabAdmin("0");
					teacher.setDepartAdmin("0");
					teacher.setVaadmin("0");
				}
				if(role.trim().equals("1")){
					teacher.setResearchLabAdmin("0");
					teacher.setDepartAdmin("1");
					teacher.setVaadmin("0");
				}
				if(role.trim().equals("2")){
					teacher.setResearchLabAdmin("1");
					teacher.setDepartAdmin("0");
					teacher.setVaadmin("0");
				}
				if(role.trim().equals("3")){
					teacher.setResearchLabAdmin("0");
					teacher.setDepartAdmin("0");
					teacher.setVaadmin("1");
				}
			}
			this.teacherDAO.merge(teacher);
			tx=this.teacherDAO.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
			try {
				ServletActionContext.getResponse().getWriter().write("error");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 添加用户信息
	 */
	public void add() {
		Transaction tx=null;
		try{
			/*
			 * 获取完整的研究所信息
			 */
			researchLab=this.researchLabDAO.findById(researchLab.getResearchLabId());
			/*
			 * 获取完整的系别信息
			 */
			department=this.departmentDAO.findById(department.getDepartmentId());
			/*
			 * 设置研究所信息
			 */
			this.teacher.setResearchLab(researchLab);
			/*
			 * 设置系别信息
			 */
			this.teacher.setDepartment(department);
			/*
			 * 设置spareTire
			 */
			this.teacher.setSpareTire("1");
			/*
			 * 设置教师身份
			 */
			if(role!=null){
				if(role.trim().equals("0")){
					teacher.setResearchLabAdmin("0");
					teacher.setDepartAdmin("0");
					teacher.setVaadmin("0");
				}
				if(role.trim().equals("1")){
					teacher.setResearchLabAdmin("0");
					teacher.setDepartAdmin("1");
					teacher.setVaadmin("0");
				}
				if(role.trim().equals("2")){
					teacher.setResearchLabAdmin("1");
					teacher.setDepartAdmin("0");
					teacher.setVaadmin("0");
				}
				if(role.trim().equals("3")){
					teacher.setResearchLabAdmin("0");
					teacher.setDepartAdmin("0");
					teacher.setVaadmin("1");
				}
			}
			this.teacherDAO.save(teacher);
			tx=this.teacherDAO.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
			try {
				ServletActionContext.getResponse().getWriter().write("error");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 获取教师信息
	 * @return
	 */
	public String get() {
		String pageSize_s=null;
		if(session.get("pageSize_user")!=null){
			pageSize_s=(String) (session.get("pageSize_user")+"");
			if(pageSize_s!=null && pageSize_s.trim().length()!=0){
				pageSize_user=Integer.parseInt(pageSize_s);
			}
		}
		boolean isDivided=false;
		if(this.getIsDivided()!=null && this.getIsDivided().trim().equals("true")){
			isDivided=true;
		}
		this.request.put("Teachers", this.teacherDAO.findAllByCondition(pageIndex, pageSize_user, (String) session.get("findCondition"), isDivided));
		return "success";
	}


	@Override
	public String execute() throws Exception {
		return "success";
	}

	private int pageIndex = 1;
	private int pageSize_user = 1;
	private String isDivided;
	private String findCondition;
	private TeacherDAO teacherDAO=new TeacherDAO();
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private Map<String,Object> request;
	private Teacher teacher;
	private ResearchLabDAO researchLabDAO=new ResearchLabDAO();
	private ResearchLab researchLab;
	private DepartmentDAO departmentDAO=new DepartmentDAO();
	private Department department;
	private String role;
	
	public String getIsDivided() {
		return isDivided;
	}

	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}

	public String getFindCondition() {
		return findCondition;
	}

	public void setFindCondition(String findCondition) {
		this.findCondition = findCondition;
		session.put("findCondition", findCondition);
	}

	public int getPageSize_user() {
		return pageSize_user;
	}

	public void setPageSize_user(int pageSize_user) {
		this.pageSize_user = pageSize_user;
		session.put("pageSize_user", pageSize_user);
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public ResearchLab getResearchLab() {
		return researchLab;
	}
	public void setResearchLab(ResearchLab researchLab) {
		this.researchLab = researchLab;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
