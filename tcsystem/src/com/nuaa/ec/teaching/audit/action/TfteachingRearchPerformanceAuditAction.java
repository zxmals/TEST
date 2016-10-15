package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfteachingRearchPerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfteachingRearchPerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfteachingRearchPerformanceAuditAction implements RequestAware{
	public String getTfteachingRearchPerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_TRP") == null) {
			session.put("department_TRP", new Department());
		}
		if ((Integer) session.get("pageSize_TRP") == null) {
			session.put("pageSize_TRP", 1);
		}
		try {
			this.request
					.put("TfteachingRearchPerformanceList",
							this.TfteachingRearchPerformanceDAO.getTFteachingRearchPefroList(pageIndex,
									(Integer) session.get("pageSize_TRP"),
									(String) session.get("termId_TRP"),
									(Department) session.get("department_TRP"),
									(String) session.get("checkOutStatus_TRP"),
									isDivided));
									
			tx = this.TfteachingRearchPerformanceDAO.getSession().beginTransaction();
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
		List<TfteachingRearchPerformance> checkoutList=new ArrayList<TfteachingRearchPerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfteachingRearchPerformance TfteachingRearchPerfList = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				TfteachingRearchPerfList = this.TfteachingRearchPerformanceDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(TfteachingRearchPerfList!=null){
					TfteachingRearchPerfList.setCheckOut("1");
					checkoutList.add(TfteachingRearchPerfList);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				TfteachingRearchPerfList=this.TfteachingRearchPerformanceDAO.findById(Integer.parseInt(idsNot[i]));
				if(TfteachingRearchPerfList!=null){
					TfteachingRearchPerfList.setCheckOut("2");
					checkoutList.add(TfteachingRearchPerfList);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TfteachingRearchPerformanceDAO.updateCheckoutStatus(checkoutList)) {
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
	private int pageSize_TRP;
	private String termId_TRP;
	private Department department_TRP;
	private int operstatus;
	private String checkOutStatus_TRP;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfteachingRearchPerformanceDAO TfteachingRearchPerformanceDAO=new TfteachingRearchPerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_TRP() {
		return pageSize_TRP;
	}
	public void setPageSize_TRP(int pageSize_TRP) {
		this.pageSize_TRP = pageSize_TRP;
		session.put("pageSize_TRP", pageSize_TRP);
	}
	public String getTermId_TRP() {
		return termId_TRP;
	}
	public void setTermId_TRP(String termId_TRP) {
		this.termId_TRP = termId_TRP;
		session.put("termId_TRP", termId_TRP);
	}
	public Department getDepartment_TRP() {
		return department_TRP;
	}
	public void setDepartment_TRP(Department department_TRP) {
		this.department_TRP = department_TRP;
		session.put("department_TRP", department_TRP);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_TRP() {
		return checkOutStatus_TRP;
	}
	public void setCheckOutStatus_TRP(String checkOutStatus_TRP) {
		this.checkOutStatus_TRP = checkOutStatus_TRP;
		session.put("checkOutStatus_TRP", checkOutStatus_TRP);
	}
	public String getCheckOutIDs() {
		return checkOutIDs;
	}
	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
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
