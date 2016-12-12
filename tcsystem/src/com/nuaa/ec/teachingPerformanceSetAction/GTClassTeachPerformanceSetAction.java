package com.nuaa.ec.teachingPerformanceSetAction;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfclassTeachEvaluationDAO;
import com.nuaa.ec.dao.TfclassTeachPefromanceDAO;
import com.nuaa.ec.dao.TfclassTeachTimeDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfclassTeachEvaluation;
import com.nuaa.ec.model.TfclassTeachPefromance;
import com.nuaa.ec.model.TfclassTeachTime;
import com.nuaa.ec.utils.MergeTermToYear;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionContext;

public class GTClassTeachPerformanceSetAction implements RequestAware{
	/**
	 * 删除一条记录
	 */
	public void deleteRecord(){
		try{
			classTeachPerformance=classTeachPerformanceDAO.findById(classTeachPerformance.getClassPefromanceId());
			classTeachPerformance.setSpareTire("0");
			classTeachPerformanceDAO.merge(classTeachPerformance);
			classTeachPerformanceDAO.getSession().beginTransaction().commit();
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
	 * 更新一条课堂教学绩效记录
	 */
	public void updateRecord(){
		try{
			/*
			 * 获得时间类别对象
			 */
			classTeachTime=classTeachTimeDAO.findById(classTeachTime.getSumtimeId());
			/*
			 * 获得评估级别的对象
			 */
			classTeachEvaluation=classTeachEvaluationDAO.findById(classTeachEvaluation.getEvaluationId());
			/*
			 * 设置时间类别
			 */
			classTeachPerformance.setTfclassTeachTime(classTeachTime);
			/*
			 * 设置评估级别
			 */
			classTeachPerformance.setTfclassTeachEvaluation(classTeachEvaluation);
			/*
			 * 设置当前教师
			 */
			classTeachPerformance.setTeacher((Teacher) session.get("teacher"));
			/*
			 * 设置分数
			 */
			classTeachPerformance.setFinalScore(this.getScore(classTeachPerformance, classTeachTime, classTeachEvaluation));
			/*
			 * 设置学期（周期是一学年，所以无论前台选的那个学期 同意存储为该学年的第一个学期）
			 */
			classTeachPerformance.setTermId(MergeTermToYear.getTermAfterMerge(classTeachPerformance.getTermId()));
			session.put("termId_GTCT", classTeachPerformance.getTermId());
			/*
			 * 设置其他信息 checkout spareTire 
			 */
			classTeachPerformance.setCheckOut("1");
			classTeachPerformance.setSpareTire("1");
			/*
			 * 执行save
			 */
			this.classTeachPerformanceDAO.merge(classTeachPerformance);
			this.classTeachPerformanceDAO.getSession().beginTransaction().commit();
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
	 * 新增一条课堂教学记录
	 */
	public void insertRecord(){
		try{
			/*
			 * 获得时间类别对象
			 */
			classTeachTime=classTeachTimeDAO.findById(classTeachTime.getSumtimeId());
			/*
			 * 获得评估级别的对象
			 */
			classTeachEvaluation=classTeachEvaluationDAO.findById(classTeachEvaluation.getEvaluationId());
			/*
			 * 设置时间类别
			 */
			classTeachPerformance.setTfclassTeachTime(classTeachTime);
			/*
			 * 设置评估级别
			 */
			classTeachPerformance.setTfclassTeachEvaluation(classTeachEvaluation);
			/*
			 * 设置主键
			 */
			classTeachPerformance.setClassPefromanceId(pkmk.mkpk("ClassPefromanceID", "TFClassTeach_Pefromance", "classP"));
			/*
			 * 设置当前教师
			 */
			classTeachPerformance.setTeacher((Teacher) session.get("teacher"));
			/*
			 * 设置分数
			 */
			classTeachPerformance.setFinalScore(this.getScore(classTeachPerformance, classTeachTime, classTeachEvaluation));
			/*
			 * 设置学期（周期是一学年，所以无论前台选的那个学期 同意存储为该学年的第一个学期）
			 */
			classTeachPerformance.setTermId(MergeTermToYear.getTermAfterMerge(classTeachPerformance.getTermId()));
			session.put("termId_GTCT", classTeachPerformance.getTermId());
			/*
			 * 设置其他信息 checkout spareTire 
			 */
			classTeachPerformance.setCheckOut("1");
			classTeachPerformance.setSpareTire("1");
			/*
			 * 执行save
			 */
			this.classTeachPerformanceDAO.save(classTeachPerformance);
			this.classTeachPerformanceDAO.getSession().beginTransaction().commit();
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
	public double getScore(TfclassTeachPefromance perf,TfclassTeachTime time,TfclassTeachEvaluation evaluation){
		return Double.parseDouble(perf.getSumtime())*time.getRatio()*evaluation.getRatio();
	}
	/**
	 * 获得当前用户符合查询条件的所有记录
	 * @return
	 */
	public String getAllRecord(){
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
			if (session.get("pageSize_GTCT") != null) {
				pageSize_GTCT = (Integer) session.get("pageSize_GTCT");
			}
			this.request.put("classTeachPerformanceUnionTftermList",
					classTeachPerformanceDAO.findAllWithDivided(pageIndex,
							pageSize_GTCT,
							(String) session.get("termId_GTCT"), isDivided));
			tx = this.classTeachPerformanceDAO.getSession().beginTransaction();
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
	private int pageSize_GTCT = 1;
	private int operstatus;
	private String isDivided;
	private String termId_GTCT;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private TfclassTeachPefromance classTeachPerformance;
	private TfclassTeachPefromanceDAO classTeachPerformanceDAO=new TfclassTeachPefromanceDAO();
	private TfclassTeachEvaluation classTeachEvaluation;
	private TfclassTeachEvaluationDAO classTeachEvaluationDAO=new TfclassTeachEvaluationDAO();
	private TfclassTeachTime classTeachTime;
	private TfclassTeachTimeDAO classTeachTimeDAO=new TfclassTeachTimeDAO();
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_GTCT() {
		return pageSize_GTCT;
	}

	public void setPageSize_GTCT(int pageSize_GTCT) {
		this.pageSize_GTCT = pageSize_GTCT;
		session.put("pageSize_GTCT", pageSize_GTCT);
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

	public String getTermId_GTCT() {
		return termId_GTCT;
	}

	public void setTermId_GTCT(String termId_GTCT) {
		this.termId_GTCT = termId_GTCT;
		session.put("termId_GTCT", termId_GTCT);
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public TfclassTeachPefromance getClassTeachPerformance() {
		return classTeachPerformance;
	}
	public void setClassTeachPerformance(
			TfclassTeachPefromance classTeachPerformance) {
		this.classTeachPerformance = classTeachPerformance;
	}
	public TfclassTeachEvaluation getClassTeachEvaluation() {
		return classTeachEvaluation;
	}
	public void setClassTeachEvaluation(TfclassTeachEvaluation classTeachEvaluation) {
		this.classTeachEvaluation = classTeachEvaluation;
	}
	public TfclassTeachTime getClassTeachTime() {
		return classTeachTime;
	}
	public void setClassTeachTime(TfclassTeachTime classTeachTime) {
		this.classTeachTime = classTeachTime;
	}

}
