package com.nuaa.ec.Adm_teachingPerformanceSetAction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TfteachingCompetitionPerformanceDAO;
import com.nuaa.ec.dao.TfteachingCompetitionRewardLevelDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingCompetitionPerformance;
import com.nuaa.ec.model.TfteachingCompetitionRewardLevel;
import com.opensymphony.xwork2.ActionContext;

public class ATTeachingCompetitionPerformanceSetAction implements RequestAware{
	/**
	 * 删除一条记录
	 */
	public void deleteRecord(){
		Transaction tx=null;
		try{
			/**
			 * 先根据upid查出该条记录 然后设置spareTire 最后merge
			 */
			tfTeachingCompetitionPerformance=this.teachingCompetitionPerformanceDAO.findById(tfTeachingCompetitionPerformance.getUpid());
			tfTeachingCompetitionPerformance.setSpareTire("0");
			this.teachingCompetitionPerformanceDAO.merge(tfTeachingCompetitionPerformance);
			tx=this.teachingCompetitionPerformanceDAO.getSession().beginTransaction();
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
			tfTeachingCompetitionRewardLevel=this.tfTeachingComRewardLevelDAO.findById(this.tfTeachingCompetitionRewardLevel.getCompetRewardLevelId());
			tfTeachingCompetitionPerformance.setTfteachingCompetitionRewardLevel(tfTeachingCompetitionRewardLevel);
			tfTeachingCompetitionPerformance.setTeacher(teacherDAO.findById(teacher.getTeacherId()));
			tfTeachingCompetitionPerformance.setFinalScore(this.getScore(tfTeachingCompetitionRewardLevel.getCompetRewardLevelId().trim()));
			tfTeachingCompetitionPerformance.setCheckOut("1");
			tfTeachingCompetitionPerformance.setSpareTire("1");
			tx=this.teachingCompetitionPerformanceDAO.getSession().beginTransaction();
			this.teachingCompetitionPerformanceDAO.merge(tfTeachingCompetitionPerformance);
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
	 * 获取等级对应的分数
	 * @return
	 * @throws Exception
	 */
	public double getScore(String rewardLevel){
		double score=0;
		if(rewardLevel.equals("TFcp1")){
			score=100;
		}else if(rewardLevel.equals("TFcp2")){
			score=50;
		}else if(rewardLevel.equals("TFcp3")){
			score=30;
		}else if(rewardLevel.equals("TFcp4")){
			score=15;
		}else{
			score=5;
		}
			
		return score;
	}
	/**
	 * 获得教师的所有教学竞赛绩效的信息
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
			tx=this.teachingCompetitionPerformanceDAO.getSession().beginTransaction();
			/**
			 * 第一次进来的时候pageSize为空
			 */
			if (session.get("pageSize_ATTCP") != null) {
				pageSize_ATTCP = (Integer) session.get("pageSize_ATTCP");
			}
			this.request.put("teachingCompetitionPerfUnionTftermList",
					this.teachingCompetitionPerformanceDAO.findAllWithDivided_adm(
							pageIndex, pageSize_ATTCP,
							(String) session.get("termId_ATTCP"),(String) session.get("searchCondition_ATTCP"), isDivided));
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
	private int pageSize_ATTCP=1;
	private int operstatus;
	private String isDivided;
	private String termId_ATTCP;
	private String searchCondition_ATTCP;
	private Teacher teacher;
	private TeacherDAO teacherDAO=new TeacherDAO();
	private Map<String, Object> request;
	private TfteachingCompetitionPerformance tfTeachingCompetitionPerformance;
	private TfteachingCompetitionRewardLevel tfTeachingCompetitionRewardLevel;
	private TfteachingCompetitionRewardLevelDAO tfTeachingComRewardLevelDAO = new TfteachingCompetitionRewardLevelDAO();
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private TfteachingCompetitionPerformanceDAO teachingCompetitionPerformanceDAO = new TfteachingCompetitionPerformanceDAO();

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_ATTCP() {
		return pageSize_ATTCP;
	}

	public void setPageSize_ATTCP(int pageSize_ATTCP) {
		this.pageSize_ATTCP = pageSize_ATTCP;
		session.put("pageSize_ATTCP", pageSize_ATTCP);
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

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}


	public TfteachingCompetitionRewardLevel getTfTeachingCompetitionRewardLevel() {
		return tfTeachingCompetitionRewardLevel;
	}

	public void setTfTeachingCompetitionRewardLevel(
			TfteachingCompetitionRewardLevel tfTeachingCompetitionRewardLevel) {
		this.tfTeachingCompetitionRewardLevel = tfTeachingCompetitionRewardLevel;
	}

	public String getTermId_ATTCP() {
		return termId_ATTCP;
	}

	public void setTermId_ATTCP(String termId_ATTCP) {
		this.termId_ATTCP = termId_ATTCP;
		session.put("termId_ATTCP", termId_ATTCP);
	}
	public TfteachingCompetitionPerformance getTfTeachingCompetitionPerformance() {
		return tfTeachingCompetitionPerformance;
	}
	public void setTfTeachingCompetitionPerformance(
			TfteachingCompetitionPerformance tfTeachingCompetitionPerformance) {
		this.tfTeachingCompetitionPerformance = tfTeachingCompetitionPerformance;
	}
	public String getSearchCondition_ATTCP() {
		return searchCondition_ATTCP;
	}
	public void setSearchCondition_ATTCP(String searchCondition_ATTCP) {
		this.searchCondition_ATTCP = searchCondition_ATTCP;
		try {
			session.put("searchCondition_ATTCP", new String(searchCondition_ATTCP.getBytes("ISO-8859-1"),"UTF-8"));
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
