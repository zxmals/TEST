package com.nuaa.ec.teachingPerformanceSetAction;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfundergraduateTutorGuidanceCacheDAO;
import com.nuaa.ec.dao.TfundergraduateTutorGuidancePerformanceDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfundergraduateTutorGuidanceCache;
import com.nuaa.ec.model.TfundergraduateTutorGuidancePerformance;
import com.nuaa.ec.utils.MergeTermToYear;
import com.opensymphony.xwork2.ActionContext;

public class GTUndergraduateTutorGuidancePerformanceSetAction implements RequestAware{
	/**
	 * 删除一条本科生导师指导绩效记录
	 */
	public void deleteRecord(){
		Transaction tx=null;
		try{
			undergraduateTutorGuidancePerformance=this.undergraduateTutorGuidancePerformanceDAO.findById(undergraduateTutorGuidancePerformance.getUpid());
			undergraduateTutorGuidancePerformance.setSpareTire("0");
			this.undergraduateTutorGuidancePerformanceDAO.merge(undergraduateTutorGuidancePerformance);
			tx=this.undergraduateTutorGuidancePerformanceDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			this.response.getWriter().write("succ");
		}catch(Exception ex){
			ex.printStackTrace();
			this.setOperstatus(-1);
			try {
				this.response.getWriter().write("error");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 更新一条本科生导师指导绩效记录
	 */
	public void updateRecord(){
		Transaction tx=null;
		try{
			/*
			 *获得[TFUndergraduateTutorGuidance_Cache] 
			 */
			undergraduateTutorGuidanceCache=this.undergraduateTutorGuidanceCacheDAO.findById("TFstuguide1");
			/*
			 *设置TFUndergraduateTutorGuidance_Cache
			 */
			undergraduateTutorGuidancePerformance.setTfundergraduateTutorGuidanceCache(undergraduateTutorGuidanceCache);
			/*
			 * 设置当前教师
			 */
			undergraduateTutorGuidancePerformance.setTeacher((Teacher) session.get("teacher"));
			/*
			 * 设置学期
			 */
			undergraduateTutorGuidancePerformance.setTermId(MergeTermToYear.getTermAfterMerge(undergraduateTutorGuidancePerformance.getTermId().trim()));
			/*
			 * 设置其他信息
			 */
			undergraduateTutorGuidancePerformance.setYears(1.0);
			undergraduateTutorGuidancePerformance.setCheckOut("0");
			undergraduateTutorGuidancePerformance.setSpareTire("1");
			undergraduateTutorGuidancePerformance.setYearceiling(20);
			undergraduateTutorGuidancePerformance.setFinalScore((double) this.getScore(undergraduateTutorGuidancePerformance));
			this.undergraduateTutorGuidancePerformanceDAO.merge(undergraduateTutorGuidancePerformance);
			tx=this.undergraduateTutorGuidancePerformanceDAO.getSession().beginTransaction();
			tx.commit();
			this.response.getWriter().write("succ");
		}catch(Exception ex){
			ex.printStackTrace();
			this.setOperstatus(-1);
			try {
				this.response.getWriter().write("error");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 插入一条本科生导师指导绩效
	 */
	public void insertRecord(){
		Transaction tx=null;
		try{
			/*
			 *获得[TFUndergraduateTutorGuidance_Cache] 
			 */
			undergraduateTutorGuidanceCache=this.undergraduateTutorGuidanceCacheDAO.findById("TFstuguide1");
			/*
			 *设置TFUndergraduateTutorGuidance_Cache
			 */
			undergraduateTutorGuidancePerformance.setTfundergraduateTutorGuidanceCache(undergraduateTutorGuidanceCache);
			/*
			 * 设置当前教师
			 */
			undergraduateTutorGuidancePerformance.setTeacher((Teacher) session.get("teacher"));
			/*
			 * 设置学期
			 */
			undergraduateTutorGuidancePerformance.setTermId(MergeTermToYear.getTermAfterMerge(undergraduateTutorGuidancePerformance.getTermId().trim()));
			/*
			 * 设置其他信息
			 */
			undergraduateTutorGuidancePerformance.setYears(1.0);
			undergraduateTutorGuidancePerformance.setCheckOut("0");
			undergraduateTutorGuidancePerformance.setSpareTire("1");
			undergraduateTutorGuidancePerformance.setYearceiling(20);
			undergraduateTutorGuidancePerformance.setFinalScore((double) this.getScore(undergraduateTutorGuidancePerformance));
			this.undergraduateTutorGuidancePerformanceDAO.save(undergraduateTutorGuidancePerformance);
			tx=this.undergraduateTutorGuidancePerformanceDAO.getSession().beginTransaction();
			tx.commit();
			this.response.getWriter().write("succ");
		}catch(Exception ex){
			ex.printStackTrace();
			this.setOperstatus(-1);
			try {
				this.response.getWriter().write("error");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public float getScore(TfundergraduateTutorGuidancePerformance undergraduateTutorGuidPerf){
		float score=0f;
		score=undergraduateTutorGuidPerf.getStudentQuantity()*2;
		if(score>20){
			score=20f;
		}
		return score;
	}
	/**
	 * 获取当前用户的符合条件的所有记录
	 * @return
	 */
	public String getAllRecord() {
		Transaction tx = null;
		boolean isDivided = false;
		/**
		 * 判断是否是分页操作
		 */
		if (this.isDivided != null && this.isDivided.length() != 0
				&& this.isDivided.equals("true")) {
			isDivided = true;
		}
		try {
			/**
			 * 第一次进来的时候pageSize为空
			 */
			if (session.get("pageSize_GTUTG") != null) {
				pageSize_GTUTG = (Integer) session.get("pageSize_GTUTG");
			}
			this.request.put("undergraduateTutorGuidancePerformanceUnionTftermList", undergraduateTutorGuidancePerformanceDAO
					.findAllWithDivided(pageIndex, pageSize_GTUTG,
							(String) session.get("termId_GTUTG"), isDivided));
			tx=this.undergraduateTutorGuidancePerformanceDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}
	public String execute() throws Exception{
		return "success";
	}
	private int pageIndex = 1;
	private int pageSize_GTUTG = 1;
	private int operstatus;
	private String isDivided;
	private String termId_GTUTG;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private TfundergraduateTutorGuidancePerformance undergraduateTutorGuidancePerformance=new TfundergraduateTutorGuidancePerformance();
	private TfundergraduateTutorGuidancePerformanceDAO undergraduateTutorGuidancePerformanceDAO=new TfundergraduateTutorGuidancePerformanceDAO();
	private TfundergraduateTutorGuidanceCache undergraduateTutorGuidanceCache=new TfundergraduateTutorGuidanceCache();
	private TfundergraduateTutorGuidanceCacheDAO undergraduateTutorGuidanceCacheDAO=new TfundergraduateTutorGuidanceCacheDAO();
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_GTUTG() {
		return pageSize_GTUTG;
	}
	public void setPageSize_GTUTG(int pageSize_GTUTG) {
		this.pageSize_GTUTG = pageSize_GTUTG;
		session.put("pageSize_GTUTG", pageSize_GTUTG);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getIsDivided() {
		return isDivided;
	}
	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}
	public String getTermId_GTUTG() {
		return termId_GTUTG;
	}
	public void setTermId_GTUTG(String termId_GTUTG) {
		this.termId_GTUTG = termId_GTUTG;
		session.put("termId_GTUTG", termId_GTUTG);
	}
	public TfundergraduateTutorGuidancePerformance getUndergraduateTutorGuidancePerformance() {
		return undergraduateTutorGuidancePerformance;
	}
	public void setUndergraduateTutorGuidancePerformance(
			TfundergraduateTutorGuidancePerformance undergraduateTutorGuidancePerformance) {
		this.undergraduateTutorGuidancePerformance = undergraduateTutorGuidancePerformance;
	}
	public TfundergraduateTutorGuidanceCache getUndergraduateTutorGuidanceCache() {
		return undergraduateTutorGuidanceCache;
	}
	public void setUndergraduateTutorGuidanceCache(
			TfundergraduateTutorGuidanceCache undergraduateTutorGuidanceCache) {
		this.undergraduateTutorGuidanceCache = undergraduateTutorGuidanceCache;
	}
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}
}


















