package com.nuaa.ec.teach.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfclassTeachTimeDAO;
import com.nuaa.ec.dao.TftechingAbilityEffectSubModularDAO;
import com.nuaa.ec.model.ExpertType;
import com.nuaa.ec.model.TfclassTeachTime;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 总堂时数据字典项设置
 * 
 * @author 远野贵树
 * 
 */
public class ClassTeachRatioSetAction extends ActionSupport implements
		RequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	/**
	 * 数据传值对象
	 */
	private TfclassTeachTime classTeachTime;
	private TfclassTeachTimeDAO classTeachTimeDao = new TfclassTeachTimeDAO();
//	private TftechingAbilityEffectSubModularDAO tftechingAbilityEffectSubModularDAO = new TftechingAbilityEffectSubModularDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private Integer operstatus;

	public String execute() {
		return "success";
	}

	/**
	 * 总堂时系数设置
	 * 
	 * @return
	 */
	public String totalClassTimeRatio() {
		request.put("classTeachTimeList", classTeachTimeDao.findAll());
		return "success";
	}

	/**
	 * 添加总堂时数据字典项
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addtotalClassTime() throws Exception {
		Transaction tx = null;
		try {
			classTeachTime.setSpareTire("1");
			classTeachTime
					.setSumtimeId(pkmk.mkpk(
							EntityUtil.getPkColumnName(TfclassTeachTime.class),
							EntityUtil.getTableName(TfclassTeachTime.class),
							"TFTCtime"));
			classTeachTimeDao.save(classTeachTime);
			tx = classTeachTimeDao.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			totalClassTimeRatio();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}

	/**
	 * 删除总堂时数据字典项
	 * 
	 * @return
	 * @throws Exception
	 */
	public void deletetotalClassTime() throws Exception {
		Transaction tx = null;
		try {
			classTeachTime.setSpareTire("0");
			classTeachTimeDao.merge(classTeachTime);
			tx = classTeachTimeDao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}

	/**
	 * 更新总堂时数字字典项
	 * 
	 * @throws Exception
	 */
	public void updatetotalClassTime() throws Exception {
		Transaction tx = null;
		try {
			classTeachTime.setSpareTire("1");
			classTeachTimeDao.merge(classTeachTime);
			tx = classTeachTimeDao.getSession().beginTransaction();
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

	public TfclassTeachTime getClassTeachTime() {
		return classTeachTime;
	}

	public void setClassTeachTime(TfclassTeachTime classTeachTime) {
		this.classTeachTime = classTeachTime;
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

}
