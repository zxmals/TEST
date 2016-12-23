package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.util.Map;


import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfteachingRearchEvaluationDAO;
import com.nuaa.ec.dao.TfteachingRearchFundlevelDAO;
import com.nuaa.ec.dao.TfteachingRearchPerformanceDAO;
import com.nuaa.ec.dao.TfteachingRearchProjectDAO;
import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingRearchEvaluation;
import com.nuaa.ec.model.TfteachingRearchFundlevel;
import com.nuaa.ec.model.TfteachingRearchPerformance;
import com.nuaa.ec.model.TfteachingRearchProject;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.utils.EntityUtil;


public class ATteachingresearchPerformanceSetAction implements RequestAware,
		SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Tfterm term;
	private Teacher tfteacher;
	private TfteachingRearchEvaluation teachreachevalute;
	private TfteachingRearchFundlevel teachreachfundlevel;
	private TfteachingRearchPerformance teachreachperce;
	private TfteachingRearchProject teachreachprojec;
	
	private TfteachingRearchEvaluationDAO teachreachevalutedao = new TfteachingRearchEvaluationDAO();
	private TfteachingRearchFundlevelDAO teachreachfundleveldao = new TfteachingRearchFundlevelDAO();
	private TfteachingRearchPerformanceDAO teachreachpercedao = new TfteachingRearchPerformanceDAO();
	private TfteachingRearchProjectDAO teachreachprojecdao = new TfteachingRearchProjectDAO();
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
			request.put("teachresearchli", teachreachprojecdao.findPaging((pagenum - 1)* limitrow, limitrow, EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId")));
			request.put("tftermList", termdao.findAll());
			request.put("passfundli", teachreachfundleveldao.findAll());
			request.put("evaluateli", teachreachevalutedao.findAll());
			int li = teachreachprojecdao.getRows(EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId"));
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
			teachreachprojec.setSpareTire("1");
			this.setTeachreachevalute(teachreachevalutedao.findById(teachreachevalute.getEvaluationId()));
			this.setTeachreachfundlevel(teachreachfundleveldao.findById(teachreachfundlevel.getFundLevelId()));
			teachreachprojec.setTfteachingRearchEvaluation(teachreachevalute);
			teachreachprojec.setTfteachingRearchFundlevel(teachreachfundlevel);
			teachreachprojec.setProjetScore(teachreachevalute.getRatio()*teachreachfundlevel.getScore());
			teachreachprojec.setTfterm(termdao.findById(term.getTermId()));
			teachreachprojec.setDepartmentId(EntityUtil.findDepartIdByTeacherId(teachreachprojec.getChargePersonId(),
					teachreachprojecdao.getSession()));
			teachreachprojecdao.merge(teachreachprojec);
			tx = teachreachprojecdao.getSession().beginTransaction();
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
			teachreachprojecdao.deleteBylogic(teachreachprojec.getProjectId());
			teachreachpercedao.deleteRef(teachreachprojec);
			tx = teachreachpercedao.getSession().beginTransaction();
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
			JSONArray jsay = JSONArray.fromObject(teachreachpercedao.findMember(teachreachprojec));
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
				teachreachpercedao.updateScore(tmps[0],tmpscore, teachreachprojec);
				sumscore -= tmpscore;
			}
			if(sumscore!=0){
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("分数分配不当，或多或少");
				return;
			}
			tx = teachreachpercedao.getSession().beginTransaction();
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
			teachreachpercedao.quitProject(tfteacher, teachreachprojec);
			tx = teachreachpercedao.getSession().beginTransaction();
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

	public TfteachingRearchEvaluation getTeachreachevalute() {
		return teachreachevalute;
	}

	public void setTeachreachevalute(TfteachingRearchEvaluation teachreachevalute) {
		this.teachreachevalute = teachreachevalute;
	}

	public TfteachingRearchFundlevel getTeachreachfundlevel() {
		return teachreachfundlevel;
	}

	public void setTeachreachfundlevel(TfteachingRearchFundlevel teachreachfundlevel) {
		this.teachreachfundlevel = teachreachfundlevel;
	}

	public TfteachingRearchPerformance getTeachreachperce() {
		return teachreachperce;
	}

	public void setTeachreachperce(TfteachingRearchPerformance teachreachperce) {
		this.teachreachperce = teachreachperce;
	}

	public TfteachingRearchProject getTeachreachprojec() {
		return teachreachprojec;
	}

	public void setTeachreachprojec(TfteachingRearchProject teachreachprojec) {
		this.teachreachprojec = teachreachprojec;
	}

	public Teacher getTfteacher() {
		return tfteacher;
	}

	public void setTfteacher(Teacher tfteacher) {
		this.tfteacher = tfteacher;
	}

}
