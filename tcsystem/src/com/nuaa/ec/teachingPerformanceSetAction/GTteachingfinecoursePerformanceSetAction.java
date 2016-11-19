package com.nuaa.ec.teachingPerformanceSetAction;

import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.SelfUndertakeTaskDAO;
import com.nuaa.ec.dao.TffineCourseConstructionLevelDAO;
import com.nuaa.ec.dao.TffineCourseConstructionPerformanceDAO;
import com.nuaa.ec.dao.TffineCourseConstructionProjectDAO;
import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TffineCourseConstructionLevel;
import com.nuaa.ec.model.TffineCourseConstructionPerformance;
import com.nuaa.ec.model.TffineCourseConstructionProject;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class GTteachingfinecoursePerformanceSetAction implements RequestAware,
		SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Tfterm term;
	private TffineCourseConstructionPerformance finecourseperce;
	private TffineCourseConstructionLevel finecourselevel;
	private TffineCourseConstructionProject finecourseproject;
	
	private TffineCourseConstructionPerformanceDAO finecoursepercedao = new TffineCourseConstructionPerformanceDAO();
	private TffineCourseConstructionProjectDAO finecourseprojectdao = new TffineCourseConstructionProjectDAO();
	private TffineCourseConstructionLevelDAO finecourseleveldao = new TffineCourseConstructionLevelDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private TftermDAO termdao = new TftermDAO();
	private SelfUndertakeTaskDAO selfdao = new SelfUndertakeTaskDAO();
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
			request.put("finecourseli", finecourseprojectdao.findPaging((pagenum - 1)* limitrow, limitrow, EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId")));
			request.put("tftermList", termdao.findAll());
			request.put("courselevel", finecourseleveldao.findAll());
			int li = finecourseprojectdao.getRows(EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId"));
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
	
	public void addProject()throws Exception{
		Transaction tx = null;
		try {
			finecourseproject.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
			finecourseproject.setCheckOut("0");
			finecourseproject.setCourseId(pkmk.mkpk(EntityUtil.getPkColumnName(TffineCourseConstructionProject.class), EntityUtil.getTableName(TffineCourseConstructionProject.class), "TFCOURSE"));
			this.setFinecourselevel(finecourseleveldao.findById(finecourselevel.getCourseLevelId()));
			finecourseproject.setProjectSumScore(finecourselevel.getScore());
			finecourseproject.setSpareTire("1");
			finecourseproject.setTffineCourseConstructionLevel(finecourselevel);
			finecourseproject.setTfterm(termdao.findById(term.getTermId()));
//			
			finecourseperce = new TffineCourseConstructionPerformance();
			finecourseperce.setCheckOut("0");
			finecourseperce.setSelfUndertakeTask(selfdao.findByUndertakeTaskNameDim());
			finecourseperce.setSpareTire("1");
			finecourseperce.setTeacher((Teacher)session.get("teacher"));
			finecourseperce.setTffineCourseConstructionProject(finecourseproject);
			finecourseprojectdao.save(finecourseproject);
			finecoursepercedao.save(finecourseperce);
			tx = finecourseprojectdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			throw e;
		}
	}
	
	public void updateProject()throws Exception{
		Transaction tx = null;
		try {
			finecourseproject.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
			this.setFinecourselevel(finecourseleveldao.findById(finecourselevel.getCourseLevelId()));
			finecourseproject.setProjectSumScore(finecourselevel.getScore());
			finecourseproject.setSpareTire("1");
			finecourseproject.setTffineCourseConstructionLevel(finecourselevel);
			finecourseproject.setTfterm(termdao.findById(term.getTermId()));
			finecourseprojectdao.merge(finecourseproject);
			tx = finecourseprojectdao.getSession().beginTransaction();
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
			finecourseprojectdao.deleteBylogic(finecourseproject.getCourseId());
			finecoursepercedao.deleteRef(finecourseproject);
			tx = finecoursepercedao.getSession().beginTransaction();
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
			JSONArray jsay = JSONArray.fromObject(finecoursepercedao.findMember(finecourseproject));
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
				finecoursepercedao.updateScore(tmps[0],tmpscore,finecourseproject);
				sumscore -= tmpscore;
			}
			if(sumscore!=0){
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("分数分配不当，或多或少");
				return;
			}
			tx = finecoursepercedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			throw e;
		}
	}
	
	public void joinPeoject()throws Exception{
		Transaction tx = null;
		try {
			if(finecoursepercedao.checkexist(finecourseproject, (Teacher)session.get("teacher"))){
				finecourseperce = new TffineCourseConstructionPerformance();
				finecourseperce.setCheckOut("0");
				finecourseperce.setSelfUndertakeTask(selfdao.findByUndertakeTaskNameDim());
				finecourseperce.setSpareTire("1");
				finecourseperce.setTeacher((Teacher)session.get("teacher"));
				finecourseperce.setTffineCourseConstructionProject(finecourseproject);
				finecoursepercedao.save(finecourseperce);
			}else{
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("不能重复加入");
				return;
			}
			tx = finecoursepercedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			throw e;
		}
	}
	
	public String getPersonJoin()throws Exception{
		try {
			int pagenum = 0;
			int limitrow = 0;
			String limit = (String) ServletActionContext.getRequest().getParameter("limit");
			String pagenumber = (String) ServletActionContext.getRequest().getParameter("pagenum");
			pagenum = pagenumber != null?(pagenum = !"".equals(pagenumber.trim()) ? Integer.parseInt(pagenumber) : 1):1;
			limitrow = limit != null?(!"".equals(limit.trim()) ? Integer.parseInt(limit) : 5):5;
			request.put("finecoursepli", finecoursepercedao.findPaging((pagenum - 1)* limitrow, limitrow,
					EntityUtil.generateTeachingQueryCondition(term, "tffineCourseConstructionProject.tfterm.termId"),(Teacher)session.get("teacher")));
			request.put("tftermList", termdao.findAll());
			int li = finecoursepercedao.getRows(EntityUtil.generateTeachingQueryCondition(term, "tffineCourseConstructionProject.tfterm.termId"),(Teacher)session.get("teacher"));
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
	
	public void quitProject()throws Exception{
		Transaction tx = null;
		try {
			finecoursepercedao.quitProject((Teacher)session.get("teacher"), finecourseproject);
			tx = finecoursepercedao.getSession().beginTransaction();
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

	public TffineCourseConstructionPerformance getFinecourseperce() {
		return finecourseperce;
	}

	public void setFinecourseperce(
			TffineCourseConstructionPerformance finecourseperce) {
		this.finecourseperce = finecourseperce;
	}

	public TffineCourseConstructionLevel getFinecourselevel() {
		return finecourselevel;
	}

	public void setFinecourselevel(TffineCourseConstructionLevel finecourselevel) {
		this.finecourselevel = finecourselevel;
	}

	public TffineCourseConstructionProject getFinecourseproject() {
		return finecourseproject;
	}

	public void setFinecourseproject(
			TffineCourseConstructionProject finecourseproject) {
		this.finecourseproject = finecourseproject;
	}
}
