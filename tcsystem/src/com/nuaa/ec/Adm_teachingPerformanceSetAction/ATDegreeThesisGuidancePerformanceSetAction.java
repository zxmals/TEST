package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TfdegreeThesisGuidancePerformanceDAO;
import com.nuaa.ec.dao.TfdegreeThesisGuidanceRewardLevelDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfdegreeThesisGuidancePerformance;
import com.nuaa.ec.model.TfdegreeThesisGuidanceRewardLevel;
import com.opensymphony.xwork2.ActionContext;

public class ATDegreeThesisGuidancePerformanceSetAction implements RequestAware{
	/**
	 * function:删除一条记录
	 * @return void
	 */
	public void deleteRecord(){
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
	 * function：更新一条记录
	 */
	public void updateRecord(){
		System.out.println("更新的ID是："+degreeThesisGuidancePerformance.getTermId());
		Transaction tx=null;
		try{
			tx=this.degreeThesisGuidancePerformanceDAO.getSession().beginTransaction();
			/*
			 * 先取出rewardlevel的完整信息然后设置新的信息
			 */
			degreeThsisGuidanceRewardLevel=this.degreeGuidanceRewardLevelDAO.findById(degreeThsisGuidanceRewardLevel.getRewardLevelId());
			degreeThesisGuidancePerformance.setTfdegreeThesisGuidanceRewardLevel(degreeThsisGuidanceRewardLevel);
			degreeThesisGuidancePerformance.setTeacher(teacherDAO.findById(teacher.getTeacherId()));
			degreeThesisGuidancePerformance.setFinalScore(this.getScore(degreeThsisGuidanceRewardLevel.getRewardLevelId()));
			degreeThesisGuidancePerformance.setSpareTire("1");
			degreeThesisGuidancePerformance.setCheckOut("1");
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
	public String getAllRecord(){
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
			if(session.get("pageSize_ATDTG")!=null){
				pageSize_ATDTG=(Integer) session.get("pageSize_ATDTG");
			}
			this.request.put("degreeThesisGuidancePerfUnionTftermList",
					this.degreeThesisGuidancePerformanceDAO.findAllWithDivided_adm(pageIndex, pageSize_ATDTG,(String) session.get("termId_ATDTG"), (String) session.get("searchCondition_ATDTG"),isDivided));
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
			this.setOperstatus(-1);
		}
		return "success";
	}
	public String execute() throws Exception{
		return "success";
	}
	private int pageIndex = 1;
	private int pageSize_ATDTG=1;
	private int operstatus;
	private String isDivided;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private TfdegreeThesisGuidancePerformance degreeThesisGuidancePerformance;
	private Teacher teacher;
	private TeacherDAO teacherDAO=new TeacherDAO();
	private String termId_ATDTG;
	private String searchCondition_ATDTG;
	private TfdegreeThesisGuidanceRewardLevel degreeThsisGuidanceRewardLevel;
	private TfdegreeThesisGuidancePerformanceDAO degreeThesisGuidancePerformanceDAO = new TfdegreeThesisGuidancePerformanceDAO();
	private TfdegreeThesisGuidanceRewardLevelDAO degreeGuidanceRewardLevelDAO = new TfdegreeThesisGuidanceRewardLevelDAO();
	private HttpServletResponse response=ServletActionContext.getResponse();
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_ATDTG() {
		return pageSize_ATDTG;
	}

	public void setPageSize_ATDTG(int pageSize_ATDTG) {
		this.pageSize_ATDTG = pageSize_ATDTG;
		session.put("pageSize_ATDTG", pageSize_ATDTG);
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
	public String getTermId_ATDTG() {
		return termId_ATDTG;
	}
	public void setTermId_ATDTG(String termId_ATDTG) {
		this.termId_ATDTG = termId_ATDTG;
		session.put("termId_ATDTG", termId_ATDTG);
	}
	public String getSearchCondition_ATDTG() {
		return searchCondition_ATDTG;
	}
	public void setSearchCondition_ATDTG(String searchCondition_ATDTG) {
		this.searchCondition_ATDTG = searchCondition_ATDTG;
		try {
			//解决中文乱码问题
			session.put("searchCondition_ATDTG", new String(searchCondition_ATDTG.getBytes("ISO-8859-1"),"UTF-8"));
//			System.out.println(session.get("searchCondition_ATDTG"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
