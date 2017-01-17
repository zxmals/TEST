package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TfsummerCourseInternationalConstructionLevelDAO;
import com.nuaa.ec.dao.TfsummerCourseInternationalConstructionPerformanceDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfsummerCourseInternationalConstructionLevel;
import com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance;
import com.opensymphony.xwork2.ActionContext;

public class ATSummerCourseInternationalConstructionPerformanceSetAction implements RequestAware{
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
			summerCourseInterConsPerf.setTeacher(teacherDAO.findById(teacher.getTeacherId()));
			/**
			 * 设置其他信息:checkOut SpareTire;
			 */
			summerCourseInterConsPerf.setSpareTire("1");
			summerCourseInterConsPerf.setCheckOut("1");;
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
			if (session.get("pageSize_ATSCI") != null) {
				pageSize_ATSCI = (Integer) session.get("pageSize_ATSCI");
			}
			this.request.put("summerCourseInterConsPerfUnionTftermList",
					this.summerCourseInterConsPerfDAO
							.findAllWithDivided_adm(pageIndex, pageSize_ATSCI,
									(String) session.get("termId_ATSCI"),(String) session.get("searchCondition_ATSCI"),
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
	private int pageSize_ATSCI = 1;
	private int operstatus;
	private String isDivided;
	private String termId_ATSCI;
	private Teacher teacher;
	private TeacherDAO teacherDAO=new TeacherDAO();
	private String searchCondition_ATSCI;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private TfsummerCourseInternationalConstructionPerformance summerCourseInterConsPerf;
	private TfsummerCourseInternationalConstructionLevel summerCourseInterConsLevel;
	private TfsummerCourseInternationalConstructionPerformanceDAO summerCourseInterConsPerfDAO = new TfsummerCourseInternationalConstructionPerformanceDAO();
	private TfsummerCourseInternationalConstructionLevelDAO summerCourseInterConsLevelDAO=new TfsummerCourseInternationalConstructionLevelDAO();
	private HttpServletResponse response=ServletActionContext.getResponse();
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_ATSCI() {
		return pageSize_ATSCI;
	}
	public void setPageSize_ATSCI(int pageSize_ATSCI) {
		this.pageSize_ATSCI = pageSize_ATSCI;
		session.put("pageSize_ATSCI", pageSize_ATSCI);
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
	public String getTermId_ATSCI() {
		return termId_ATSCI;
	}
	public void setTermId_ATSCI(String termId_ATSCI) {
		this.termId_ATSCI = termId_ATSCI;
		session.put("termId_ATSCI", termId_ATSCI);
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
	public String getSearchCondition_ATSCI() {
		return searchCondition_ATSCI;
	}
	public void setSearchCondition_ATSCI(String searchCondition_ATSCI) {
		this.searchCondition_ATSCI = searchCondition_ATSCI;
		try {
			session.put("searchCondition_ATSCI", new String(searchCondition_ATSCI.getBytes("ISO_8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
