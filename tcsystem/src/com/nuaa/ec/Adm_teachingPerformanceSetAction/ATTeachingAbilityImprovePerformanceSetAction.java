package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TfteachingAbilityImproveLevelDAO;
import com.nuaa.ec.dao.TfteachingAbilityImprovePerformanceDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingAbilityImproveLevel;
import com.nuaa.ec.model.TfteachingAbilityImprovePerformance;
import com.opensymphony.xwork2.ActionContext;

public class ATTeachingAbilityImprovePerformanceSetAction implements
		RequestAware {
	/**
	 * 删除一条记录
	 */
	public void deleteRecord(){
		Transaction tx=null;
		try{
			/**
			 * 获得tfteachingAbilityImprovePerformance全部信息
			 * 然后改动spareTire属性  否则不这样做可能其他属性在
			 * 更新时会变成null
			 */
			tfteachingAbilityImprovePerformance=this.tfteachingAbilityImprovePerformanceDAO.findById(tfteachingAbilityImprovePerformance.getPuid());
			tfteachingAbilityImprovePerformance.setSpareTire("0");
			this.tfteachingAbilityImprovePerformanceDAO.merge(tfteachingAbilityImprovePerformance);
			tx=this.tfteachingAbilityImprovePerformanceDAO.getSession().beginTransaction();
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
	 * 更新记录信息
	 */
	public void updateRecord(){
		Transaction tx=null;
		try{
			tfteachingAbilityImproveLevel=this.tfteachingAbilityImproveLevelDAO.findById(tfteachingAbilityImproveLevel.getImproveLevelId());
			double score=this.getScore(tfteachingAbilityImprovePerformance.getSumhours(), tfteachingAbilityImproveLevel.getImproveLevelId());
			tfteachingAbilityImprovePerformance.setTfteachingAbilityImproveLevel(tfteachingAbilityImproveLevel);
			tfteachingAbilityImprovePerformance.setTeacher(teacherDAO.findById(teacher.getTeacherId()));
			tfteachingAbilityImprovePerformance.setYearceiling(30);
			tfteachingAbilityImprovePerformance.setFinalScore(score);
			tfteachingAbilityImprovePerformance.setSpareTire("1");
			tfteachingAbilityImprovePerformance.setCheckOut("0");
			this.tfteachingAbilityImprovePerformanceDAO.merge(tfteachingAbilityImprovePerformance);
			tx=this.tfteachingAbilityImprovePerformanceDAO.getSession().beginTransaction();
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
	 * 根据总时长计算分数
	 */
	public double getScore(double sumHours,String typeID){
		double score=0;
		if(typeID.trim().equals("TFImLv1")){
			//校内活动级别
//			score=(sumHours%2==0)?((sumHours/2)*1):(((sumHours+1)/2)*1);这是原始的计算式子，不过可以简化
			score=(sumHours%2==0)?(sumHours/2):((sumHours+1)/2);//简化后
		}else{
			//校外活动级别
//			score=(sumHours%2==0)?((sumHours/2)*2):(((sumHours+1)/2)*2);//这是原始的计算式子
			score=(sumHours%2==0)?(sumHours):(sumHours+1);//这是简化运算后的计算式子
		}
		/**
		 * 每个教师每年最多30分
		 */
		if(score>30){
			score=30;
		}
		return score;
	}
	/**
	 * 获得教师所有的教学能力提升绩效记录
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
			if (session.get("pageSize_ATTAI") != null) {
				pageSize_ATTAI = (Integer) session.get("pageSize_ATTAI");
			}
			this.request.put("tfTeachingAbilityImprovePerfUnionTfermList",
					this.tfteachingAbilityImprovePerformanceDAO
							.findAllWithDivided_adm(pageIndex, pageSize_ATTAI,
									(String) session.get("termId_ATTAI"),
									(String) session.get("searchCondition_ATTAI"),
									isDivided));
			tx = this.tfteachingAbilityImprovePerformanceDAO.getSession()
					.beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}

	public String execute() throws Exception {
		return "success";
	}

	private int pageIndex = 1;
	private int pageSize_ATTAI = 1;
	private int operstatus;
	private String isDivided;
	private String termId_ATTAI;
	private Teacher teacher;
	private String searchCondition_ATTAI;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private TeacherDAO teacherDAO=new TeacherDAO();
	private TfteachingAbilityImprovePerformanceDAO tfteachingAbilityImprovePerformanceDAO = new TfteachingAbilityImprovePerformanceDAO();
	private TfteachingAbilityImproveLevelDAO tfteachingAbilityImproveLevelDAO = new TfteachingAbilityImproveLevelDAO();
	private TfteachingAbilityImprovePerformance tfteachingAbilityImprovePerformance;
	private TfteachingAbilityImproveLevel tfteachingAbilityImproveLevel;
	private HttpServletResponse response = ServletActionContext.getResponse();

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_ATTAI() {
		return pageSize_ATTAI;
	}

	public void setPageSize_ATTAI(int pageSize_ATTAI) {
		this.pageSize_ATTAI = pageSize_ATTAI;
		session.put("pageSize_ATTAI", pageSize_ATTAI);
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

	public String getTermId_ATTAI() {
		return termId_ATTAI;
	}

	public void setTermId_ATTAI(String termId_ATTAI) {
		this.termId_ATTAI = termId_ATTAI;
		session.put("termId_ATTAI", termId_ATTAI);
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public TfteachingAbilityImprovePerformance getTfteachingAbilityImprovePerformance() {
		return tfteachingAbilityImprovePerformance;
	}

	public void setTfteachingAbilityImprovePerformance(
			TfteachingAbilityImprovePerformance tfteachingAbilityImprovePerformance) {
		this.tfteachingAbilityImprovePerformance = tfteachingAbilityImprovePerformance;
	}

	public TfteachingAbilityImproveLevel getTfteachingAbilityImproveLevel() {
		return tfteachingAbilityImproveLevel;
	}

	public void setTfteachingAbilityImproveLevel(
			TfteachingAbilityImproveLevel tfteachingAbilityImproveLevel) {
		this.tfteachingAbilityImproveLevel = tfteachingAbilityImproveLevel;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSearchCondition_ATTAI() {
		return searchCondition_ATTAI;
	}

	public void setSearchCondition_ATTAI(String searchCondition_ATTAI) {
		this.searchCondition_ATTAI = searchCondition_ATTAI;
		try {
			session.put("searchCondition_ATTAI", new String(
					searchCondition_ATTAI.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
