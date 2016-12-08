package com.nuaa.ec.teachingPerformanceSetAction;
//专业建设项目申报
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.SelfUndertakeTaskDAO;
import com.nuaa.ec.dao.TfprofessionalProjectDeclareLevelDAO;
import com.nuaa.ec.dao.TfprofessionalProjectDeclarePerformanceDAO;
import com.nuaa.ec.dao.TfprofessionalProjectDeclareProjectDAO;
import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfprofessionalProjectDeclareLevel;
import com.nuaa.ec.model.TfprofessionalProjectDeclarePerformance;
import com.nuaa.ec.model.TfprofessionalProjectDeclareProject;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class GTteachingprofeprojePerformanceSetAction implements RequestAware,
		SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Tfterm term;
	private TfprofessionalProjectDeclarePerformance profeprojeprece;
	private TfprofessionalProjectDeclareProject profeprojeproject;
	private TfprofessionalProjectDeclareLevel profeprojelevel;
	
	private TfprofessionalProjectDeclarePerformanceDAO profeprojeprecedao = new TfprofessionalProjectDeclarePerformanceDAO();
	private TfprofessionalProjectDeclareProjectDAO profeprojeprojectdao = new TfprofessionalProjectDeclareProjectDAO();
	private TfprofessionalProjectDeclareLevelDAO profeprojeleveldao = new TfprofessionalProjectDeclareLevelDAO();
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
			request.put("profeprojeporjeli", profeprojeprojectdao.findPaging((pagenum - 1)* limitrow, limitrow, EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId")));
			request.put("tftermList", termdao.findAll());
			request.put("projectlevel", profeprojeleveldao.findAll());
			int li = profeprojeprojectdao.getRows(EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId"));
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
			profeprojeproject.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
			profeprojeproject.setCheckOut("5");
			profeprojeproject.setProjectId(pkmk.mkpk(EntityUtil.getPkColumnName(TfprofessionalProjectDeclareProject.class), EntityUtil.getTableName(TfprofessionalProjectDeclareProject.class), "TFPFPJ"));
			this.setProfeprojelevel(profeprojeleveldao.findById(profeprojelevel.getProjectLevelId()));
			profeprojeproject.setProjectSumScore(profeprojelevel.getScore());
			profeprojeproject.setSpareTire("1");
			profeprojeproject.setTfprofessionalProjectDeclareLevel(profeprojelevel);
			profeprojeproject.setTfterm(termdao.findById(term.getTermId()));
			profeprojeproject.setDepartmentId(EntityUtil.findDepartIdByTeacherId(((Teacher)session.get("teacher")).getTeacherId(),
					profeprojeprojectdao.getSession()));
//
			profeprojeprece = new TfprofessionalProjectDeclarePerformance();
			profeprojeprece.setCheckOut("0");
			profeprojeprece.setSelfUndertakeTask(selfdao.findByUndertakeTaskNameDim());
			profeprojeprece.setSingleScore(0.0);
			profeprojeprece.setSpareTire("1");
			profeprojeprece.setTeacher((Teacher)session.get("teacher"));
			profeprojeprece.setTfprofessionalProjectDeclareProject(profeprojeproject);
			profeprojeprojectdao.save(profeprojeproject);
			profeprojeprecedao.save(profeprojeprece);
			tx = profeprojeprojectdao.getSession().beginTransaction();
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
			profeprojeproject.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
			this.setProfeprojelevel(profeprojeleveldao.findById(profeprojelevel.getProjectLevelId()));
			profeprojeproject.setProjectSumScore(profeprojelevel.getScore());
			profeprojeproject.setSpareTire("1");
			profeprojeproject.setTfprofessionalProjectDeclareLevel(profeprojelevel);
			profeprojeproject.setTfterm(termdao.findById(term.getTermId()));
			profeprojeprojectdao.merge(profeprojeproject);
			tx = profeprojeprojectdao.getSession().beginTransaction();
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
			profeprojeprojectdao.deleteBylogic(profeprojeproject.getProjectId());
			profeprojeprecedao.deleteRef(profeprojeproject);
			tx = profeprojeprecedao.getSession().beginTransaction();
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
			JSONArray jsay = JSONArray.fromObject(profeprojeprecedao.findMember(profeprojeproject));
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
				profeprojeprecedao.updateScore(tmps[0],tmpscore,profeprojeproject);
				sumscore -= tmpscore;
			}
			if(sumscore!=0){
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("分数分配不当，或多或少");
				return;
			}
			tx = profeprojeprecedao.getSession().beginTransaction();
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
			if(profeprojeprecedao.checkexist(profeprojeproject, (Teacher)session.get("teacher"))){
				profeprojeprece = new TfprofessionalProjectDeclarePerformance();
				profeprojeprece.setCheckOut("0");
				profeprojeprece.setSelfUndertakeTask(selfdao.findByUndertakeTaskNameOJoin());
				profeprojeprece.setSingleScore(0.0);
				profeprojeprece.setSpareTire("1");
				profeprojeprece.setTeacher((Teacher)session.get("teacher"));
				profeprojeprece.setTfprofessionalProjectDeclareProject(profeprojeproject);
				profeprojeprecedao.save(profeprojeprece);
			}else{
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("不能重复加入");
				return;
			}
			tx = profeprojeprecedao.getSession().beginTransaction();
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
			request.put("profeprojepli", profeprojeprecedao.findPaging((pagenum - 1)* limitrow, limitrow,
					EntityUtil.generateTeachingQueryCondition(term, "tfprofessionalProjectDeclareProject.tfterm.termId"),(Teacher)session.get("teacher")));
			request.put("tftermList", termdao.findAll());
			int li = profeprojeprecedao.getRows(EntityUtil.generateTeachingQueryCondition(term, "tfprofessionalProjectDeclareProject.tfterm.termId"),(Teacher)session.get("teacher"));
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
			profeprojeprecedao.quitProject((Teacher)session.get("teacher"), profeprojeproject);
			tx = profeprojeprecedao.getSession().beginTransaction();
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

	public TfprofessionalProjectDeclarePerformance getProfeprojeprece() {
		return profeprojeprece;
	}

	public void setProfeprojeprece(
			TfprofessionalProjectDeclarePerformance profeprojeprece) {
		this.profeprojeprece = profeprojeprece;
	}

	public TfprofessionalProjectDeclareProject getProfeprojeproject() {
		return profeprojeproject;
	}

	public void setProfeprojeproject(
			TfprofessionalProjectDeclareProject profeprojeproject) {
		this.profeprojeproject = profeprojeproject;
	}

	public TfprofessionalProjectDeclareLevel getProfeprojelevel() {
		return profeprojelevel;
	}

	public void setProfeprojelevel(TfprofessionalProjectDeclareLevel profeprojelevel) {
		this.profeprojelevel = profeprojelevel;
	}
}
