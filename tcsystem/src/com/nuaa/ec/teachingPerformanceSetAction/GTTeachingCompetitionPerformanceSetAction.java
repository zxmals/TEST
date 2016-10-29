package com.nuaa.ec.teachingPerformanceSetAction;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfteachingCompetitionPerformanceDAO;
import com.nuaa.ec.dao.TfteachingCompetitionRewardLevelDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingCompetitionPerformance;
import com.nuaa.ec.model.TfteachingCompetitionRewardLevel;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionContext;

public class GTTeachingCompetitionPerformanceSetAction implements RequestAware {
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
				this.response.getWriter().write("eroor");
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
			tfTeachingCompetitionPerformance.setTeacher((Teacher) session.get("teacher"));
			tfTeachingCompetitionPerformance.setFinalScore(this.getScore(tfTeachingCompetitionRewardLevel.getCompetRewardLevelId().trim()));
			tfTeachingCompetitionPerformance.setCheckOut("0");
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
	 * 插入教学竞赛绩效记录
	 */
	public void insertRecord(){
		Transaction tx=null;
		try{
			System.out.println("reward ID:"+tfTeachingCompetitionRewardLevel.getCompetRewardLevelId());
			/*
			 * 获取奖励水平的完整信息
			 */
			tfTeachingCompetitionRewardLevel=this.tfTeachingComRewardLevelDAO.findById(tfTeachingCompetitionRewardLevel.getCompetRewardLevelId());
			/*
			 * 设置各项信息
			 */
			tfTeachingCompetitionPerformance.setTfteachingCompetitionRewardLevel(tfTeachingCompetitionRewardLevel);
			/*
			 * 取出当前教师，设置给TFTeachingCompetitionPerformance
			 */
			tfTeachingCompetitionPerformance.setTeacher((Teacher) session.get("teacher"));
			tfTeachingCompetitionPerformance.setFinalScore(this.getScore(this.tfTeachingCompetitionRewardLevel.getCompetRewardLevelId()));
			tfTeachingCompetitionPerformance.setCompetitionId(pkmk.mkpk("competitionID","TFTeachingCompetition_Performance", "cp"));
			tfTeachingCompetitionPerformance.setSpareTire("1");
			tfTeachingCompetitionPerformance.setCheckOut("0");
			this.teachingCompetitionPerformanceDAO.save(tfTeachingCompetitionPerformance);
			tx=this.teachingCompetitionPerformanceDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(-1);
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
	 * 获得当前教师的所有教学竞赛绩效的信息
	 * 
	 * @return
	 */
	public String getAllRecordOfCurrentTeacher() {
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
			if (session.get("pageSize_GTTCP") != null) {
				pageSize_GTTCP = (Integer) session.get("pageSize_GTTCP");
			}
			this.request.put("teachingCompetitionPerfList",
					this.teachingCompetitionPerformanceDAO.findAllWithDivided(
							pageIndex, pageSize_GTTCP,
							(String) session.get("termId_GTTCP"), isDivided));
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
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

	public String execute() throws Exception {
		return "success";
	}

	private int pageIndex = 1;
	private int pageSize_GTTCP=1;
	private int operstatus;
	private String isDivided;
	private String termId_GTTCP;
	private Map<String, Object> request;
	private TfteachingCompetitionPerformance tfTeachingCompetitionPerformance;
	private TfteachingCompetitionRewardLevel tfTeachingCompetitionRewardLevel;
	private TfteachingCompetitionRewardLevelDAO tfTeachingComRewardLevelDAO = new TfteachingCompetitionRewardLevelDAO();
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private TfteachingCompetitionPerformanceDAO teachingCompetitionPerformanceDAO = new TfteachingCompetitionPerformanceDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_GTTCP() {
		return pageSize_GTTCP;
	}

	public void setPageSize_GTTCP(int pageSize_GTTCP) {
		this.pageSize_GTTCP = pageSize_GTTCP;
		session.put("pageSize_GTTCP", pageSize_GTTCP);
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

	public String getTermId_GTTCP() {
		return termId_GTTCP;
	}

	public void setTermId_GTTCP(String termId_GTTCP) {
		this.termId_GTTCP = termId_GTTCP;
		session.put("termId_GTTCP", termId_GTTCP);
	}
	public TfteachingCompetitionPerformance getTfTeachingCompetitionPerformance() {
		return tfTeachingCompetitionPerformance;
	}
	public void setTfTeachingCompetitionPerformance(
			TfteachingCompetitionPerformance tfTeachingCompetitionPerformance) {
		this.tfTeachingCompetitionPerformance = tfTeachingCompetitionPerformance;
	}
}
