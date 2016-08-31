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
import com.nuaa.ec.utils.EntityUtil;
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
			this.setOperstatus(-1);
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
	// TODO: 期刊设置 //Periodical - set
	/***
	 * get the information of periodical //获取期刊表的信息
	 * @return
	 */
	public String getPeriodicalINF(){
		try {
			request.put("Periodical", periodicaldao.findAll());
			getPeriodicalTypeINF();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	/***
	 * 增加一个期刊  // add a periodical
	 * @return
	 */
	public String addPeriodical(){
		Transaction tx = null;
		try {
			periodi.setSpareTire("1");
			periodi.setPeriodicalId(pkmk.mkpk(EntityUtil.getPkColumnName(Periodical.class), EntityUtil.getTableName(Periodical.class), "P"));
			periodi.setPeriodicalType(ptypedao.findById(periodi.getPeriodicalType().getPtypeId()));
			periodicaldao.save(periodi);
			tx = periodicaldao.getSession().beginTransaction();
			tx.commit();
			getPeriodicalINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
			this.setOperstatus(-1);
		}
		return "success";
	}
	/***
	 * 更新一个期刊//update a periodical 
	 */
	public void updatePeriodical(){
		periodi.setSpareTire("1");
		periodi.setPeriodicalType(ptypedao.findById(periodi.getPeriodicalType().getPtypeId()));
		Transaction tx = null;
		try {
			periodicaldao.merge(periodi);
			tx = periodicaldao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
	}
	/***
	 * 删除一个期刊 // delete a periodical 
	 */
	public void deletePeriodical(){
		periodi.setSpareTire("0");
		periodi.setPeriodicalType(ptypedao.findById(periodi.getPeriodicalType().getPtypeId()));
		Transaction tx = null;
		try {
			periodicaldao.merge(periodi);
			tx = periodicaldao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
	}
	// TODO: 期刊论文评分设置 //PeriodicalScore - set
	public String getPeriodicalScoreINF(){
		try {
			request.put("PeriodicalScore", ppaperscoredao.findAll());
			getPeriodicalTypeINF();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	/***
	 * 添加一项新的 期刊评分项 / add a new periodical - paper - evaluate --score
	 * @return
	 */
	public String addPeriodicalScore(){
		Transaction tx = null;
		try {
			ppaperscore.setSpareTire("1");
			ppaperscore.setPeriodicalType(ptypedao.findById(ppaperscore.getPeriodicalType().getPtypeId()));
			ppaperscore.setScoreId(pkmk.mkpk(EntityUtil.getPkColumnName(PeriodicalPapersScore.class), EntityUtil.getTableName(PeriodicalPapersScore.class), "PPS"));
			ppaperscore.setSubModular(null);
			ppaperscoredao.save(ppaperscore);
			tx = ppaperscoredao.getSession().beginTransaction();
			tx.commit();
			getPeriodicalScoreINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			this.setOperstatus(-1);
		}
		return "success";
	}
	/***
	 * 更新一项期刊评分/ update a periodical - paper - evaluate --score
	 */
	public void updatePeriodicalScore(){
		Transaction tx = null;
		try {
			ppaperscore.setSpareTire("1");
			ppaperscore.setPeriodicalType(ptypedao.findById(ppaperscore.getPeriodicalType().getPtypeId()));
			ppaperscoredao.merge(ppaperscore);
			tx = ppaperscoredao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
	}
	/***
	 * 删除一个 期刊评分 项/delete a periodical-paper evaluate --score
	 */
	public void deletePeriodicalScore(){
		Transaction tx = null;
		try {
			ppaperscore.setSpareTire("0");
			ppaperscore.setPeriodicalType(ptypedao.findById(ppaperscore.getPeriodicalType().getPtypeId()));
			ppaperscoredao.merge(ppaperscore);
			tx = ppaperscoredao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
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
