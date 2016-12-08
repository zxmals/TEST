package com.nuaa.ec.teachingPerformanceSetAction;

import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.SelfUndertakeTaskDAO;
import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.dao.TftextbookConstructionPerformanceDAO;
import com.nuaa.ec.dao.TftextbookConstructionProjectDAO;
import com.nuaa.ec.dao.TftextbookConstructionTblevelDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.model.TftextbookConstructionPerformance;
import com.nuaa.ec.model.TftextbookConstructionProject;
import com.nuaa.ec.model.TftextbookConstructionTblevel;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class GTteachingtextbookPerformanceSetAction implements RequestAware,
		SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Tfterm term;
	private TftextbookConstructionPerformance textbookperce;
	private TftextbookConstructionProject textbookproject;
	private TftextbookConstructionTblevel textbooklevel;
	
	private TftextbookConstructionPerformanceDAO textbookpercedao = new TftextbookConstructionPerformanceDAO();
	private TftextbookConstructionProjectDAO textbookprojectdao = new TftextbookConstructionProjectDAO();
	private TftextbookConstructionTblevelDAO textbookleveldao = new TftextbookConstructionTblevelDAO();
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
			request.put("textbookproli", textbookprojectdao.findPaging((pagenum - 1)* limitrow, limitrow, EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId")));
			request.put("tftermList", termdao.findAll());
			request.put("tbooklevel", textbookleveldao.findAll());
			int li = textbookprojectdao.getRows(EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId"));
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
			textbookproject.setBookId(pkmk.mkpk(EntityUtil.getPkColumnName(TftextbookConstructionProject.class), EntityUtil.getTableName(TftextbookConstructionProject.class), "TFTB"));
			textbookproject.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
			textbookproject.setCheckOut("5");
			this.setTextbooklevel(textbookleveldao.findById(textbooklevel.getTblevelId()));
			textbookproject.setProjectSumScore(textbooklevel.getScore());
			textbookproject.setSpareTire("1");
			textbookproject.setTfterm(termdao.findById(term.getTermId()));
			textbookproject.setTftextbookConstructionTblevel(textbooklevel);
			textbookproject.setDepartmentId(EntityUtil.findDepartIdByTeacherId(((Teacher)session.get("teacher")).getTeacherId(),
					textbookprojectdao.getSession()));
			
			textbookperce = new TftextbookConstructionPerformance();
			textbookperce.setCheckOut("0");
			textbookperce.setSelfUndertakeTask(selfdao.findByUndertakeTaskNameDim());
			textbookperce.setSpareTire("1");
			textbookperce.setTeacher((Teacher)session.get("teacher"));
			textbookperce.setTftextbookConstructionProject(textbookproject);
			textbookprojectdao.save(textbookproject);;
			textbookpercedao.save(textbookperce);
			tx = textbookprojectdao.getSession().beginTransaction();
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
			textbookproject.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
			this.setTextbooklevel(textbookleveldao.findById(textbooklevel.getTblevelId()));
			textbookproject.setProjectSumScore(textbooklevel.getScore());
			textbookproject.setSpareTire("1");
			textbookproject.setTfterm(termdao.findById(term.getTermId()));
			textbookproject.setTftextbookConstructionTblevel(textbooklevel);
			textbookprojectdao.merge(textbookproject);
			tx = textbookprojectdao.getSession().beginTransaction();
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
			textbookprojectdao.deleteBylogic(textbookproject.getBookId());
			textbookpercedao.deleteRef(textbookproject);
			tx = textbookpercedao.getSession().beginTransaction();
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
			JSONArray jsay = JSONArray.fromObject(textbookpercedao.findMember(textbookproject));
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
				textbookpercedao.updateScore(tmps[0],tmpscore,textbookproject);
				sumscore -= tmpscore;
			}
			if(sumscore!=0){
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("分数分配不当，或多或少");
				return;
			}
			tx = textbookpercedao.getSession().beginTransaction();
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
			if(textbookpercedao.checkexist(textbookproject, (Teacher)session.get("teacher"))){
				textbookperce = new TftextbookConstructionPerformance();
				textbookperce.setCheckOut("0");
				textbookperce.setSelfUndertakeTask(selfdao.findByUndertakeTaskNameOJoin());
				textbookperce.setSpareTire("1");
				textbookperce.setTeacher((Teacher)session.get("teacher"));
				textbookperce.setTftextbookConstructionProject(textbookproject);
				textbookpercedao.save(textbookperce);
			}else{
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("不能重复加入");
				return;
			}
			tx = textbookpercedao.getSession().beginTransaction();
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
			request.put("textbookprecli", textbookpercedao.findPaging((pagenum - 1)* limitrow, limitrow,
					EntityUtil.generateTeachingQueryCondition(term, "tftextbookConstructionProject.tfterm.termId"),(Teacher)session.get("teacher")));
			request.put("tftermList", termdao.findAll());
			int li = textbookpercedao.getRows(EntityUtil.generateTeachingQueryCondition(term, "tftextbookConstructionProject.tfterm.termId"),(Teacher)session.get("teacher"));
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
			textbookpercedao.quitProject((Teacher)session.get("teacher"), textbookproject);
			tx = textbookpercedao.getSession().beginTransaction();
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

	public TftextbookConstructionPerformance getTextbookperce() {
		return textbookperce;
	}

	public void setTextbookperce(TftextbookConstructionPerformance textbookperce) {
		this.textbookperce = textbookperce;
	}

	public TftextbookConstructionProject getTextbookproject() {
		return textbookproject;
	}

	public void setTextbookproject(TftextbookConstructionProject textbookproject) {
		this.textbookproject = textbookproject;
	}

	public TftextbookConstructionTblevel getTextbooklevel() {
		return textbooklevel;
	}

	public void setTextbooklevel(TftextbookConstructionTblevel textbooklevel) {
		this.textbooklevel = textbooklevel;
	}
}
