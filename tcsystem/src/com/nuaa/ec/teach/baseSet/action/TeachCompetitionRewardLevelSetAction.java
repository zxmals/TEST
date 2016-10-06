package com.nuaa.ec.teach.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfclassTeachTimeDAO;
import com.nuaa.ec.dao.TfteachingCompetitionRewardLevelDAO;
import com.nuaa.ec.model.TfclassTeachTime;
import com.nuaa.ec.model.TfteachingCompetitionRewardLevel;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionSupport;

public class TeachCompetitionRewardLevelSetAction extends ActionSupport
		implements RequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	/**
	 * 数据传值对象
	 */
	private TfteachingCompetitionRewardLevel teachingCompetitionRewardLevel;
	private TfteachingCompetitionRewardLevelDAO teachingCompetitionRewardLevelDao = new TfteachingCompetitionRewardLevelDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private Integer operstatus;

	public String execute() {
		return SUCCESS;
	}

	/**
	 * 教学竞赛设置
	 * 
	 * @return
	 */
	public String teachingCompetitionRewardLevelList() {
		request.put("teachingCompetitionRewardLevelList",
				teachingCompetitionRewardLevelDao.findAll());
		return SUCCESS;
	}

	/**
	 * 添加教学竞赛数据字典项
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addTeachingCompetitionRewardLevel() throws Exception {
		Transaction tx = null;
		try {
			teachingCompetitionRewardLevel.setSpareTire("1");
			teachingCompetitionRewardLevel
					.setCompetRewardLevelId(pkmk.mkpk(
							EntityUtil.getPkColumnName(TfteachingCompetitionRewardLevel.class),
							EntityUtil.getTableName(TfteachingCompetitionRewardLevel.class),
							"TFcp"));
			teachingCompetitionRewardLevelDao
					.save(teachingCompetitionRewardLevel);
			tx = teachingCompetitionRewardLevelDao.getSession()
					.beginTransaction();
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
	 * 删除教学竞赛时数据字典项
	 * 
	 * @return
	 * @throws Exception
	 */
	public void deleteTeachingCompetitionRewardLevel() throws Exception {
		Transaction tx = null;
		try {
			teachingCompetitionRewardLevel.setSpareTire("0");
			teachingCompetitionRewardLevelDao
					.merge(teachingCompetitionRewardLevel);
			tx = teachingCompetitionRewardLevelDao.getSession()
					.beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}

	/**
	 * 更新教学竞赛数据字典项
	 * 
	 * @throws Exception
	 */
	public void updateTeachingCompetitionRewardLevel() throws Exception {
		Transaction tx = null;
		try {
			teachingCompetitionRewardLevel.setSpareTire("1");
			teachingCompetitionRewardLevelDao
					.merge(teachingCompetitionRewardLevel);
			tx = teachingCompetitionRewardLevelDao.getSession()
					.beginTransaction();
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

	public TfteachingCompetitionRewardLevel getTeachingCompetitionRewardLevel() {
		return teachingCompetitionRewardLevel;
	}

	public void setTeachingCompetitionRewardLevel(
			TfteachingCompetitionRewardLevel teachingCompetitionRewardLevel) {
		this.teachingCompetitionRewardLevel = teachingCompetitionRewardLevel;
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

}
