package com.nuaa.ec.teach.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfdegreeThesisGuidanceRewardLevelDAO;
import com.nuaa.ec.model.TfclassTeachTime;
import com.nuaa.ec.model.TfdegreeThesisGuidanceRewardLevel;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionSupport;

public class DegreeThesisGuidanceRewardLevelRatioSetAction extends
		ActionSupport implements RequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	/**
	 * 数据传值对象
	 */
	private TfdegreeThesisGuidanceRewardLevel degreeThesisGuidanceRewardLevel;
	private TfdegreeThesisGuidanceRewardLevelDAO degreeThesisGuidanceRewardLevelDao = new TfdegreeThesisGuidanceRewardLevelDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private Integer operstatus;

	public String execute() {
		return SUCCESS;
	}

	/**
	 * 学位论文指导系数设置
	 * 
	 * @return
	 */
	public String degreeThesisGuidanceRewardLevelList() {
		request.put("degreeThesisGuidanceRewardLevelList", degreeThesisGuidanceRewardLevelDao.findAll());
		return SUCCESS;
	}

	/**
	 * 添加学位论文指导数据字典项
	 * 
	 * @return
	 * @throws Exception
	 */
	public String adddegreeThesisGuidanceRewardLevel() throws Exception {
		Transaction tx = null;
		try {
			degreeThesisGuidanceRewardLevel.setSpareTire("1");
			degreeThesisGuidanceRewardLevel
					.setRewardLevelId(pkmk.mkpk(
							EntityUtil.getPkColumnName(TfdegreeThesisGuidanceRewardLevel.class),
							EntityUtil.getTableName(TfdegreeThesisGuidanceRewardLevel.class),
							"TFDR"));
			degreeThesisGuidanceRewardLevelDao.save(degreeThesisGuidanceRewardLevel);
			tx = degreeThesisGuidanceRewardLevelDao.getSession().beginTransaction();
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
	 * 删除学位论文指导
	 * 
	 * @return
	 * @throws Exception
	 */
	public void deletedegreeThesisGuidanceRewardLevel() throws Exception {
		Transaction tx = null;
		try {
			degreeThesisGuidanceRewardLevel.setSpareTire("0");
			degreeThesisGuidanceRewardLevelDao.merge(degreeThesisGuidanceRewardLevel);
			tx = degreeThesisGuidanceRewardLevelDao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}

	/**
	 * 更新学位论文数据字典项
	 * 
	 * @throws Exception
	 */
	public void updatedegreeThesisGuidanceRewardLevel() throws Exception {
		Transaction tx = null;
		try {
			degreeThesisGuidanceRewardLevel.setSpareTire("1");
			degreeThesisGuidanceRewardLevelDao.merge(degreeThesisGuidanceRewardLevel);
			tx = degreeThesisGuidanceRewardLevelDao.getSession().beginTransaction();
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

	public TfdegreeThesisGuidanceRewardLevel getDegreeThesisGuidanceRewardLevel() {
		return degreeThesisGuidanceRewardLevel;
	}

	public void setDegreeThesisGuidanceRewardLevel(
			TfdegreeThesisGuidanceRewardLevel degreeThesisGuidanceRewardLevel) {
		this.degreeThesisGuidanceRewardLevel = degreeThesisGuidanceRewardLevel;
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

}
