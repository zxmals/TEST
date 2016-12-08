package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.util.Map;


import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfteachingPaperPerformanceDAO;
import com.nuaa.ec.dao.TfteachingPaperProjectDAO;
import com.nuaa.ec.dao.TfteachingPaperRetrievalConditionDAO;
import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingPaperPerformance;
import com.nuaa.ec.model.TfteachingPaperProject;
import com.nuaa.ec.model.TfteachingPaperRetrievalCondition;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.utils.EntityUtil;


public class ATteachingpaperPerformanceSetAction implements RequestAware,
		SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Tfterm term;
	private Teacher tfteacher;
	private TfteachingPaperPerformance teachpaperpce;
	private TfteachingPaperProject teachpaperproject;
	private TfteachingPaperRetrievalCondition teachpaperretri;

	private TfteachingPaperPerformanceDAO teachpaperpcedao = new TfteachingPaperPerformanceDAO();
	private TfteachingPaperProjectDAO teachpaperprojectdao = new TfteachingPaperProjectDAO();
	private TfteachingPaperRetrievalConditionDAO teachpaperretridao = new TfteachingPaperRetrievalConditionDAO();
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
			request.put("teachpaperli", teachpaperprojectdao.findPaging((pagenum - 1)* limitrow, limitrow, EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId")));
			request.put("tftermList", termdao.findAll());
			request.put("paperretrili", teachpaperretridao.findAll());
			int li = teachpaperprojectdao.getRows(EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId"));
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
	
	
	public void updateProject()throws Exception{
		Transaction tx = null;
		try {
			teachpaperproject.setSpareTire("1");
			this.setTeachpaperretri(teachpaperretridao.findById(teachpaperretri.getThesisRetrivalId()));
			teachpaperproject.setProjectSumScore(teachpaperretri.getScore());
			teachpaperproject.setTfteachingPaperRetrievalCondition(teachpaperretri);
			teachpaperproject.setTfterm(termdao.findById(term.getTermId()));
			teachpaperproject.setDepartmentId(EntityUtil.findDepartIdByTeacherId(((Teacher)session.get("teacher")).getTeacherId(),
					teachpaperprojectdao.getSession()));
			teachpaperprojectdao.merge(teachpaperproject);
			tx = teachpaperprojectdao.getSession().beginTransaction();
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
			teachpaperprojectdao.deleteBylogic(teachpaperproject.getTeachPaperId());
			teachpaperpcedao.deleteRef(teachpaperproject);
			tx = teachpaperprojectdao.getSession().beginTransaction();
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
			JSONArray jsay = JSONArray.fromObject(teachpaperpcedao.findMember(teachpaperproject));
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
				teachpaperpcedao.updateScore(tmps[0],tmpscore, teachpaperproject);
				sumscore -= tmpscore;
			}
			if(sumscore!=0){
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("分数分配不当，或多或少");
				return;
			}
			tx = teachpaperpcedao.getSession().beginTransaction();
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
			teachpaperpcedao.quitProject(tfteacher, teachpaperproject);
			tx = teachpaperpcedao.getSession().beginTransaction();
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

	public TfteachingPaperPerformance getTeachpaperpce() {
		return teachpaperpce;
	}

	public void setTeachpaperpce(TfteachingPaperPerformance teachpaperpce) {
		this.teachpaperpce = teachpaperpce;
	}

	public TfteachingPaperProject getTeachpaperproject() {
		return teachpaperproject;
	}

	public void setTeachpaperproject(TfteachingPaperProject teachpaperproject) {
		this.teachpaperproject = teachpaperproject;
	}

	public TfteachingPaperRetrievalCondition getTeachpaperretri() {
		return teachpaperretri;
	}

	public void setTeachpaperretri(TfteachingPaperRetrievalCondition teachpaperretri) {
		this.teachpaperretri = teachpaperretri;
	}

	public Teacher getTfteacher() {
		return tfteacher;
	}

	public void setTfteacher(Teacher tfteacher) {
		this.tfteacher = tfteacher;
	}
}
