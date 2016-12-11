package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfteachingCompetitionPerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfteachingCompetitionPerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfteachingCompetitionPerformanceAuditAction implements RequestAware{
	public void doCheckOutTask() {
		List<TfteachingCompetitionPerformance> checkoutList=new ArrayList<TfteachingCompetitionPerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfteachingCompetitionPerformance TfteachingCompetitionPerfList = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				TfteachingCompetitionPerfList = this.TfteachingCompetitionPerfDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(TfteachingCompetitionPerfList!=null){
					TfteachingCompetitionPerfList.setCheckOut("3");
					checkoutList.add(TfteachingCompetitionPerfList);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				TfteachingCompetitionPerfList=this.TfteachingCompetitionPerfDAO.findById(Integer.parseInt(idsNot[i]));
				if(TfteachingCompetitionPerfList!=null){
					TfteachingCompetitionPerfList.setCheckOut("2");
					checkoutList.add(TfteachingCompetitionPerfList);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TfteachingCompetitionPerfDAO.updateCheckoutStatus(checkoutList)) {
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
	public String getTFTeachingCompetitionPerfList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_TCP") == null) {
			session.put("department_TCP", new Department());
		}
		if ((Integer) session.get("pageSize_TCP") == null) {
			session.put("pageSize_TCP", 1);
		}
		try {
			this.request
					.put("TfteachingCompetitionPerfList",
							this.TfteachingCompetitionPerfDAO.getTFTeachingCompetitionPefroList(pageIndex,
									(Integer) session.get("pageSize_TCP"),
									(String) session.get("termId_TCP"),
									(Department) session.get("department_TCP"),
									(String) session.get("checkOutStatus_TCP"),
									isDivided));
									
			tx = this.TfteachingCompetitionPerfDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		} finally {
//			this.TfEN_WTBC_PerformDAO.closeSession();
		}
		return "success";
	}
	public String execute() throws Exception {
		return "success";
	}
	private int pageIndex = 1;
	private int pageSize_TCP;
	private String termId_TCP;
	private Department department_TCP;
	private int operstatus;
	private String checkOutStatus_TCP;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfteachingCompetitionPerformanceDAO TfteachingCompetitionPerfDAO=new TfteachingCompetitionPerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_TCP() {
		return pageSize_TCP;
	}
	public void setPageSize_TCP(int pageSize_TCP) {
		this.pageSize_TCP = pageSize_TCP;
		session.put("pageSize_TCP", pageSize_TCP);
	}
	public String getTermId_TCP() {
		return termId_TCP;
	}
	public void setTermId_TCP(String termId_TCP) {
		this.termId_TCP = termId_TCP;
		session.put("termId_TCP", termId_TCP);
	}
	public Department getDepartment_TCP() {
		return department_TCP;
	}
	public void setDepartment_TCP(Department department_TCP) {
		this.department_TCP = department_TCP;
		session.put("department_TCP", department_TCP);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_TCP() {
		return checkOutStatus_TCP;
	}
	public void setCheckOutStatus_TCP(String checkOutStatus_TCP) {
		this.checkOutStatus_TCP = checkOutStatus_TCP;
		session.put("checkOutStatus_TCP", checkOutStatus_TCP);
	}
	public String getCheckOutIDs() {
		return checkOutIDs;
	}
	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}
	public void setRequest(Map<String, Object> request) {
		this.request=request;
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
	
}
