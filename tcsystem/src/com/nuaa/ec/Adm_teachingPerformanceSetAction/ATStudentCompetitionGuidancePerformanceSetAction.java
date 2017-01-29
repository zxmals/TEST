package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TfstudentCompetitionGuidanceCompetitionTypeDAO;
import com.nuaa.ec.dao.TfstudentCompetitionGuidancePerformanceDAO;
import com.nuaa.ec.dao.TfstudentCompetitionGuidanceRewardLevelDAO;
import com.nuaa.ec.dao.TfstudentCompetitionGuidanceScoreDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfstudentCompetitionGuidanceCompetitionType;
import com.nuaa.ec.model.TfstudentCompetitionGuidancePerformance;
import com.nuaa.ec.model.TfstudentCompetitionGuidanceRewardLevel;
import com.nuaa.ec.model.TfstudentCompetitionGuidanceScore;
import com.opensymphony.xwork2.ActionContext;

public class ATStudentCompetitionGuidancePerformanceSetAction implements RequestAware{
	/**
	 * 删除一条记录
	 */
	public void deleteRecord(){
		Transaction tx=null;
		try{
			/**
			 * 获得学生竞赛指导绩效记录
			 */
			studentCompetGuidPerf=this.studentCompetGuidPerfDAO.findById(studentCompetGuidPerf.getUpid());
			studentCompetGuidPerf.setSpareTire("0");
			this.studentCompetGuidPerfDAO.merge(studentCompetGuidPerf);
			tx=this.studentCompetGuidPerfDAO.getSession().beginTransaction();
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
	 * 更新一条记录
	 */
	public void updateRecord(){
		Transaction tx=null;
		try{
			/**
			 * 获得竞赛级别信息
			 */
			studentCompetGuidanceCompetitionType=this.studentCompetGuidanceCompetitionTypeDAO.findById(studentCompetGuidanceCompetitionType.getCompetitionTypeId());
			/**
			 * 获得获奖级别信息
			 */
			studentCompetGuidRewardLevel=this.studentCompetGuidRewardLevelDAO.findById(studentCompetGuidRewardLevel.getRewardLevelId());
			/**
			 * 通过竞赛级别以及获奖级别获得得分详细信息studentCompetGuidScore
			 */
			studentCompetGuidScore=this.studentCompetGuidScoreDAO.findScoreWithCompetitionTypeAndRewardLevel(studentCompetGuidanceCompetitionType, studentCompetGuidRewardLevel);
			/**
			 * 设置得分信息
			 */
			studentCompetGuidPerf.setTfstudentCompetitionGuidanceScore(studentCompetGuidScore);
			/**
			 * 设置教师
			 */
			studentCompetGuidPerf.setTeacher(teacherDAO.findById(teacher.getTeacherId()));
			/**
			 * 设置finalScore
			 */
			studentCompetGuidPerf.setFinalScore(studentCompetGuidScore.getBaseScore());
			/**
			 * 设置checkout以及spareTire信息
			 */
			studentCompetGuidPerf.setCheckOut("1");
			studentCompetGuidPerf.setSpareTire("1");
			/**
			 * 执行更新操作
			 */
			this.studentCompetGuidPerfDAO.merge(studentCompetGuidPerf);
			/**
			 * 事务提交
			 */
			tx=this.studentCompetGuidPerfDAO.getSession().beginTransaction();
			tx.commit();
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
	 * 获得当前教师符合检索条件的所有检索记录
	 * 
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
			if (session.get("pageSize_ATSCG") != null) {
				pageSize_ATSCG = (Integer) session.get("pageSize_ATSCG");
			}
			this.request.put("studCompetGuidPerfUnionTftermList",
					studentCompetGuidPerfDAO.findAllWithDivided_adm(pageIndex,
							pageSize_ATSCG,
							(String) session.get("termId_ATSCG"),(String) session.get("searchCondition_ATSCG"), isDivided));
			tx = this.studentCompetGuidPerfDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}
	private int pageIndex = 1;
	private int pageSize_ATSCG = 1;
	private int operstatus;
	private String isDivided;
	private String termId_ATSCG;
	private Teacher teacher;
	private String searchCondition_ATSCG;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private TfstudentCompetitionGuidancePerformance studentCompetGuidPerf;
	private TfstudentCompetitionGuidanceRewardLevel studentCompetGuidRewardLevel;
	private TfstudentCompetitionGuidanceCompetitionType studentCompetGuidanceCompetitionType;
	private TfstudentCompetitionGuidanceScore studentCompetGuidScore;
	private TfstudentCompetitionGuidancePerformanceDAO studentCompetGuidPerfDAO = new TfstudentCompetitionGuidancePerformanceDAO();
	private TfstudentCompetitionGuidanceRewardLevelDAO studentCompetGuidRewardLevelDAO = new TfstudentCompetitionGuidanceRewardLevelDAO();
	private TfstudentCompetitionGuidanceCompetitionTypeDAO studentCompetGuidanceCompetitionTypeDAO = new TfstudentCompetitionGuidanceCompetitionTypeDAO();
	private TfstudentCompetitionGuidanceScoreDAO studentCompetGuidScoreDAO = new TfstudentCompetitionGuidanceScoreDAO();
	private TeacherDAO teacherDAO=new TeacherDAO();
	private HttpServletResponse response = ServletActionContext.getResponse();

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_ATSCG() {
		return pageSize_ATSCG;
	}

	public void setPageSize_ATSCG(int pageSize_ATSCG) {
		this.pageSize_ATSCG = pageSize_ATSCG;
		session.put("pageSize_ATSCG", pageSize_ATSCG);
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

	public String getTermId_ATSCG() {
		return termId_ATSCG;
	}

	public void setTermId_ATSCG(String termId_ATSCG) {
		this.termId_ATSCG = termId_ATSCG;
		session.put("termId_ATSCG", termId_ATSCG);
	}

	public TfstudentCompetitionGuidancePerformance getStudentCompetGuidPerf() {
		return studentCompetGuidPerf;
	}

	public void setStudentCompetGuidPerf(
			TfstudentCompetitionGuidancePerformance studentCompetGuidPerf) {
		this.studentCompetGuidPerf = studentCompetGuidPerf;
	}

	public TfstudentCompetitionGuidanceRewardLevel getStudentCompetGuidRewardLevel() {
		return studentCompetGuidRewardLevel;
	}

	public void setStudentCompetGuidRewardLevel(
			TfstudentCompetitionGuidanceRewardLevel studentCompetGuidRewardLevel) {
		this.studentCompetGuidRewardLevel = studentCompetGuidRewardLevel;
	}

	public TfstudentCompetitionGuidanceCompetitionType getStudentCompetGuidanceCompetitionType() {
		return studentCompetGuidanceCompetitionType;
	}

	public void setStudentCompetGuidanceCompetitionType(
			TfstudentCompetitionGuidanceCompetitionType studentCompetGuidanceCompetitionType) {
		this.studentCompetGuidanceCompetitionType = studentCompetGuidanceCompetitionType;
	}

	public TfstudentCompetitionGuidanceScore getStudentCompetGuidScore() {
		return studentCompetGuidScore;
	}

	public void setStudentCompetGuidScore(
			TfstudentCompetitionGuidanceScore studentCompetGuidScore) {
		this.studentCompetGuidScore = studentCompetGuidScore;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSearchCondition_ATSCG() {
		return searchCondition_ATSCG;
	}

	public void setSearchCondition_ATSCG(String searchCondition_ATSCG) {
		this.searchCondition_ATSCG = searchCondition_ATSCG;
		try {
			session.put("searchCondition_ATSCG", new String(searchCondition_ATSCG.getBytes("ISO-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
