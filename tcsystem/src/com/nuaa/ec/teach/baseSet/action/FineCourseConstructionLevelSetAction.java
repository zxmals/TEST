package com.nuaa.ec.teach.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TffineCourseConstructionLevelDAO;
import com.nuaa.ec.model.TfclassTeachTime;
import com.nuaa.ec.model.TffineCourseConstructionLevel;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionSupport;

public class FineCourseConstructionLevelSetAction extends ActionSupport
		implements RequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	/**
	 * 数据传值对象
	 */
	private TffineCourseConstructionLevel entity;
	private TffineCourseConstructionLevelDAO entityDao = new TffineCourseConstructionLevelDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private Integer operstatus;

	public String execute() {
		return SUCCESS;
	}

	/**
	 * 精品课程等级设置
	 * 
	 * @return
	 */
	public String entityList() {
		request.put("entityList", entityDao.findAll());
		return SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addEntity() throws Exception {
		Transaction tx = null;
		try {
			entity.setSpareTire("1");
			entity.setCourseLevelId(pkmk.mkpk(
					EntityUtil.getPkColumnName(TffineCourseConstructionLevel.class),
					EntityUtil.getTableName(TffineCourseConstructionLevel.class), "TFCL"));
			entityDao.save(entity);
			tx = entityDao.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			entityList();
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
	public void deleteEntity() throws Exception {
		Transaction tx = null;
		try {
			entity.setSpareTire("0");
			entityDao.merge(entity);
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
	public void updateEntity() throws Exception {
		Transaction tx = null;
		try {
			entity.setSpareTire("1");
			entityDao.merge(entity);
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

	public TffineCourseConstructionLevel getEntity() {
		return entity;
	}

	public void setEntity(TffineCourseConstructionLevel entity) {
		this.entity = entity;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}
}
