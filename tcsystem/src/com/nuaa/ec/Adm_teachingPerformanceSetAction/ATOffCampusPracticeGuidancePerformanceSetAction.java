package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TfoffCampusPracticeGuidanceLevelDAO;
import com.nuaa.ec.dao.TfoffCampusPracticeGuidancePerformanceDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfoffCampusPracticeGuidanceLevel;
import com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionContext;

public class ATOffCampusPracticeGuidancePerformanceSetAction implements RequestAware{
	/*
	 * 删除一条记录
	 */
	public void deleteRecord(){
		Transaction tx=null;
		try{
			/*
			 * 获取offCampusPracticeGuidancePerformance
			 */
			offCampusPracticeGuidancePerformance=this.offCampusPracticeGuidancePerformanceDAO.findById(offCampusPracticeGuidancePerformance.getUpid());
			offCampusPracticeGuidancePerformance.setSpareTire("0");
			this.offCampusPracticeGuidancePerformanceDAO.merge(offCampusPracticeGuidancePerformance);
			tx=this.offCampusPracticeGuidancePerformanceDAO.getSession().beginTransaction();
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
			/*
			 * 获得绩效级别信息
			 */
			offCampusPracticeGuidanceLevel=this.offCampusPracticeGuidanceLevelDAO.findById(offCampusPracticeGuidanceLevel.getProjectId());
			if(offCampusPracticeGuidanceLevel.getProjectId().trim().equals("TFOCP1")){
				offCampusPracticeGuidancePerformance.setSumhours(null);
			}else{
				offCampusPracticeGuidancePerformance.setQuantityUnit(null);
			}
			/*
			 * 设置绩效级别
			 */
			offCampusPracticeGuidancePerformance.setTfoffCampusPracticeGuidanceLevel(offCampusPracticeGuidanceLevel);
			/*
			 * 设置分数
			 */
			offCampusPracticeGuidancePerformance.setFinalScore(this.getScore(offCampusPracticeGuidancePerformance, offCampusPracticeGuidanceLevel));
			/*
			 * 设置当前教师
			 */
			offCampusPracticeGuidancePerformance.setTeacher(teacherDAO.findById(teacher.getTeacherId()));
			/*
			 * 设置其他信息 checkout spareTire YearCeiling
			 */
			offCampusPracticeGuidancePerformance.setCheckOut("0");
			offCampusPracticeGuidancePerformance.setSpareTire("1");
			offCampusPracticeGuidancePerformance.setYearCeiling(50);
			/*
			 * save
			 */
			this.offCampusPracticeGuidancePerformanceDAO.merge(offCampusPracticeGuidancePerformance);
			/*
			 * 提交事务
			 */
			tx=this.offCampusPracticeGuidancePerformanceDAO.getSession().beginTransaction();
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
	public double getScore(TfoffCampusPracticeGuidancePerformance offCamPracGuidPerf,TfoffCampusPracticeGuidanceLevel offCamPracGuidLevel){
		double score=0;
		/*
		 * 判断绩效级别
		 */
		if(offCamPracGuidLevel.getProjectId().equals("TFOCP1")){
			//说明是projectType是新增实践基地类别
			score=offCamPracGuidPerf.getQuantityUnit()*30;
		}else{
			score=offCamPracGuidPerf.getSumhours();//每两小时记2分
		}
		/*
		 * 判断分数是否超出最高上限50分
		 */
		score=(score>50)?(50):(score);
		return score;
	}
	/**
	 * 获得符合检索条件的所有检索记录
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
			if (session.get("pageSize_ATOCP") != null) {
				pageSize_ATOCP = (Integer) session.get("pageSize_ATOCP");
			}
			this.request.put("offCampusPracGuidPerfUnionTftermList",
					offCampusPracticeGuidancePerformanceDAO.findAllWithDivided_adm(pageIndex,
							pageSize_ATOCP,
							(String) session.get("termId_ATOCP"),(String) session.get("searchCondition_ATOCP"), isDivided));
			tx = this.offCampusPracticeGuidancePerformanceDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}
	private int pageIndex = 1;
	private int pageSize_ATOCP = 1;
	private int operstatus;
	private String isDivided;
	private String termId_ATOCP;
	private Teacher teacher;
	private String searchCondition_ATOCP;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private TfoffCampusPracticeGuidancePerformance offCampusPracticeGuidancePerformance;
	private TfoffCampusPracticeGuidancePerformanceDAO offCampusPracticeGuidancePerformanceDAO=new TfoffCampusPracticeGuidancePerformanceDAO();
	private TfoffCampusPracticeGuidanceLevel offCampusPracticeGuidanceLevel;
	private TfoffCampusPracticeGuidanceLevelDAO offCampusPracticeGuidanceLevelDAO=new TfoffCampusPracticeGuidanceLevelDAO();
	private TeacherDAO teacherDAO=new TeacherDAO();
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_ATOCP() {
		return pageSize_ATOCP;
	}

	public void setPageSize_ATOCP(int pageSize_ATOCP) {
		this.pageSize_ATOCP = pageSize_ATOCP;
		session.put("pageSize_ATOCP", pageSize_ATOCP);
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

	public String getTermId_ATOCP() {
		return termId_ATOCP;
	}

	public void setTermId_ATOCP(String termId_ATOCP) {
		this.termId_ATOCP = termId_ATOCP;
		session.put("termId_ATOCP", termId_ATOCP);
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public TfoffCampusPracticeGuidancePerformance getOffCampusPracticeGuidancePerformance() {
		return offCampusPracticeGuidancePerformance;
	}

	public void setOffCampusPracticeGuidancePerformance(
			TfoffCampusPracticeGuidancePerformance offCampusPracticeGuidancePerformance) {
		this.offCampusPracticeGuidancePerformance = offCampusPracticeGuidancePerformance;
	}

	public TfoffCampusPracticeGuidanceLevel getOffCampusPracticeGuidanceLevel() {
		return offCampusPracticeGuidanceLevel;
	}

	public void setOffCampusPracticeGuidanceLevel(
			TfoffCampusPracticeGuidanceLevel offCampusPracticeGuidanceLevel) {
		this.offCampusPracticeGuidanceLevel = offCampusPracticeGuidanceLevel;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSearchCondition_ATOCP() {
		return searchCondition_ATOCP;
	}

	public void setSearchCondition_ATOCP(String searchCondition_ATOCP) {
		this.searchCondition_ATOCP = searchCondition_ATOCP;
		try {
			session.put("searchCondition_ATOCP", new String(searchCondition_ATOCP.getBytes("ISO-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


}
