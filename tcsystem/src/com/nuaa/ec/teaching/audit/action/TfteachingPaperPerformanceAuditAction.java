package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfteachingPaperPerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfteachingPaperPerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfteachingPaperPerformanceAuditAction implements RequestAware{
	public void doCheckOutTask() {
		List<TfteachingPaperPerformance> checkoutList=new ArrayList<TfteachingPaperPerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfteachingPaperPerformance TfteachingPaperPerformance = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				TfteachingPaperPerformance = this.TfteachingPaperPerfDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(TfteachingPaperPerformance!=null){
					TfteachingPaperPerformance.setCheckOut("1");
					checkoutList.add(TfteachingPaperPerformance);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				TfteachingPaperPerformance=this.TfteachingPaperPerfDAO.findById(Integer.parseInt(idsNot[i]));
				if(TfteachingPaperPerformance!=null){
					TfteachingPaperPerformance.setCheckOut("2");
					checkoutList.add(TfteachingPaperPerformance);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TfteachingPaperPerfDAO.updateCheckoutStatus(checkoutList)) {
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
	public String getTfteachingPaperPerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_TPP") == null) {
			session.put("department_TPP", new Department());
		}
		if ((Integer) session.get("pageSize_TPP") == null) {
			session.put("pageSize_TPP", 1);
		}
		try {
			this.request
					.put("TfteachingPaperPerformanceList",
							this.TfteachingPaperPerfDAO.getTFteachingPaperPefroList(pageIndex,
									(Integer) session.get("pageSize_TPP"),
									(String) session.get("termId_TPP"),
									(Department) session.get("department_TPP"),
									(String) session.get("checkOutStatus_TPP"),
									isDivided));
									
			tx = this.TfteachingPaperPerfDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		} finally {
		}
		return "success";
	}
	public String execute() throws Exception{
		return "success";
	}
	private int pageIndex = 1;
	private int pageSize_TPP;
	private String termId_TPP;
	private Department department_TPP;
	private int operstatus;
	private String checkOutStatus_TPP;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfteachingPaperPerformanceDAO TfteachingPaperPerfDAO=new TfteachingPaperPerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_TPP() {
		return pageSize_TPP;
	}
	public void setPageSize_TPP(int pageSize_TPP) {
		this.pageSize_TPP = pageSize_TPP;
		session.put("pageSize_TPP", pageSize_TPP);
	}
	public String getTermId_TPP() {
		return termId_TPP;
	}
	public void setTermId_TPP(String termId_TPP) {
		this.termId_TPP = termId_TPP;
		session.put("termId_TPP", termId_TPP);
	}
	public Department getDepartment_TPP() {
		return department_TPP;
	}
	public void setDepartment_TPP(Department department_TPP) {
		this.department_TPP = department_TPP;
		session.put("department_TPP", department_TPP);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_TPP() {
		return checkOutStatus_TPP;
	}
	public void setCheckOutStatus_TPP(String checkOutStatus_TPP) {
		this.checkOutStatus_TPP = checkOutStatus_TPP;
		session.put("checkOutStatus_TPP", checkOutStatus_TPP);
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
