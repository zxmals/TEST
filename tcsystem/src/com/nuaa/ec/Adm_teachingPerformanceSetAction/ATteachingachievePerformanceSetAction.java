package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfteachingAchievementPerformanceDAO;
import com.nuaa.ec.dao.TfteachingAchievementProjectDAO;
import com.nuaa.ec.dao.TfteachingAchievementRewardLevelDAO;
import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingAchievementPerformance;
import com.nuaa.ec.model.TfteachingAchievementProject;
import com.nuaa.ec.model.TfteachingAchievementRewardLevel;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.utils.EntityUtil;


public class ATteachingachievePerformanceSetAction implements RequestAware,
		SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Tfterm term;
	private Teacher tfteacher;
	private TfteachingAchievementPerformance achieveperce;
	private TfteachingAchievementProject achieveproject;
	private TfteachingAchievementRewardLevel achievelevel;
	
	private TfteachingAchievementPerformanceDAO achievepercedao = new TfteachingAchievementPerformanceDAO();
	private TfteachingAchievementProjectDAO achieveprojectdao = new TfteachingAchievementProjectDAO();
	private TfteachingAchievementRewardLevelDAO achieveleveldao = new TfteachingAchievementRewardLevelDAO();
	private TftermDAO termdao = new TftermDAO();
	//default method
	public String execute(){
		return "success";
	}
	
	public String gainAllProject()throws Exception{
		try {
			int pagenum = 0;
			int limitrow = 0;
			String limit = (String) ServletActionContext.getRequest().getParameter("limit");
			String pagenumber = (String) ServletActionContext.getRequest().getParameter("pagenum");
			pagenum = pagenumber != null?(pagenum = !"".equals(pagenumber.trim()) ? Integer.parseInt(pagenumber) : 1):1;
			limitrow = limit != null?(!"".equals(limit.trim()) ? Integer.parseInt(limit) : 5):5;
			request.put("achieveproject", achieveprojectdao.findPaging((pagenum - 1)* limitrow, limitrow, EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId")));
			request.put("tftermList", termdao.findAll());
			request.put("rewardlevel", achieveleveldao.findAll());
			int li = achieveprojectdao.getRows(EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId"));
			int sumpage = 0;
			sumpage = li % limitrow == 0?li / limitrow:li / limitrow + 1;
			request.put("sumrow", li);
			request.put("sumpage", sumpage);
			request.put("nextpage", pagenum < sumpage?1 + pagenum:pagenum);
			request.put("prepage", pagenum > 1?pagenum - 1:1);
			request.put("pagenum", pagenum);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return "success";
	}
//	
	public void updateProject()throws Exception{
		Transaction tx = null;
		try {
			this.setAchievelevel(achieveleveldao.findById(achievelevel.getRewardId()));
			achieveproject.setProjectSumScore(achievelevel.getScore());
			achieveproject.setSpareTire("1");
			achieveproject.setTfteachingAchievementRewardLevel(achievelevel);
			achieveproject.setTfterm(termdao.findById(term.getTermId()));
			achieveprojectdao.merge(achieveproject);
			tx = achieveprojectdao.getSession().beginTransaction();
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
			achieveprojectdao.deleteBylogic(achieveproject.getProjectId());
			achievepercedao.deleteRef(achieveproject);
			tx = achievepercedao.getSession().beginTransaction();
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
			JSONArray jsay = JSONArray.fromObject(achievepercedao.findMember(achieveproject));
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
				achievepercedao.updateScore(tmps[0],tmpscore,achieveproject);
				sumscore -= tmpscore;
			}
			if(sumscore!=0){
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("分数分配不当，或多或少");
				return;
			}
			tx = achievepercedao.getSession().beginTransaction();
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
			achievepercedao.quitProject(tfteacher, achieveproject);
			tx = achievepercedao.getSession().beginTransaction();
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

	public TfteachingAchievementPerformance getAchieveperce() {
		return achieveperce;
	}

	public void setAchieveperce(TfteachingAchievementPerformance achieveperce) {
		this.achieveperce = achieveperce;
	}

	public TfteachingAchievementProject getAchieveproject() {
		return achieveproject;
	}

	public void setAchieveproject(TfteachingAchievementProject achieveproject) {
		this.achieveproject = achieveproject;
	}

	public TfteachingAchievementRewardLevel getAchievelevel() {
		return achievelevel;
	}

	public void setAchievelevel(TfteachingAchievementRewardLevel achievelevel) {
		this.achievelevel = achievelevel;
	}

	public Teacher getTfteacher() {
		return tfteacher;
	}

	public void setTfteacher(Teacher tfteacher) {
		this.tfteacher = tfteacher;
	}
}
