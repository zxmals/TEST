package com.nuaa.ec.teach.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfclassTeachEvaluationDAO;
import com.nuaa.ec.model.TfclassTeachEvaluation;
import com.nuaa.ec.model.TfclassTeachTime;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionSupport;

public class classTeachEvaluationSetAction extends ActionSupport implements
		RequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	/**
	 * 数据传值对象
	 */
	private TfclassTeachEvaluation teachEvaluation;
	private TfclassTeachEvaluationDAO classTeachEvaluationDao = new TfclassTeachEvaluationDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private Integer operstatus;

	public String execute() {
		return SUCCESS;
	}

	/**
	 * 课堂教学评估设置
	 * 
	 * @return
	 */
	public String teachEvaluation() {
		request.put("teachEvaluationList", classTeachEvaluationDao.findAll());
		return SUCCESS;

	}

	/**
	 * 添加教学评估
	 * 
	 * @throws Exception
	 */
	public void addTeachEvaluation() throws Exception {
		Transaction tx = null;
		try {
			teachEvaluation.setSpareTire("1");
			teachEvaluation.setEvaluationId(pkmk.mkpk(
					EntityUtil.getPkColumnName(TfclassTeachEvaluation.class),
					EntityUtil.getTableName(TfclassTeachEvaluation.class),
					"TFTCe"));
			classTeachEvaluationDao.save(teachEvaluation);
			tx = classTeachEvaluationDao.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
	}

	/**
	 * 删除教学评价
	 * 
	 * @return
	 * @throws Exception
	 */
	public void deleteTeachEvaluation() throws Exception {
		Transaction tx = null;
		try {
			teachEvaluation.setSpareTire("0");
			classTeachEvaluationDao.merge(teachEvaluation);
			tx = classTeachEvaluationDao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}

	/**
	 * 更新教学评价
	 * 
	 * @throws Exception
	 */
	public void updateTeachEvaluation() throws Exception {
		Transaction tx = null;
		try {
			teachEvaluation.setSpareTire("1");
			classTeachEvaluationDao.merge(teachEvaluation);
			tx = classTeachEvaluationDao.getSession().beginTransaction();
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

	public TfclassTeachEvaluation getTeachEvaluation() {
		return teachEvaluation;
	}

	public void setTeachEvaluation(TfclassTeachEvaluation teachEvaluation) {
		this.teachEvaluation = teachEvaluation;
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

}
