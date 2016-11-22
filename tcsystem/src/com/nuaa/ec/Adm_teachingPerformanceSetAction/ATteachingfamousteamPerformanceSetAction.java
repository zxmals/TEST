package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.util.Map;







import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.SelfUndertakeTaskDAO;
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

public class ATteachingfamousteamPerformanceSetAction implements RequestAware,
		SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Tfterm term;
	private Teacher tfteacher;
	private TffamousTeacherTeamPerformance teachteam;
	private TffamousTeacherTeamProject teachteamprojec;
	private TffamousTeacherTeamRewadLevel teachteamrewardlevel;

	private TffamousTeacherTeamPerformanceDAO teachteamdao = new TffamousTeacherTeamPerformanceDAO();
	private TffamousTeacherTeamProjectDAO teachteamprojecdao = new TffamousTeacherTeamProjectDAO();
	private TffamousTeacherTeamRewadLevelDAO teachteamrewardleveldao = new TffamousTeacherTeamRewadLevelDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private TftermDAO termdao = new TftermDAO();
	private SelfUndertakeTaskDAO selfdao = new SelfUndertakeTaskDAO();
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
	
	public void updateTeachTeam()throws Exception{
		Transaction tx = null;
		try {
			teachteamprojec.setSpareTire("1");
			this.setTeachteamrewardlevel(teachteamrewardleveldao.findById(teachteamrewardlevel.getTeachRewardLevelId()));
			teachteamprojec.setTffamousTeacherTeamRewadLevel(teachteamrewardlevel);
			teachteamprojec.setTfterm(termdao.findById(teachteamprojec.getTfterm().getTermId()));
			teachteamprojec.setProjectSumScore(teachteamrewardlevel.getScore());
			teachteamprojecdao.merge(teachteamprojec);
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
	
	public void deleteProject()throws Exception{
		Transaction tx = null;
		try {
			teachteamprojecdao.deleteBylogic(teachteamprojec.getTeacherTeamPerformanceId());
			teachteamdao.deleteRef(teachteamprojec);
			tx = teachteamdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			throw e;
		}
	}
	
	public void getMember()throws Exception{
		try {
			JSONArray jsay = JSONArray.fromObject(teachteamdao.findMember(teachteamprojec));
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().write(jsay.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public void changescore()throws Exception{
		Transaction tx = null;
		try {
			String a = ServletActionContext.getRequest().getParameter("mixs");
			String[] tscores = a.trim().split("_");
			String[] tmps = new String[2];
			double sumscore = Double.parseDouble(ServletActionContext.getRequest().getParameter("sumscore"));
			double tmpscore = 0;
			for(int i=0;i<tscores.length;i++){
				tmps = tscores[i].split(",");
				tmpscore = Double.parseDouble(tmps[1]);
				teachteamdao.updateScore(tmps[0],tmpscore, teachteamprojec);
				sumscore -= tmpscore;
			}
			if(sumscore!=0){
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("分数分配不当，或多或少");
				return;
			}
			tx = teachteamdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			throw e;
		}
	}
	
	
	public void quitProject()throws Exception{
		Transaction tx = null;
		try {
			teachteamdao.quitProject(tfteacher, teachteamprojec);
			tx = teachteamdao.getSession().beginTransaction();
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

	public Teacher getTfteacher() {
		return tfteacher;
	}

	public void setTfteacher(Teacher tfteacher) {
		this.tfteacher = tfteacher;
	}
}
