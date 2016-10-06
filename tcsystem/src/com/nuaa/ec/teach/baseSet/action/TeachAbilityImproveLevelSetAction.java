package com.nuaa.ec.teach.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfclassTeachTimeDAO;
import com.nuaa.ec.dao.TfteachingAbilityImproveLevelDAO;
import com.nuaa.ec.model.TfclassTeachTime;
import com.nuaa.ec.model.TfteachingAbilityImproveLevel;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionSupport;

public class TeachAbilityImproveLevelSetAction extends ActionSupport implements
		RequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	/**
	 * 数据传值对象
	 */
	private TfteachingAbilityImproveLevel teachingAbilityImproveLevel;
	private TfteachingAbilityImproveLevelDAO teachingAbilityImproveLevelDao = new TfteachingAbilityImproveLevelDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private Integer operstatus;

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	/**
	 * 教学能力提升设置
	 * 
	 * @return
	 */
	public String teachingAbilityImproveLevelList() {
		request.put("teachingAbilityImproveLevelList",
				teachingAbilityImproveLevelDao.findAll());
		return SUCCESS;
	}

	/**
	 * 添加教学能力提升项
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addTeachingAbilityImproveLevel() throws Exception {
		Transaction tx = null;
		try {
			teachingAbilityImproveLevel.setSpareTire("1");
			teachingAbilityImproveLevel
					.setImproveLevelId(pkmk.mkpk(
							EntityUtil.getPkColumnName(TfteachingAbilityImproveLevel.class),
							EntityUtil.getTableName(TfteachingAbilityImproveLevel.class),
							"TFImLv"));
			teachingAbilityImproveLevelDao.save(teachingAbilityImproveLevel);
			tx = teachingAbilityImproveLevelDao.getSession().beginTransaction();
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
	 * 删除教学能力提升项
	 * 
	 * @return
	 * @throws Exception
	 */
	public void deleteTeachingAbilityImproveLevel() throws Exception {
		Transaction tx = null;
		try {
			teachingAbilityImproveLevel.setSpareTire("0");
			teachingAbilityImproveLevelDao.merge(teachingAbilityImproveLevel);
			tx = teachingAbilityImproveLevelDao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}

	/**
	 * 更新教学能力提升项
	 * 
	 * @throws Exception
	 */
	public void updateTeachingAbilityImproveLevel() throws Exception {
		Transaction tx = null;
		try {
			teachingAbilityImproveLevel.setSpareTire("1");
			teachingAbilityImproveLevelDao.merge(teachingAbilityImproveLevel);
			tx = teachingAbilityImproveLevelDao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}

	public TfteachingAbilityImproveLevel getTeachingAbilityImproveLevel() {
		return teachingAbilityImproveLevel;
	}

	public void setTeachingAbilityImproveLevel(
			TfteachingAbilityImproveLevel teachingAbilityImproveLevel) {
		this.teachingAbilityImproveLevel = teachingAbilityImproveLevel;
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

}
