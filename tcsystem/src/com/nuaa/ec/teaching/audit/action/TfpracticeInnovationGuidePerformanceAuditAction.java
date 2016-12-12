package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfpracticeInnovationGuidePerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfpracticeInnovationGuidePerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfpracticeInnovationGuidePerformanceAuditAction implements RequestAware{
	public String getTfPracticeInnovationGuidePerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_PIG") == null) {
			session.put("department_PIG", new Department());
		}
		if ((Integer) session.get("pageSize_PIG") == null) {
			session.put("pageSize_PIG", 1);
		}
		try {
			this.request
					.put("TfPracticeInnovationGuidePerformanceList",
							this.tfpracticeInnovationGuidePerformanceDAO.getTfPracticeInnovationGuidePerformanceListToBeAudited(pageIndex,
									(Integer) session.get("pageSize_PIG"),
									(String) session.get("termId_PIG"),
									(Department) session.get("department_PIG"),
									(String) session.get("checkOutStatus_PIG"),
									isDivided));
			tx = this.tfpracticeInnovationGuidePerformanceDAO.getSession().beginTransaction();
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
		List<TfpracticeInnovationGuidePerformance> checkoutList=new ArrayList<TfpracticeInnovationGuidePerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfpracticeInnovationGuidePerformance tfPraticeInnovationGuidePerformance = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				tfPraticeInnovationGuidePerformance = this.tfpracticeInnovationGuidePerformanceDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(tfPraticeInnovationGuidePerformance!=null){
					tfPraticeInnovationGuidePerformance.setCheckOut("3");
					checkoutList.add(tfPraticeInnovationGuidePerformance);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				tfPraticeInnovationGuidePerformance=this.tfpracticeInnovationGuidePerformanceDAO.findById(Integer.parseInt(idsNot[i]));
				if(tfPraticeInnovationGuidePerformance!=null){
					tfPraticeInnovationGuidePerformance.setCheckOut("2");
					checkoutList.add(tfPraticeInnovationGuidePerformance);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tfpracticeInnovationGuidePerformanceDAO.updateCheckoutStatus(checkoutList)) {
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
	private int pageSize_PIG;
	private String termId_PIG;
	private Department department_PIG;
	private int operstatus;
	private String checkOutStatus_PIG;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfpracticeInnovationGuidePerformanceDAO tfpracticeInnovationGuidePerformanceDAO=new TfpracticeInnovationGuidePerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_PIG() {
		return pageSize_PIG;
	}
	public void setPageSize_PIG(int pageSize_PIG) {
		this.pageSize_PIG = pageSize_PIG;
		session.put("pageSize_PIG", pageSize_PIG);
	}
	public String getTermId_PIG() {
		return termId_PIG;
	}
	public void setTermId_PIG(String termId_PIG) {
		this.termId_PIG = termId_PIG;
		session.put("termId_PIG", termId_PIG);
	}
	public Department getDepartment_PIG() {
		return department_PIG;
	}
	public void setDepartment_PIG(Department department_PIG) {
		this.department_PIG = department_PIG;
		session.put("department_PIG", department_PIG);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_PIG() {
		return checkOutStatus_PIG;
	}
	public void setCheckOutStatus_PIG(String checkOutStatus_PIG) {
		this.checkOutStatus_PIG = checkOutStatus_PIG;
		session.put("checkOutStatus_PIG", checkOutStatus_PIG);
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
