package com.nuaa.ec.teachingPerformanceSetAction;

import java.util.Map;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TffamousTeacherTeamPerformanceDAO;
import com.nuaa.ec.dao.TffamousTeacherTeamProjectDAO;
import com.nuaa.ec.dao.TffamousTeacherTeamRewadLevelDAO;
import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TffamousTeacherTeamPerformance;
import com.nuaa.ec.model.TffamousTeacherTeamProject;
import com.nuaa.ec.model.TffamousTeacherTeamRewadLevel;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class GTteachingfamousteamPerformanceSetAction implements RequestAware,
		SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Tfterm term;
	private TffamousTeacherTeamPerformance teachteam;
	private TffamousTeacherTeamProject teachteamprojec;
	private TffamousTeacherTeamRewadLevel teachteamrewardlevel;

	private TffamousTeacherTeamPerformanceDAO teachteamdao = new TffamousTeacherTeamPerformanceDAO();
	private TffamousTeacherTeamProjectDAO teachteamprojecdao = new TffamousTeacherTeamProjectDAO();
	private TffamousTeacherTeamRewadLevelDAO teachteamrewardleveldao = new TffamousTeacherTeamRewadLevelDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private TftermDAO termdao = new TftermDAO();
	//default method
	public String execute(){
		return "success";
	}
	
	public String gainAllProject()throws Exception{
		try {
			int pagenum = 1;
			int limitrow = 5;
			String limit = (String) ServletActionContext.getRequest().getParameter("limit");
			String pagenumber = (String) ServletActionContext.getRequest().getParameter("pagenum");
			if (pagenumber != null) {
				pagenum = !"".equals(pagenumber.trim()) ? Integer.parseInt(pagenumber) : 1;
			}
			if (limit != null) {
				limitrow = !"".equals(limit.trim()) ? Integer.parseInt(limit) : 5;
			}
			request.put("teachteamli", teachteamprojecdao.findPaging((pagenum - 1)* limitrow, limitrow, EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId")));
			request.put("tftermList", termdao.findAll());
			request.put("rewardLevel", teachteamrewardleveldao.findAll());
			int li = teachteamprojecdao.getRows(EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId"));
			int sumpage = 1;
			if (li % limitrow == 0) {
				sumpage = li / limitrow;
			} else {
				sumpage = li / limitrow + 1;
			}
			request.put("sumrow", li);
			request.put("sumpage", sumpage);
			if (pagenum < sumpage) {
				request.put("nextpage", 1 + pagenum);
			} else {
				request.put("nextpage", pagenum);
			}
			if (pagenum > 1) {
				request.put("prepage", pagenum - 1);
			} else {
				request.put("prepage", 1);
			}
			request.put("pagenum", pagenum);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return "success";
	}
	
	public void addProject()throws Exception{
		Transaction tx = null;
		try {
			teachteamprojec.setSpareTire("1");
			teachteamprojec.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
			teachteamprojec.setCheckout("0");
			teachteamprojec.setTeacherTeamPerformanceId(pkmk.mkpk(EntityUtil.getPkColumnName(TffamousTeacherTeamProject.class), EntityUtil.getTableName(TffamousTeacherTeamProject.class), "TTE"));
			teachteamprojec.setTffamousTeacherTeamRewadLevel(teachteamrewardleveldao.findById(teachteamrewardlevel.getTeachRewardLevelId()));
			teachteamprojec.setTfterm(termdao.findById(teachteamprojec.getTfterm().getTermId()));
			teachteamprojecdao.save(teachteamprojec);
			tx = teachteamprojecdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			throw e;
		}
	}
	
	//Getter & Setter
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Tfterm getTerm() {
		return term;
	}

	public void setTerm(Tfterm term) {
		this.term = term;
	}

	public TffamousTeacherTeamPerformance getTeachteam() {
		return teachteam;
	}

	public void setTeachteam(TffamousTeacherTeamPerformance teachteam) {
		this.teachteam = teachteam;
	}

	public TffamousTeacherTeamProject getTeachteamprojec() {
		return teachteamprojec;
	}

	public void setTeachteamprojec(TffamousTeacherTeamProject teachteamprojec) {
		this.teachteamprojec = teachteamprojec;
	}

	public TffamousTeacherTeamRewadLevel getTeachteamrewardlevel() {
		return teachteamrewardlevel;
	}

	public void setTeachteamrewardlevel(
			TffamousTeacherTeamRewadLevel teachteamrewardlevel) {
		this.teachteamrewardlevel = teachteamrewardlevel;
	}

}
