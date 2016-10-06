package com.nuaa.ec.teach.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TffamousTeacherTeamRewadLevelDAO;
import com.nuaa.ec.model.TfclassTeachTime;
import com.nuaa.ec.model.TffamousTeacherTeamRewadLevel;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionSupport;

public class FamousTeacherTeamRewadLevelSetAction extends ActionSupport
		implements RequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	/**
	 * 数据传值对象
	 */
	private TffamousTeacherTeamRewadLevel famousTeacherTeamRewadLevel;
	private TffamousTeacherTeamRewadLevelDAO famousTeacherTeamRewadLevelDao = new TffamousTeacherTeamRewadLevelDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private Integer operstatus;

	/**
	 * 优秀教师团队称号系数设置
	 * 
	 * @return
	 */
	public String famousTeacherTeamRewadLevelList() {
		request.put("famousTeacherTeamRewadLevelList",
				famousTeacherTeamRewadLevelDao.findAll());
		return SUCCESS;
	}

	/**
	 * 添加优秀教师团队称号删除
	 */
	public void addFamousTeacherTeamRewadLevel() {
		famousTeacherTeamRewadLevel.setSpareTire("1");
		famousTeacherTeamRewadLevel.setTeachRewardLevelId(pkmk.mkpk(
				EntityUtil.getPkColumnName(TfclassTeachTime.class),
				EntityUtil.getTableName(TfclassTeachTime.class), "Tteam"));
		famousTeacherTeamRewadLevelDao.save(famousTeacherTeamRewadLevel);
	}

	/**
	 * 删除优秀教师团队称号删除
	 * 
	 * @throws Exception
	 */
	public void deletefamousTeacherTeamRewadLevel() throws Exception {
		Transaction tx = null;
		try {
			famousTeacherTeamRewadLevel.setSpareTire("0");
			famousTeacherTeamRewadLevelDao.merge(famousTeacherTeamRewadLevel);
			tx = famousTeacherTeamRewadLevelDao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}

	/**
	 * 更新优秀教师团队称号
	 * 
	 * @throws Exception
	 */
	public void updatefamousTeacherTeamRewadLevel() throws Exception {
		Transaction tx = null;
		try {
			famousTeacherTeamRewadLevel.setSpareTire("1");
			famousTeacherTeamRewadLevelDao.merge(famousTeacherTeamRewadLevel);
			tx = famousTeacherTeamRewadLevelDao.getSession().beginTransaction();
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

	public TffamousTeacherTeamRewadLevel getFamousTeacherTeamRewadLevel() {
		return famousTeacherTeamRewadLevel;
	}

	public void setFamousTeacherTeamRewadLevel(
			TffamousTeacherTeamRewadLevel famousTeacherTeamRewadLevel) {
		this.famousTeacherTeamRewadLevel = famousTeacherTeamRewadLevel;
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

}
