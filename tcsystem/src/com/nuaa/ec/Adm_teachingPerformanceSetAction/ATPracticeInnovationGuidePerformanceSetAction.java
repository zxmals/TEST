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
import com.nuaa.ec.dao.TfpracticeInnovationGuideGraduationThesisGuideEvalutionDAO;
import com.nuaa.ec.dao.TfpracticeInnovationGuideLevelDAO;
import com.nuaa.ec.dao.TfpracticeInnovationGuidePerformanceDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfpracticeInnovationGuideGraduationThesisGuideEvalution;
import com.nuaa.ec.model.TfpracticeInnovationGuideLevel;
import com.nuaa.ec.model.TfpracticeInnovationGuidePerformance;
import com.opensymphony.xwork2.ActionContext;

public class ATPracticeInnovationGuidePerformanceSetAction implements RequestAware{
	/**
	 * 删除一条记录
	 */
	public void deleteRecord(){
		Transaction tx=null;
		try{
			/**
			 * 找到对应的记录performance记录
			 */
			pracInnoGuidPerf=this.pracInnoGuidPerfDAO.findById(pracInnoGuidPerf.getUpid());
			/**
			 * 设置删除标志
			 */
			this.pracInnoGuidPerf.setSpareTire("0");
			/**
			 * 执行删除事务
			 */
			this.pracInnoGuidPerfDAO.merge(pracInnoGuidPerf);
			/**
			 * 获取并提交事务
			 */
			tx=this.pracInnoGuidPerfDAO.getSession().beginTransaction();
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
			 * 获取论文指导级别
			 */
			pracInnoGuidGradThesisExalution=this.pracInnoGuidGradThesisExalutionDAO.findById(pracInnoGuidGradThesisExalution.getThesisEvaluationLevelId());
			/**
			 * 获取项目指导级别
			 */
			pracInnoGuidLevel=this.pracInnoGuidLevelDAO.findById(pracInnoGuidLevel.getInnovationGuideLevelId());
			/**
			 * 设置论文指导级别
			 */
			pracInnoGuidPerf.setTfpracticeInnovationGuideGraduationThesisGuideEvalution(pracInnoGuidGradThesisExalution);
			/**
			 * 设置项目指导级别
			 */
			pracInnoGuidPerf.setTfpracticeInnovationGuideLevel(pracInnoGuidLevel);
			/**
			 * 设置当前教师
			 */
			pracInnoGuidPerf.setTeacher(teacherDAO.findById(teacher.getTeacherId()));
			/**
			 * 设置分数
			 */
			pracInnoGuidPerf.setFinalScore(this.getScore(pracInnoGuidGradThesisExalution, pracInnoGuidLevel));
			/**
			 * 设置checkout和spireTire信息
			 */
			pracInnoGuidPerf.setSpareTire("1");
			pracInnoGuidPerf.setCheckOut("0");
			/**
			 * 事务处理
			 */
			this.pracInnoGuidPerfDAO.merge(pracInnoGuidPerf);
			tx=this.pracInnoGuidPerfDAO.getSession().beginTransaction();
			this.setOperstatus(1);
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
	 * 获取分数
	 */
	public double getScore(TfpracticeInnovationGuideGraduationThesisGuideEvalution thesisLevel,TfpracticeInnovationGuideLevel projectLevel){
		DecimalFormat  df  = new DecimalFormat("######0.00"); 
		return Double.parseDouble(df.format(thesisLevel.getRatio()*projectLevel.getScore()));
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
			if (session.get("pageSize_ATPIG") != null) {
				pageSize_ATPIG = (Integer) session.get("pageSize_ATPIG");
			}
			this.request.put("pracInnoGuidPerfUnionTftermList", pracInnoGuidPerfDAO
					.findAllWithDivided_adm(pageIndex, pageSize_ATPIG,
							(String) session.get("termId_ATPIG"), (String) session.get("searchCondition_ATPIG"),isDivided));
			tx=this.pracInnoGuidPerfDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}
	private int pageIndex = 1;
	private int pageSize_ATPIG = 1;
	private int operstatus;
	private String isDivided;
	private String termId_ATPIG;
	private Teacher teacher;
	private String searchCondition_ATPIG;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private TfpracticeInnovationGuidePerformance pracInnoGuidPerf = new TfpracticeInnovationGuidePerformance();
	private TfpracticeInnovationGuideLevel pracInnoGuidLevel = new TfpracticeInnovationGuideLevel();
	private TfpracticeInnovationGuideGraduationThesisGuideEvalution pracInnoGuidGradThesisExalution = new TfpracticeInnovationGuideGraduationThesisGuideEvalution();
	private TfpracticeInnovationGuidePerformanceDAO pracInnoGuidPerfDAO = new TfpracticeInnovationGuidePerformanceDAO();
	private TfpracticeInnovationGuideLevelDAO pracInnoGuidLevelDAO = new TfpracticeInnovationGuideLevelDAO();
	private TfpracticeInnovationGuideGraduationThesisGuideEvalutionDAO pracInnoGuidGradThesisExalutionDAO = new TfpracticeInnovationGuideGraduationThesisGuideEvalutionDAO();
	private TeacherDAO teacherDAO=new TeacherDAO();
	private HttpServletResponse response = ServletActionContext.getResponse();

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_ATPIG() {
		return pageSize_ATPIG;
	}

	public void setPageSize_ATPIG(int pageSize_ATPIG) {
		this.pageSize_ATPIG = pageSize_ATPIG;
		session.put("pageSize_ATPIG", pageSize_ATPIG);
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

	public String getTermId_ATPIG() {
		return termId_ATPIG;
	}

	public void setTermId_ATPIG(String termId_ATPIG) {
		this.termId_ATPIG = termId_ATPIG;
		session.put("termId_ATPIG", termId_ATPIG);
	}

	public TfpracticeInnovationGuidePerformance getPracInnoGuidPerf() {
		return pracInnoGuidPerf;
	}

	public void setPracInnoGuidPerf(
			TfpracticeInnovationGuidePerformance pracInnoGuidPerf) {
		this.pracInnoGuidPerf = pracInnoGuidPerf;
	}

	public TfpracticeInnovationGuideLevel getPracInnoGuidLevel() {
		return pracInnoGuidLevel;
	}

	public void setPracInnoGuidLevel(
			TfpracticeInnovationGuideLevel pracInnoGuidLevel) {
		this.pracInnoGuidLevel = pracInnoGuidLevel;
	}

	public TfpracticeInnovationGuideGraduationThesisGuideEvalution getPracInnoGuidGradThesisExalution() {
		return pracInnoGuidGradThesisExalution;
	}

	public void setPracInnoGuidGradThesisExalution(
			TfpracticeInnovationGuideGraduationThesisGuideEvalution pracInnoGuidGradThesisExalution) {
		this.pracInnoGuidGradThesisExalution = pracInnoGuidGradThesisExalution;
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

	public String getSearchCondition_ATPIG() {
		return searchCondition_ATPIG;
	}

	public void setSearchCondition_ATPIG(String searchCondition_ATPIG) {
		this.searchCondition_ATPIG = searchCondition_ATPIG;
		try {
			session.put("searchCondition_ATPIG", new String(searchCondition_ATPIG.getBytes("ISO-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
