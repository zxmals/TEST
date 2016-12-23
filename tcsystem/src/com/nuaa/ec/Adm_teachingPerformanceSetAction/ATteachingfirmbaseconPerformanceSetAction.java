package com.nuaa.ec.Adm_teachingPerformanceSetAction;
//企业工作站与联合培养基地建设
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.SelfUndertakeTaskDAO;
import com.nuaa.ec.dao.TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO;
import com.nuaa.ec.dao.TfenterpriseWorkstationTrainingBaseConstructionProjectDAO;
import com.nuaa.ec.dao.TfenterpriseWorkstationTrainingbaseConstructionLevelDAO;
import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance;
import com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionProject;
import com.nuaa.ec.model.TfenterpriseWorkstationTrainingbaseConstructionLevel;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class ATteachingfirmbaseconPerformanceSetAction implements RequestAware,
		SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private Tfterm term;
	private Teacher tfteacher;
	private TfenterpriseWorkstationTrainingBaseConstructionPerformance firmtrainperce;
	private TfenterpriseWorkstationTrainingBaseConstructionProject firmtrainproject;
	private TfenterpriseWorkstationTrainingbaseConstructionLevel firmtrainlevel;

	private TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO firmtrainpercedao = new TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO();
	private TfenterpriseWorkstationTrainingBaseConstructionProjectDAO firmtrainprojectdao = new TfenterpriseWorkstationTrainingBaseConstructionProjectDAO();
	private TfenterpriseWorkstationTrainingbaseConstructionLevelDAO firmtrainleveldao = new TfenterpriseWorkstationTrainingbaseConstructionLevelDAO();
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
			request.put("firmtrainli", firmtrainprojectdao.findPaging((pagenum - 1)* limitrow, limitrow, EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId")));
			request.put("tftermList", termdao.findAll());
			request.put("projectlevel", firmtrainleveldao.findAll());
			int li = firmtrainprojectdao.getRows(EntityUtil.generateTeachingQueryCondition(term, "tfterm.termId"));
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
			this.setFirmtrainlevel(firmtrainleveldao.findById(firmtrainlevel.getTrainingConstruLevelId()));
			double ratio = 1.0;
			ratio  = "".equals(firmtrainproject.getQuantityUnit().trim())?1.0:Double.parseDouble(firmtrainproject.getQuantityUnit().trim());
			firmtrainproject.setProjectSumScore(firmtrainlevel.getScore()*ratio);
			firmtrainproject.setSpareTire("1");
			firmtrainproject.setTfenterpriseWorkstationTrainingbaseConstructionLevel(firmtrainlevel);
			firmtrainproject.setTfterm(termdao.findById(term.getTermId()));
			firmtrainproject.setDepartmentId(EntityUtil.findDepartIdByTeacherId(firmtrainproject.getChargePersonId(),
					firmtrainprojectdao.getSession()));
			firmtrainprojectdao.merge(firmtrainproject);
			tx = firmtrainprojectdao.getSession().beginTransaction();
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
			firmtrainprojectdao.deleteBylogic(firmtrainproject.getProjectId());
			firmtrainpercedao.deleteRef(firmtrainproject);
			tx = firmtrainpercedao.getSession().beginTransaction();
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
			JSONArray jsay = JSONArray.fromObject(firmtrainpercedao.findMember(firmtrainproject));
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
				firmtrainpercedao.updateScore(tmps[0],tmpscore,firmtrainproject);
				sumscore -= tmpscore;
			}
			if(sumscore!=0){
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("分数分配不当，或多或少");
				return;
			}
			tx = firmtrainpercedao.getSession().beginTransaction();
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
			firmtrainpercedao.quitProject(tfteacher, firmtrainproject);
			tx = firmtrainpercedao.getSession().beginTransaction();
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

	public TfenterpriseWorkstationTrainingBaseConstructionPerformance getFirmtrainperce() {
		return firmtrainperce;
	}

	public void setFirmtrainperce(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance firmtrainperce) {
		this.firmtrainperce = firmtrainperce;
	}

	public TfenterpriseWorkstationTrainingBaseConstructionProject getFirmtrainproject() {
		return firmtrainproject;
	}

	public void setFirmtrainproject(
			TfenterpriseWorkstationTrainingBaseConstructionProject firmtrainproject) {
		this.firmtrainproject = firmtrainproject;
	}

	public TfenterpriseWorkstationTrainingbaseConstructionLevel getFirmtrainlevel() {
		return firmtrainlevel;
	}

	public void setFirmtrainlevel(
			TfenterpriseWorkstationTrainingbaseConstructionLevel firmtrainlevel) {
		this.firmtrainlevel = firmtrainlevel;
	}

	public Teacher getTfteacher() {
		return tfteacher;
	}

	public void setTfteacher(Teacher tfteacher) {
		this.tfteacher = tfteacher;
	}
}
