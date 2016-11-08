package com.nuaa.ec.teachingPerformanceSetAction;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfoffCampusPracticeGuidanceLevelDAO;
import com.nuaa.ec.dao.TfoffCampusPracticeGuidancePerformanceDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfoffCampusPracticeGuidanceLevel;
import com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionContext;

public class GTOffCampusPracticeGuidancePerformanceSetAction implements RequestAware{
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
			offCampusPracticeGuidancePerformance.setTeacher((Teacher) session.get("teacher"));
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
	/**
	 * 新增一条校外实践知道记录
	 */
	public void insertRecord(){
		Transaction tx=null;
		try{
			/*
			 * 获得绩效级别信息
			 */
			offCampusPracticeGuidanceLevel=this.offCampusPracticeGuidanceLevelDAO.findById(offCampusPracticeGuidanceLevel.getProjectId());
			/*
			 * 设置绩效级别
			 */
			offCampusPracticeGuidancePerformance.setTfoffCampusPracticeGuidanceLevel(offCampusPracticeGuidanceLevel);
			/*
			 * 设置指导绩效ID
			 */
			offCampusPracticeGuidancePerformance.setOffguidanceId(pkmk.mkpk("offguidanceID", "TFOffcampusPracticeGuidance_Performance", "TFOFF"));
			/*
			 * 设置分数
			 */
			offCampusPracticeGuidancePerformance.setFinalScore(this.getScore(offCampusPracticeGuidancePerformance, offCampusPracticeGuidanceLevel));
			/*
			 * 设置当前教师
			 */
			offCampusPracticeGuidancePerformance.setTeacher((Teacher) session.get("teacher"));
			/*
			 * 设置其他信息 checkout spareTire YearCeiling
			 */
			offCampusPracticeGuidancePerformance.setCheckOut("0");
			offCampusPracticeGuidancePerformance.setSpareTire("1");
			offCampusPracticeGuidancePerformance.setYearCeiling(50);
			/*
			 * save
			 */
			this.offCampusPracticeGuidancePerformanceDAO.save(offCampusPracticeGuidancePerformance);
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
			if (session.get("pageSize_GTOCP") != null) {
				pageSize_GTOCP = (Integer) session.get("pageSize_GTOCP");
			}
			this.request.put("offCampusPracGuidPerfUnionTftermList",
					offCampusPracticeGuidancePerformanceDAO.findAllWithDivided(pageIndex,
							pageSize_GTOCP,
							(String) session.get("termId_GTOCP"), isDivided));
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
	private int pageSize_GTOCP = 1;
	private int operstatus;
	private String isDivided;
	private String termId_GTOCP;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private TfoffCampusPracticeGuidancePerformance offCampusPracticeGuidancePerformance;
	private TfoffCampusPracticeGuidancePerformanceDAO offCampusPracticeGuidancePerformanceDAO=new TfoffCampusPracticeGuidancePerformanceDAO();
	private TfoffCampusPracticeGuidanceLevel offCampusPracticeGuidanceLevel;
	private TfoffCampusPracticeGuidanceLevelDAO offCampusPracticeGuidanceLevelDAO=new TfoffCampusPracticeGuidanceLevelDAO();
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_GTOCP() {
		return pageSize_GTOCP;
	}

	public void setPageSize_GTOCP(int pageSize_GTOCP) {
		this.pageSize_GTOCP = pageSize_GTOCP;
		session.put("pageSize_GTOCP", pageSize_GTOCP);
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

	public String getTermId_GTOCP() {
		return termId_GTOCP;
	}

	public void setTermId_GTOCP(String termId_GTOCP) {
		this.termId_GTOCP = termId_GTOCP;
		session.put("termId_GTOCP", termId_GTOCP);
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

}
