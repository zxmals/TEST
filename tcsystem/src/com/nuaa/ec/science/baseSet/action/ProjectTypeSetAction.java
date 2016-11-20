package com.nuaa.ec.science.baseSet.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.ProjectTypeDAO;
import com.nuaa.ec.dao.ScientificResearchProjectScoreDAO;
import com.nuaa.ec.dao.SubModularDAO;
import com.nuaa.ec.model.ProjectType;
import com.nuaa.ec.model.ScientificResearchProjectScore;
import com.nuaa.ec.model.SubModular;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionSupport;

public class ProjectTypeSetAction extends ActionSupport implements RequestAware{

	public SubModular getSubModular() {
		return subModular;
	}

	public void setSubModular(SubModular subModular) {
		this.subModular = subModular;
	}

	public ScientificResearchProjectScore getSRPScore() {
		return SRPScore;
	}

	public void setSRPScore(ScientificResearchProjectScore sRPScore) {
		SRPScore = sRPScore;
	}

	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	public ProjectType getProType() {
		return proType;
	}

	public void setProType(ProjectType proType) {
		this.proType = proType;
	}

	public List<ScientificResearchProjectScore> getSRPScoreList() {
		return SRPScoreList;
	}

	public void setSRPScoreList(
			List<ScientificResearchProjectScore> sRPScoreList) {
		SRPScoreList = sRPScoreList;
	}

	public List<ProjectType> getProTypeList() {
		return proTypeList;
	}

	public void setProTypeList(List<ProjectType> proTypeList) {
		this.proTypeList = proTypeList;
	}

	public String getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(String projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getProjectTpName() {
		return projectTpName;
	}

	public void setProjectTpName(String projectTpName) {
		this.projectTpName = projectTpName;
	}

	public String getSpareTire() {
		return spareTire;
	}

	public void setSpareTire(String spareTire) {
		this.spareTire = spareTire;
	}

	public String execute() {
		return "success";
	}
	/**
	 * 获取所有的科研项目的设置信息
	 * @return
	 */
	public String viewScienResearchProjectType() {
		this.request.put("projectType", proTypeDAO.findAll());
		return "success";
	}

	/**
	 * 删除一条科研项目设置的记录
	 * 
	 * @throws Exception
	 */
	public void delProjectType() throws Exception {
		this.getProType().setSpareTire("0");
		Transaction tx = null;
		try {
			this.proTypeDAO.merge(this.proType);
			tx = this.proTypeDAO.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}

	/**
	 * 更新一条科研项目名称
	 */
	public void updateProjectType() throws Exception {
		proType.setSpareTire("1");
		Transaction tx = null;
		try {
			proTypeDAO.merge(proType);
			tx = proTypeDAO.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}

	/**
	 * 添加一条科研项目设置信息
	 */
	public String addProjectType() throws Exception {
		proType.setProjectTypeId(pkmk.mkpk("ProjectTypeID", "ProjectType", "srpt"));
		proType.setSpareTire("1");
		Transaction tx = null;
		try {
			proTypeDAO.save(proType);
			tx = proTypeDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			this.viewScienResearchProjectType();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";		
	}
	/**
	 * 获取所有的科研项目 "评分"信息
	 * @return
	 */
	public String viewScienResearchProjectScore() {
//		this.setSRPScoreList(this.proTypeDAO.getSRPScoreList());
		this.request.put("ScienScore", this.SRPScoreDAO.findAll());
		/*
		 * 加上科研项目种类的设置信息以及子模块的信息
		 * 是为了添加科研项目评分信息的时候能够给用户提供科研项目种类有哪些，以及子模块有哪些
		 */
		this.request.put("proTypeList", this.proTypeDAO.findAll());
		this.request.put("subModularList", this.subModularDAO.findAll());
		return "success";
	}
	/**
	 * 删除一条科研项目评分信息
	 */
	public void delSRPScore()throws Exception{
		SRPScore.setSpareTire("0");
		SRPScore.setSubModular(this.getSubModular());
		SRPScore.setProjectType(this.getProType());
		Transaction tx = null;
		try {
			SRPScoreDAO.merge(SRPScore);
			tx = SRPScoreDAO.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	/**
	 * 更新一条科研项目评分信息
	 */
	public void updateSRPScore()throws Exception{
		SRPScore.setSpareTire("1");
		SRPScore.setSubModular(subModular);
		SRPScore.setProjectType(proType);
//		System.out.println("subID"+subModular.getSubModularId());
//		System.out.println("proID"+proType.getProjectTypeId());
		Transaction tx = null;
		try {
			SRPScoreDAO.merge(SRPScore);
			tx = SRPScoreDAO.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	public String addSRPScore()throws Exception{
		SRPScore.setSrprojectScoreId(pkmk.mkpk("SRProjectScoreID", "ScientificResearchProjectScore", "srps"));
		SRPScore.setSpareTire("1");
		SRPScore.setProjectType(proTypeDAO.findById(proType.getProjectTypeId()));
//		System.out.println("proTypeId:"+proType.getProjectTypeId());
//		System.out.println("SRPScoreTypeId:"+SRPScore.getSrprojectScoreId());
		Transaction tx = null;
		try {
			SRPScoreDAO.save(SRPScore);
			tx = SRPScoreDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			this.viewScienResearchProjectScore();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";	
	}
	private String projectTypeId;
	private String projectTpName;
	private String spareTire;
	private ProjectType proType;
	private ScientificResearchProjectScore SRPScore;
	private SubModular subModular;
	private List<ProjectType> proTypeList = null;
	private List<ScientificResearchProjectScore> SRPScoreList = null;
	private Map<String, Object> request = null;
	private ProjectTypeDAO proTypeDAO = new ProjectTypeDAO();
	private SubModularDAO subModularDAO=new SubModularDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private int operstatus;
	private ScientificResearchProjectScoreDAO SRPScoreDAO=new ScientificResearchProjectScoreDAO();
	
}
