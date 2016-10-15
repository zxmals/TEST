package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfteachingAchievementPerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfteachingAchievementPerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfteachingAchievementPerformanceAuditAction implements RequestAware{
	
	public String getTfteachingAchievementPerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_TAP") == null) {
			session.put("department_TAP", new Department());
		}
		if ((Integer) session.get("pageSize_TAP") == null) {
			session.put("pageSize_TAP", 1);
		}
		try {
			this.request
					.put("TfteachingAchievementPerformanceList",
							this.TfteachingAchievementPerformanceDAO.getTFteachingAchievementPefroList(pageIndex,
									(Integer) session.get("pageSize_TAP"),
									(String) session.get("termId_TAP"),
									(Department) session.get("department_TAP"),
									(String) session.get("checkOutStatus_TAP"),
									isDivided));
									
			tx = this.TfteachingAchievementPerformanceDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		} finally {
		}
		return "success";
	}
	public void doCheckOutTask() {
		List<TfteachingAchievementPerformance> checkoutList=new ArrayList<TfteachingAchievementPerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfteachingAchievementPerformance TfteachingAchievementPerf = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				TfteachingAchievementPerf = this.TfteachingAchievementPerformanceDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(TfteachingAchievementPerf!=null){
					TfteachingAchievementPerf.setCheckOut("1");
					checkoutList.add(TfteachingAchievementPerf);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				TfteachingAchievementPerf=this.TfteachingAchievementPerformanceDAO.findById(Integer.parseInt(idsNot[i]));
				if(TfteachingAchievementPerf!=null){
					TfteachingAchievementPerf.setCheckOut("2");
					checkoutList.add(TfteachingAchievementPerf);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TfteachingAchievementPerformanceDAO.updateCheckoutStatus(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// this.getResearchLabList();
	}
	public String execute() throws Exception{
		return "success";
	}
	private int pageIndex = 1;
	private int pageSize_TAP;
	private String termId_TAP;
	private Department department_TAP;
	private int operstatus;
	private String checkOutStatus_TAP;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfteachingAchievementPerformanceDAO TfteachingAchievementPerformanceDAO=new TfteachingAchievementPerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_TAP() {
		return pageSize_TAP;
	}
	public void setPageSize_TAP(int pageSize_TAP) {
		this.pageSize_TAP = pageSize_TAP;
		session.put("pageSize_TAP", pageSize_TAP);
	}
	public String getTermId_TAP() {
		return termId_TAP;
	}
	public void setTermId_TAP(String termId_TAP) {
		this.termId_TAP = termId_TAP;
		session.put("termId_TAP", termId_TAP);
	}
	public Department getDepartment_TAP() {
		return department_TAP;
	}
	public void setDepartment_TAP(Department department_TAP) {
		this.department_TAP = department_TAP;
		session.put("department_TAP", department_TAP);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_TAP() {
		return checkOutStatus_TAP;
	}
	public void setCheckOutStatus_TAP(String checkOutStatus_TAP) {
		this.checkOutStatus_TAP = checkOutStatus_TAP;
		session.put("checkOutStatus_TAP", checkOutStatus_TAP);
	}
	public String getCheckOutIDs() {
		return checkOutIDs;
	}
	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}
	public String getIsDivided() {
		return isDivided;
	}
	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}
	public String getCheckOutIDsNot() {
		return checkOutIDsNot;
	}
	public void setCheckOutIDsNot(String checkOutIDsNot) {
		this.checkOutIDsNot = checkOutIDsNot;
	}
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}
}
