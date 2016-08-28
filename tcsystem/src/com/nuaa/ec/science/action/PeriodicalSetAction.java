package com.nuaa.ec.science.action;


import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.PeriodicalDAO;
import com.nuaa.ec.dao.PeriodicalPapersScoreDAO;
import com.nuaa.ec.dao.PeriodicalTypeDAO;
import com.nuaa.ec.model.Periodical;
import com.nuaa.ec.model.PeriodicalPapersScore;
import com.nuaa.ec.model.PeriodicalType;
import com.nuaa.ec.utils.PrimaryKMaker;

public class PeriodicalSetAction implements RequestAware{
	private PeriodicalType ptype;
	private PeriodicalPapersScore ppaperscore;
	private Periodical periodi;
	private int operstatus;
	
	private Map<String, Object>request;
	
	private PeriodicalDAO periodicaldao = new PeriodicalDAO();
	private PeriodicalTypeDAO ptypedao = new PeriodicalTypeDAO();
	private PeriodicalPapersScoreDAO ppaperscoredao = new PeriodicalPapersScoreDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	// TODO: 期刊类型设置 //PeriodicalType - set
	/***
	 *  获取所有可用期刊类型信息//get all of periodicaltype - inf
	 * @return
	 */
	public String getPeriodicalTypeINF(){
		try {
			request.put("PeriodicalType", ptypedao.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	} 

	/***
	 * add periodicalType //添加一条期刊类型
	 * @return
	 */
	public String addPtype (){
		ptype.setPtypeId(pkmk.mkpk("PTypeId", "PeriodicalType", "PT"));
		ptype.setSpareTire("1");
		Transaction tx = null;
		try {
			ptypedao.save(ptype);
			tx = ptypedao.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			getPeriodicalTypeINF();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
		return "success";
	}
	/***
	 * update - periodicalType //更新期刊类型
	 */
	public void updatePtype(){
		ptype.setSpareTire("1");
		Transaction tx = null;
		try {
			ptypedao.merge(ptype);
			tx = ptypedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
	}
	/***
	 *  delete the periodical-type //删除一条期刊类型
	 */
	public void deletePtype(){
		ptype.setSpareTire("0");
		Transaction tx = null;
		try {
			ptypedao.merge(ptype);
			tx = ptypedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
	}
	// TODO: 期刊设置 //PeriodicalType - set
	public String getPeriodicalINF(){
		try {
			request.put("Periodical", periodicaldao.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	//TODO: Getter and setter
	public PeriodicalType getPtype() {
		return ptype;
	}

	public void setPtype(PeriodicalType ptype) {
		this.ptype = ptype;
	}

	public PeriodicalPapersScore getPpaperscore() {
		return ppaperscore;
	}

	public void setPpaperscore(PeriodicalPapersScore ppaperscore) {
		this.ppaperscore = ppaperscore;
	}

	public Periodical getPeriodi() {
		return periodi;
	}

	public void setPeriodi(Periodical periodi) {
		this.periodi = periodi;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

}
