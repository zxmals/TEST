package com.nuaa.ec.teach.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfoffCampusPracticeGuidanceLevelDAO;
import com.nuaa.ec.model.TfoffCampusPracticeGuidanceLevel;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionSupport;

public class OffCampusPracticeGuidanceLevelSetAction extends ActionSupport
		implements RequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	/**
	 * 数据传值对象
	 */
	private TfoffCampusPracticeGuidanceLevel entity;
	private TfoffCampusPracticeGuidanceLevelDAO entityDao = new TfoffCampusPracticeGuidanceLevelDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private Integer operstatus;

	public String execute() {
		return SUCCESS;
	}

	/**
	 * 教材编写等级设置
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
			entity.setProjectId(pkmk.mkpk(
					EntityUtil
							.getPkColumnName(TfoffCampusPracticeGuidanceLevel.class),
					EntityUtil
							.getTableName(TfoffCampusPracticeGuidanceLevel.class),
					"TFOCP"));
			entityDao.save(entity);
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

	public TfoffCampusPracticeGuidanceLevel getEntity() {
		return entity;
	}

	public void setEntity(TfoffCampusPracticeGuidanceLevel entity) {
		this.entity = entity;
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

}
