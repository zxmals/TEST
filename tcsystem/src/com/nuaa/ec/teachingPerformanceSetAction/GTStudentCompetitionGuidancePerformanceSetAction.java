package com.nuaa.ec.teachingPerformanceSetAction;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfstudentCompetitionGuidanceCompetitionTypeDAO;
import com.nuaa.ec.dao.TfstudentCompetitionGuidancePerformanceDAO;
import com.nuaa.ec.dao.TfstudentCompetitionGuidanceRewardLevelDAO;
import com.nuaa.ec.dao.TfstudentCompetitionGuidanceScoreDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfstudentCompetitionGuidanceCompetitionType;
import com.nuaa.ec.model.TfstudentCompetitionGuidancePerformance;
import com.nuaa.ec.model.TfstudentCompetitionGuidanceRewardLevel;
import com.nuaa.ec.model.TfstudentCompetitionGuidanceScore;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionContext;

public class GTStudentCompetitionGuidancePerformanceSetAction implements
		RequestAware {
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
			 * 设置当前教师
			 */
			studentCompetGuidPerf.setTeacher((Teacher) session.get("teacher"));
			/**
			 * 设置finalScore
			 */
			studentCompetGuidPerf.setFinalScore(studentCompetGuidScore.getBaseScore());
			/**
			 * 设置checkout以及spareTire信息
			 */
			studentCompetGuidPerf.setCheckOut("0");
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
	 * 新增一条记录
	 */
	public void insertRecord() {
		Transaction tx = null;
		try {
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
			 * 设置当前教师
			 */
			studentCompetGuidPerf.setTeacher((Teacher) session.get("teacher"));
			/**
			 * 设置finalScore
			 */
			studentCompetGuidPerf.setFinalScore(studentCompetGuidScore.getBaseScore());
			/**
			 * 设置竞赛ID
			 */
			studentCompetGuidPerf.setCompetitionId(pkmk.mkpk("competitionID", "TFStudentCompetitionGuidance_Performance", "TFCOMPET"));
			/**
			 * 设置checkout以及spareTire信息
			 */
			studentCompetGuidPerf.setCheckOut("0");
			studentCompetGuidPerf.setSpareTire("1");
			/**
			 * 执行添加操作
			 */
			this.studentCompetGuidPerfDAO.save(studentCompetGuidPerf);
			/**
			 * 事务提交
			 */
			tx=this.studentCompetGuidPerfDAO.getSession().beginTransaction();
			tx.commit();
			this.response.getWriter().write("succ");
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
			try {
				this.response.getWriter().write("errro");
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
			if (session.get("pageSize_GTSCG") != null) {
				pageSize_GTSCG = (Integer) session.get("pageSize_GTSCG");
			}
			this.request.put("studCompetGuidPerfUnionTftermList",
					studentCompetGuidPerfDAO.findAllWithDivided(pageIndex,
							pageSize_GTSCG,
							(String) session.get("termId_GTSCG"), isDivided));
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
	private int pageSize_GTSCG = 1;
	private int operstatus;
	private String isDivided;
	private String termId_GTSCG;
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
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private HttpServletResponse response = ServletActionContext.getResponse();

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_GTSCG() {
		return pageSize_GTSCG;
	}

	public void setPageSize_GTSCG(int pageSize_GTSCG) {
		this.pageSize_GTSCG = pageSize_GTSCG;
		session.put("pageSize_GTSCG", pageSize_GTSCG);
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

	public String getTermId_GTSCG() {
		return termId_GTSCG;
	}

	public void setTermId_GTSCG(String termId_GTSCG) {
		this.termId_GTSCG = termId_GTSCG;
		session.put("termId_GTSCG", termId_GTSCG);
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

}
