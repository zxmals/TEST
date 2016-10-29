package com.nuaa.ec.teachingPerformanceSetAction;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfdegreeThesisGuidancePerformanceDAO;
import com.nuaa.ec.dao.TfdegreeThesisGuidanceRewardLevelDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfdegreeThesisGuidancePerformance;
import com.nuaa.ec.model.TfdegreeThesisGuidanceRewardLevel;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionContext;

public class GTDegreeThesisGuidancePerformanceSetAction implements RequestAware {
	/**
	 * function：更新一条记录
	 */
	public void updateDegreeThesisGuidanceRecord(){
		System.out.println("更新的ID是："+degreeThesisGuidancePerformance.getTermId());
		Transaction tx=null;
		try{
			tx=this.degreeThesisGuidancePerformanceDAO.getSession().beginTransaction();
			/*
			 * 先取出rewardlevel的完整信息然后设置新的信息
			 */
			degreeThsisGuidanceRewardLevel=this.degreeGuidanceRewardLevelDAO.findById(degreeThsisGuidanceRewardLevel.getRewardLevelId());
			degreeThesisGuidancePerformance.setTfdegreeThesisGuidanceRewardLevel(degreeThsisGuidanceRewardLevel);
			degreeThesisGuidancePerformance.setFinalScore(this.getScore(degreeThsisGuidanceRewardLevel.getRewardLevelId()));
			degreeThesisGuidancePerformance.setTeacher((Teacher) session.get("teacher"));
			degreeThesisGuidancePerformance.setSpareTire("1");
			degreeThesisGuidancePerformance.setCheckOut("0");
			this.degreeThesisGuidancePerformanceDAO.merge(degreeThesisGuidancePerformance);
			tx.commit();
			this.setOperstatus(1);
			this.response.getWriter().write("succ");
		}catch(Exception ex){
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
			try {
				this.response.getWriter().write("error");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * function:删除一条记录
	 * @return void
	 */
	public void deleteDegreeThesisGuidanceRecord(){
		Transaction tx=null;
		try{
			tx=this.degreeThesisGuidancePerformanceDAO.getSession().beginTransaction();
			degreeThesisGuidancePerformance=this.degreeThesisGuidancePerformanceDAO.findById(degreeThesisGuidancePerformance.getDegreeThesisId());
			degreeThesisGuidancePerformance.setSpareTire("0");
			this.degreeThesisGuidancePerformanceDAO.getSession().merge(degreeThesisGuidancePerformance);
			tx.commit();
			this.setOperstatus(1);
			ServletActionContext.getResponse().getWriter().write("succ");
		}catch(Exception ex){
			ex.printStackTrace();
			this.setOperstatus(-1);
			try {
				ServletActionContext.getResponse().getWriter().write("error");
			} catch (IOException e) {
				e.printStackTrace();
			}
			tx.rollback();
		}
	}
	/**
	 * function:添加学术论文指导绩效记录
	 * 
	 * @return StringFlag
	 */
	public void insertDegreeThesisGuidanceRecord() {
		System.out.println("termId:"+degreeThesisGuidancePerformance.getTermId());
		Transaction tx = null;
		try {
			degreeThesisGuidancePerformance.setDegreeThesisId(pkmk.mkpk(
					"Degree_thesisID", "TFDegreeThesisGuidance_Performance",
					"DT"));
			degreeThesisGuidancePerformance.setFinalScore(this.getScore(this.degreeThsisGuidanceRewardLevel.getRewardLevelId().trim()));
			degreeThesisGuidancePerformance.setSpareTire("1");
			degreeThesisGuidancePerformance.setCheckOut("0");
			degreeThesisGuidancePerformance.setTeacher((Teacher) session.get("teacher"));
			degreeThesisGuidancePerformance
					.setTfdegreeThesisGuidanceRewardLevel(this.degreeGuidanceRewardLevelDAO
							.findById(degreeThsisGuidanceRewardLevel
									.getRewardLevelId()));
			this.degreeThesisGuidancePerformanceDAO
					.save(degreeThesisGuidancePerformance);
			tx = this.degreeThesisGuidancePerformanceDAO.getSession()
					.beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			this.setOperstatus(-1);
			try {
				ServletActionContext.getResponse().getWriter().write("error");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * function:获得当前教师的所有的学位论文指导绩效记录
	 * 
	 * @return StringFlag
	 */
	public String getDegreeThesisGuidanceRecord() {
		Transaction tx = null;
		boolean isDivided=false;
		if(this.isDivided!=null && this.isDivided.length()!=0 && this.isDivided.equals("true")){
			isDivided=true;
		}
		try {
			tx = this.degreeThesisGuidancePerformanceDAO.getSession()
					.beginTransaction();
			/**
			 * 第一次进来的时候pageSize为空
			 */
			if(session.get("pageSize_GTDTG")!=null){
				pageSize_GTDTG=(Integer) session.get("pageSize_GTDTG");
			}
			this.request.put("degreeThesisGuidancePerfList",
					this.degreeThesisGuidancePerformanceDAO.findAllWithDivided(pageIndex, pageSize_GTDTG,(String) session.get("termId_GTDTG"), isDivided));
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			this.setOperstatus(-1);
		}
		return "success";
	}
	public double getScore(String rewardLevel){
		double score=0;
		if(rewardLevel.equals("TFDR1")){
			score=500;
		}else if(rewardLevel.equals("TFDR2")){
			score=400;
		}else if(rewardLevel.equals("TFDR3")){
			score=300;
		}else if(rewardLevel.equals("TFDR4")){
			score=200;
		}else if(rewardLevel.equals("TFDR5")){
			score=100;
		}else{
			score=50;
		}
			
		return score;
	}
	public String execute() throws Exception {
		return "success";
	}

	private int pageIndex = 1;
	private int pageSize_GTDTG=1;
	private int operstatus;
	private String isDivided;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private TfdegreeThesisGuidancePerformance degreeThesisGuidancePerformance;
	private Teacher teacher;
	private String termId_GTDTG;
	private TfdegreeThesisGuidanceRewardLevel degreeThsisGuidanceRewardLevel;
	private TfdegreeThesisGuidancePerformanceDAO degreeThesisGuidancePerformanceDAO = new TfdegreeThesisGuidancePerformanceDAO();
	private TfdegreeThesisGuidanceRewardLevelDAO degreeGuidanceRewardLevelDAO = new TfdegreeThesisGuidanceRewardLevelDAO();
	private HttpServletResponse response=ServletActionContext.getResponse();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private Teacher teacherHaveLogin;
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_GTDTG() {
		return pageSize_GTDTG;
	}

	public void setPageSize_GTDTG(int pageSize_GTDTG) {
		this.pageSize_GTDTG = pageSize_GTDTG;
		session.put("pageSize_GTDTG", pageSize_GTDTG);
	}


	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	public TfdegreeThesisGuidancePerformance getDegreeThesisGuidancePerformance() {
		return degreeThesisGuidancePerformance;
	}

	public void setDegreeThesisGuidancePerformance(
			TfdegreeThesisGuidancePerformance degreeThesisGuidancePerformance) {
		this.degreeThesisGuidancePerformance = degreeThesisGuidancePerformance;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public TfdegreeThesisGuidanceRewardLevel getDegreeThsisGuidanceRewardLevel() {
		return degreeThsisGuidanceRewardLevel;
	}

	public void setDegreeThsisGuidanceRewardLevel(
			TfdegreeThesisGuidanceRewardLevel degreeThsisGuidanceRewardLevel) {
		this.degreeThsisGuidanceRewardLevel = degreeThsisGuidanceRewardLevel;
	}

	public String getIsDivided() {
		return isDivided;
	}

	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}
	public Teacher getTeacherHaveLogin() {
		this.teacherHaveLogin= (Teacher) this.session.get("teacher");
		return teacherHaveLogin;
	}
	public String getTermId_GTDTG() {
		return termId_GTDTG;
	}
	public void setTermId_GTDTG(String termId_GTDTG) {
		this.termId_GTDTG = termId_GTDTG;
		session.put("termId_GTDTG", termId_GTDTG);
	}
}
