package com.nuaa.ec.teachingPerformanceSetAction;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfsummerCourseInternationalConstructionLevelDAO;
import com.nuaa.ec.dao.TfsummerCourseInternationalConstructionPerformanceDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfsummerCourseInternationalConstructionLevel;
import com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionContext;

public class GTSummerCourseInternationalConstructionPerformanceSetAction implements RequestAware{
	/**
	 * 删除一条信息
	 */
	public void deleteRecord(){
		Transaction tx=null;
		try{
			summerCourseInterConsPerf=this.summerCourseInterConsPerfDAO.findById(summerCourseInterConsPerf.getUpid());
			summerCourseInterConsPerf.setSpareTire("0");
			this.summerCourseInterConsPerfDAO.merge(summerCourseInterConsPerf);
			tx=this.summerCourseInterConsPerfDAO.getSession().beginTransaction();
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
	 * 更新信息
	 */
	public void updateRecord(){
		Transaction tx=null;
		try{
			/**
			 * 获取课程级别信息
			 */
			summerCourseInterConsLevel=this.summerCourseInterConsLevelDAO.findById(summerCourseInterConsLevel.getProjectLevelId());
			/**
			 * 设置级别
			 */
			summerCourseInterConsPerf.setTfsummerCourseInternationalConstructionLevel(summerCourseInterConsLevel);
			/**
			 * 设置分数
			 */
			summerCourseInterConsPerf.setScore(this.getScore(summerCourseInterConsPerf));
			/**
			 * 设置当前教师
			 */
			summerCourseInterConsPerf.setTeacher((Teacher) session.get("teacher"));
			/**
			 * 设置其他信息:checkOut SpareTire;
			 */
			summerCourseInterConsPerf.setSpareTire("1");
			summerCourseInterConsPerf.setCheckOut("0");;
			/**
			 * 保存记录
			 */
			this.summerCourseInterConsPerfDAO.merge(summerCourseInterConsPerf);
			tx=this.summerCourseInterConsPerfDAO.getSession().beginTransaction();
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
	 * 新增一条当前教师暑期课程与国际课程的记录
	 */
	public void insertRecord(){
		Transaction tx=null;
		try{
			/**
			 * 获取课程级别信息
			 */
			summerCourseInterConsLevel=this.summerCourseInterConsLevelDAO.findById(summerCourseInterConsLevel.getProjectLevelId());
			/**
			 * 设置级别
			 */
			summerCourseInterConsPerf.setTfsummerCourseInternationalConstructionLevel(summerCourseInterConsLevel);
			/**
			 * 设置项目ID
			 */
			summerCourseInterConsPerf.setProjectId(pkmk.mkpk("projectID", "TFSummerCourseInternationalConstruction_Performance", "SUMCOR"));
			/**
			 * 设置分数
			 */
			summerCourseInterConsPerf.setScore(this.getScore(summerCourseInterConsPerf));
			/**
			 * 设置当前教师
			 */
			summerCourseInterConsPerf.setTeacher((Teacher) session.get("teacher"));
			/**
			 * 设置其他信息:checkOut SpareTire;
			 */
			summerCourseInterConsPerf.setSpareTire("1");
			summerCourseInterConsPerf.setCheckOut("0");;
			/**
			 * 保存记录
			 */
			this.summerCourseInterConsPerfDAO.save(summerCourseInterConsPerf);
			tx=this.summerCourseInterConsPerfDAO.getSession().beginTransaction();
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
	 * 获得分数
	 */
	public double getScore(TfsummerCourseInternationalConstructionPerformance sumerCorseInterPerf){
		return sumerCorseInterPerf.getQuantityUnit()*20;
	}
	/**
	 * 获取当前用户的所有暑期课程与国际课程的建设的绩效记录（已经分页）
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
			if (session.get("pageSize_GTSCI") != null) {
				pageSize_GTSCI = (Integer) session.get("pageSize_GTSCI");
			}
			this.request.put("summerCourseInterConsPerfList",
					this.summerCourseInterConsPerfDAO
							.findAllWithDivided(pageIndex, pageSize_GTSCI,
									(String) session.get("termId_GTSCI"),
									isDivided));
			tx = this.summerCourseInterConsPerfDAO.getSession()
					.beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}

	private int pageIndex = 1;
	private int pageSize_GTSCI = 1;
	private int operstatus;
	private String isDivided;
	private String termId_GTSCI;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private TfsummerCourseInternationalConstructionPerformance summerCourseInterConsPerf;
	private TfsummerCourseInternationalConstructionLevel summerCourseInterConsLevel;
	private TfsummerCourseInternationalConstructionPerformanceDAO summerCourseInterConsPerfDAO = new TfsummerCourseInternationalConstructionPerformanceDAO();
	private TfsummerCourseInternationalConstructionLevelDAO summerCourseInterConsLevelDAO=new TfsummerCourseInternationalConstructionLevelDAO();
	private HttpServletResponse response=ServletActionContext.getResponse();
	private PrimaryKMaker pkmk=new PrimaryKMaker();
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_GTSCI() {
		return pageSize_GTSCI;
	}
	public void setPageSize_GTSCI(int pageSize_GTSCI) {
		this.pageSize_GTSCI = pageSize_GTSCI;
		session.put("pageSize_GTSCI", pageSize_GTSCI);
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
	public String getTermId_GTSCI() {
		return termId_GTSCI;
	}
	public void setTermId_GTSCI(String termId_GTSCI) {
		this.termId_GTSCI = termId_GTSCI;
		session.put("termId_GTSCI", termId_GTSCI);
	}
	public TfsummerCourseInternationalConstructionPerformance getSummerCourseInterConsPerf() {
		return summerCourseInterConsPerf;
	}
	public void setSummerCourseInterConsPerf(
			TfsummerCourseInternationalConstructionPerformance summerCourseInterConsPerf) {
		this.summerCourseInterConsPerf = summerCourseInterConsPerf;
	}
	public TfsummerCourseInternationalConstructionLevel getSummerCourseInterConsLevel() {
		return summerCourseInterConsLevel;
	}
	public void setSummerCourseInterConsLevel(
			TfsummerCourseInternationalConstructionLevel summerCourseInterConsLevel) {
		this.summerCourseInterConsLevel = summerCourseInterConsLevel;
	}
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}
}
