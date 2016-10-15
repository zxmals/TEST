package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfoffCampusPracticeGuidancePerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfoffCampusPracticeGuidancePerformanceAuditAction implements RequestAware{
	public String getTfOffCampusPracticeGuidancePerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_OCP") == null) {
			session.put("department_OCP", new Department());
		}
		if ((Integer) session.get("pageSize_OCP") == null) {
			session.put("pageSize_OCP", 1);
		}
		try {
			this.request
					.put("TfOffCampusPracticeGuidancePerformanceList",
							this.tfoffCampusPracticeGuidancePerformanceDAO.getOffCampusPracticeGuidancePerformanceListToBeAudit(pageIndex,
									(Integer) session.get("pageSize_OCP"),
									(String) session.get("termId_OCP"),
									(Department) session.get("department_OCP"),
									(String) session.get("checkOutStatus_OCP"),
									isDivided));
			tx = this.tfoffCampusPracticeGuidancePerformanceDAO.getSession().beginTransaction();
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
		List<TfoffCampusPracticeGuidancePerformance> checkoutList=new ArrayList<TfoffCampusPracticeGuidancePerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfoffCampusPracticeGuidancePerformance tfOffCampusPracticeGuidancePerformance = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				tfOffCampusPracticeGuidancePerformance = this.tfoffCampusPracticeGuidancePerformanceDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(tfOffCampusPracticeGuidancePerformance!=null){
					tfOffCampusPracticeGuidancePerformance.setCheckOut("1");
					checkoutList.add(tfOffCampusPracticeGuidancePerformance);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				tfOffCampusPracticeGuidancePerformance=this.tfoffCampusPracticeGuidancePerformanceDAO.findById(Integer.parseInt(idsNot[i]));
				if(tfOffCampusPracticeGuidancePerformance!=null){
					tfOffCampusPracticeGuidancePerformance.setCheckOut("2");
					checkoutList.add(tfOffCampusPracticeGuidancePerformance);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tfoffCampusPracticeGuidancePerformanceDAO.updateCheckoutStatus(checkoutList)) {
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
	private int pageSize_OCP;
	private String termId_OCP;
	private Department department_OCP;
	private int operstatus;
	private String checkOutStatus_OCP;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfoffCampusPracticeGuidancePerformanceDAO tfoffCampusPracticeGuidancePerformanceDAO=new TfoffCampusPracticeGuidancePerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_OCP() {
		return pageSize_OCP;
	}
	public void setPageSize_OCP(int pageSize_OCP) {
		this.pageSize_OCP = pageSize_OCP;
		session.put("pageSize_OCP", pageSize_OCP);
	}
	public String getTermId_OCP() {
		return termId_OCP;
	}
	public void setTermId_OCP(String termId_OCP) {
		this.termId_OCP = termId_OCP;
		session.put("termId_OCP", termId_OCP);
	}
	public Department getDepartment_OCP() {
		return department_OCP;
	}
	public void setDepartment_OCP(Department department_OCP) {
		this.department_OCP = department_OCP;
		session.put("department_OCP", department_OCP);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_OCP() {
		return checkOutStatus_OCP;
	}
	public void setCheckOutStatus_OCP(String checkOutStatus_OCP) {
		this.checkOutStatus_OCP = checkOutStatus_OCP;
		session.put("checkOutStatus_OCP", checkOutStatus_OCP);
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
