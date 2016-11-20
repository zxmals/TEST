package com.nuaa.ec.action;

import java.util.Map;



import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.model.Teacher;
import com.opensymphony.xwork2.ActionSupport;

public class Vaset2 extends ActionSupport implements RequestAware{
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	/**
	 * 数据传值对象
	 */
	private Teacher entity;
	private TeacherDAO entityDao = new TeacherDAO();
	private Integer operstatus;

	public String execute() {
		return SUCCESS;
	}

	/**
	 * 公益管理员设置
	 * 
	 * @return
	 */
	public String entityList() {
		Session session = entityDao.getSession();
		String queryString1 = "from Teacher as t where t.vaadmin = '1'";
		Query queryObject1 = session.createQuery(queryString1);
		String queryString2 = "from Teacher as t where t.vaadmin = '0' or t.vaadmin = 'NULL'";
		Query queryObject2 = session.createQuery(queryString2);
		request.put("Vaadmin", queryObject1.list());
		request.put("notVaadmin", queryObject2.list());
		return SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public void addVaAdmin() throws Exception {
		Transaction tx = null;
		try {
			Session session  = entityDao.getSession();
			String queryString1 = "update Teacher set VaAdmin = '1' where teacherId = :id";
			Query query = session.createQuery(queryString1);
			query.setString("id", entity.getTeacherId());
			query.executeUpdate();
			
			tx = session.beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
			this.setOperstatus(1);

		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public void deleteVaAdmin() throws Exception {
		Transaction tx = null;

		try {
			Session session  = entityDao.getSession();
			String queryString1 = "update Teacher set VaAdmin = '0' where teacherId = :id";
			Query query = session.createQuery(queryString1);
			query.setString("id", entity.getTeacherId());
			query.executeUpdate();
			
			tx = session.beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
			this.setOperstatus(1);

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

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	

	public Teacher getEntity() {
		return entity;
	}

	public void setEntity(Teacher entity) {
		this.entity = entity;
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

}
