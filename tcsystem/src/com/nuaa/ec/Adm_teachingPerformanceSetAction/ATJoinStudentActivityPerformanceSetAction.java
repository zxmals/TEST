package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TfjoinStudentActivityPerformanceDAO;
import com.nuaa.ec.dao.TfjoinStudentActivityTimeDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfjoinStudentActivityPerformance;
import com.nuaa.ec.model.TfjoinStudentActivityTime;
import com.opensymphony.xwork2.ActionContext;

public class ATJoinStudentActivityPerformanceSetAction implements RequestAware{
	/**
	 * 删除一条记录
	 */
	public void deleteRecord(){
		Transaction tx=null;
		try{
			joinStudentActivityPerformance=this.joinStudentActivityPerformanceDAO.findById(joinStudentActivityPerformance.getUpid());
			joinStudentActivityPerformance.setSpareTire("0");
			this.joinStudentActivityPerformanceDAO.merge(joinStudentActivityPerformance);
			tx=this.joinStudentActivityPerformanceDAO.getSession().beginTransaction();
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
	 * 修改一条参加学生活动的绩效记录
	 */
	public void updateRecord(){
		Transaction tx=null;
		try{
			/*
			 * 获得[TFJoinStudentActivity_time]的信息
			 * 只有一条记录，而且计分方式只有一种
			 * 每2h计分1分
			 */
			joinStudentActivityTime=this.joinStudentActivityTimeDAO.findById("TFacttim1");
			/*
			 * 设置教师
			 */
			joinStudentActivityPerformance.setTeacher(teacherDAO.findById(teacher.getTeacherId()));
			/*
			 * 设置TFJoinStudentActivity_time
			 */
			joinStudentActivityPerformance.setTfjoinStudentActivityTime(joinStudentActivityTime);
			/*
			 * 设置分数
			 */
			joinStudentActivityPerformance.setFinalScore(this.getScore(joinStudentActivityPerformance));
			/*
			 * 设置上限分数
			 */
			joinStudentActivityPerformance.setYearceiling(15);
			/*
			 * 设置checkout和spareTire
			 */
			joinStudentActivityPerformance.setCheckOut("0");
			joinStudentActivityPerformance.setSpareTire("1");
			/*
			 * 执行保存操作
			 */
			this.joinStudentActivityPerformanceDAO.merge(joinStudentActivityPerformance);
			/*
			 * 提交事务
			 */
			tx=this.joinStudentActivityPerformanceDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			this.response.getWriter().write("succ");
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
			this.setOperstatus(-1);
			try {
				this.response.getWriter().write("error");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取finalScore
	 */
	public double getScore(TfjoinStudentActivityPerformance joinStudActPerf){
		double score=0;
		double sumHour=joinStudActPerf.getSumhours();
		DecimalFormat df  = new DecimalFormat("######0.0");
		score=sumHour/2;
		if(Double.parseDouble(df.format(score))>15.0){
			score=15;
		}
		return score;
	}
	/**
	 * 获取当前用户的符合条件的所有记录
	 * @return
	 */
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
			if (session.get("pageSize_ATJSA") != null) {
				pageSize_ATJSA = (Integer) session.get("pageSize_ATJSA");
			}
			this.request.put("joinStuActPerfUnionTftermList", joinStudentActivityPerformanceDAO
					.findAllWithDivided_adm(pageIndex, pageSize_ATJSA,
							(String) session.get("termId_ATJSA"),(String) session.get("searchCondition_ATJSA"), isDivided));
			tx=this.joinStudentActivityPerformanceDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}
	private int pageIndex = 1;
	private int pageSize_ATJSA = 1;
	private int operstatus;
	private String isDivided;
	private String termId_ATJSA;
	private Teacher teacher;
	private String searchCondition_ATJSA;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private TfjoinStudentActivityPerformance joinStudentActivityPerformance=new TfjoinStudentActivityPerformance();
	private TfjoinStudentActivityPerformanceDAO joinStudentActivityPerformanceDAO=new TfjoinStudentActivityPerformanceDAO();
	private TfjoinStudentActivityTime joinStudentActivityTime=new TfjoinStudentActivityTime();
	private TfjoinStudentActivityTimeDAO joinStudentActivityTimeDAO=new TfjoinStudentActivityTimeDAO();
	private TeacherDAO teacherDAO=new TeacherDAO();
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_ATJSA() {
		return pageSize_ATJSA;
	}

	public void setPageSize_ATJSA(int pageSize_ATJSA) {
		this.pageSize_ATJSA = pageSize_ATJSA;
		session.put("pageSize_ATJSA", pageSize_ATJSA);
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

	public String getTermId_ATJSA() {
		return termId_ATJSA;
	}

	public void setTermId_ATJSA(String termId_ATJSA) {
		this.termId_ATJSA = termId_ATJSA;
		session.put("termId_ATJSA", termId_ATJSA);
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public TfjoinStudentActivityPerformance getJoinStudentActivityPerformance() {
		return joinStudentActivityPerformance;
	}
	public void setJoinStudentActivityPerformance(
			TfjoinStudentActivityPerformance joinStudentActivityPerformance) {
		this.joinStudentActivityPerformance = joinStudentActivityPerformance;
	}
	public TfjoinStudentActivityTime getJoinStudentActivityTime() {
		return joinStudentActivityTime;
	}
	public void setJoinStudentActivityTime(
			TfjoinStudentActivityTime joinStudentActivityTime) {
		this.joinStudentActivityTime = joinStudentActivityTime;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSearchCondition_ATJSA() {
		return searchCondition_ATJSA;
	}

	public void setSearchCondition_ATJSA(String searchCondition_ATJSA) {
		this.searchCondition_ATJSA = searchCondition_ATJSA;
		try {
			session.put("searchCondition_ATJSA", new String(searchCondition_ATJSA.getBytes("ISO-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
