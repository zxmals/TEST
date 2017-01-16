package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TfundergraduateTutorGuidanceCacheDAO;
import com.nuaa.ec.dao.TfundergraduateTutorGuidancePerformanceDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfundergraduateTutorGuidanceCache;
import com.nuaa.ec.model.TfundergraduateTutorGuidancePerformance;
import com.nuaa.ec.utils.MergeTermToYear;
import com.opensymphony.xwork2.ActionContext;

public class ATUndergraduateTutorGuidancePerformanceSetAction implements RequestAware{
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
			 * 设置教师
			 */
			undergraduateTutorGuidancePerformance.setTeacher(teacherDAO.findById(teacher.getTeacherId()));
			/*
			 * 设置学期
			 */
			undergraduateTutorGuidancePerformance.setTermId(MergeTermToYear.getTermAfterMerge(undergraduateTutorGuidancePerformance.getTermId().trim()));
			/*
			 * 设置其他信息
			 */
			undergraduateTutorGuidancePerformance.setYears(1.0);
			undergraduateTutorGuidancePerformance.setCheckOut("1");
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
			if (session.get("pageSize_ATUTG") != null) {
				pageSize_ATUTG = (Integer) session.get("pageSize_ATUTG");
			}
			this.request.put("undergraduateTutorGuidancePerformanceUnionTftermList", undergraduateTutorGuidancePerformanceDAO
					.findAllWithDivided_adm(pageIndex, pageSize_ATUTG,
							(String) session.get("termId_ATUTG"),(String) session.get("searchCondition_ATUTG"), isDivided));
			tx=this.undergraduateTutorGuidancePerformanceDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}
	private int pageIndex=1;
	private int pageSize_ATUTG = 1;
	private int operstatus;
	private String isDivided;
	private String termId_ATUTG;
	private Teacher teacher;
	private String searchCondition_ATUTG;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private TfundergraduateTutorGuidancePerformance undergraduateTutorGuidancePerformance=new TfundergraduateTutorGuidancePerformance();
	private TfundergraduateTutorGuidancePerformanceDAO undergraduateTutorGuidancePerformanceDAO=new TfundergraduateTutorGuidancePerformanceDAO();
	private TfundergraduateTutorGuidanceCache undergraduateTutorGuidanceCache=new TfundergraduateTutorGuidanceCache();
	private TfundergraduateTutorGuidanceCacheDAO undergraduateTutorGuidanceCacheDAO=new TfundergraduateTutorGuidanceCacheDAO();
	private TeacherDAO teacherDAO=new TeacherDAO();
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_ATUTG() {
		return pageSize_ATUTG;
	}
	public void setPageSize_ATUTG(int pageSize_ATUTG) {
		this.pageSize_ATUTG = pageSize_ATUTG;
		session.put("pageSize_ATUTG", pageSize_ATUTG);
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
	public String getTermId_ATUTG() {
		return termId_ATUTG;
	}
	public void setTermId_ATUTG(String termId_ATUTG) {
		this.termId_ATUTG = termId_ATUTG;
		session.put("termId_ATUTG", termId_ATUTG);
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
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getSearchCondition_ATUTG() {
		return searchCondition_ATUTG;
	}
	public void setSearchCondition_ATUTG(String searchCondition_ATUTG) {
		this.searchCondition_ATUTG = searchCondition_ATUTG;
		try {
			session.put("searchCondition_ATUTG", new String(searchCondition_ATUTG.getBytes("ISO-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
