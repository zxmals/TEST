package com.nuaa.ec.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TfteachingRearchFundlevelDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingRearchFundlevel;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionSupport;

public class TeacherManagementAction extends ActionSupport implements
		RequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	/**
	 * 数据传值对象
	 */
	private Teacher teacher;
	private Department department;
	private ResearchLab researchLab;

	private TeacherDAO entityDao = new TeacherDAO();
	private DepartmentDAO departmentDao = new DepartmentDAO();
	private ResearchLabDAO researchLabDao = new ResearchLabDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private Integer operstatus = 0;

	public String execute() {
		return SUCCESS;
	}

	/**
	 * 用户管理
	 * 
	 * @return
	 */
	public String teacherList() {
		request.put("departmentList", departmentDao.findAll());
		request.put("researchLabList", researchLabDao.findAll());
		request.put("teacherList", entityDao.findAll());
		return "teacherlist";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String elementList() throws Exception {
		request.put("departmentList", departmentDao.findAll());
		request.put("researchLabList", researchLabDao.findAll());
		return SUCCESS;
	}

	public String addTeacher() throws Exception {
		/**
		 * 再次添加列表选项数据 同一页面重复操作
		 */
		request.put("departmentList", departmentDao.findAll());
		request.put("researchLabList", researchLabDao.findAll());
		Transaction tx = null;
		/**
		 * 根据前端传来的departmentName查找数据字典(Department表)获取Department对象
		 */
		department = (Department) departmentDao.findByDepartmentName(
				department.getDepartmentName()).get(0);
		/**
		 * 根据前端传来的researchLabName查找数据字典()获取ResearchLab对象
		 */
		researchLab = (ResearchLab) researchLabDao.findByResearchLabName(
				researchLab.getResearchLabName()).get(0);
		try {
			teacher.setSpareTire("1");
			teacher.setDepartment(department);
			teacher.setResearchLab(researchLab);
			entityDao.save(teacher);
			tx = entityDao.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public void deleteTeacher() throws Exception {
		department = (Department) departmentDao.findByDepartmentName(
				department.getDepartmentName()).get(0);
		researchLab = (ResearchLab) researchLabDao.findByResearchLabName(
				researchLab.getResearchLabName()).get(0);
		Transaction tx = null;
		try {
			teacher.setSpareTire("0");
			teacher.setDepartment(department);
			teacher.setResearchLab(researchLab);
			entityDao.merge(teacher);
			tx = entityDao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}

	/**
	 * 更新
	 * 
	 * @throws Exception
	 */
	public void updateTeacher() throws Exception {
		department = (Department) departmentDao.findByDepartmentName(
				department.getDepartmentName()).get(0);
		researchLab = (ResearchLab) researchLabDao.findByResearchLabName(
				researchLab.getResearchLabName()).get(0);
		Transaction tx = null;
		try {
			teacher.setSpareTire("1");
			teacher.setDepartment(department);
			teacher.setResearchLab(researchLab);
			entityDao.merge(teacher);
			tx = entityDao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public ResearchLab getResearchLab() {
		return researchLab;
	}

	public void setResearchLab(ResearchLab researchLab) {
		this.researchLab = researchLab;
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}
}
