package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfundergraduateTutorGuidancePerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfundergraduateTutorGuidancePerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfundergraduateTutorGuidancePerformanceAuditAction implements RequestAware{
	public String getTfUndergraduateTutorGuidancePerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_UTG") == null) {
			session.put("department_UTG", new Department());
		}
		if ((Integer) session.get("pageSize_UTG") == null) {
			session.put("pageSize_UTG", 1);
		}
		try {
			this.request
					.put("TfUndergraduateTutorGuidancePerformanceList",
							this.tfundergraduateTutorGuidancePerformanceDAO.getTfUndergraduateTutorGuidancePerformanceListToBeAudited(pageIndex,
									(Integer) session.get("pageSize_UTG"),
									(String) session.get("termId_UTG"),
									(Department) session.get("department_UTG"),
									(String) session.get("checkOutStatus_UTG"),
									isDivided));
			tx = this.tfundergraduateTutorGuidancePerformanceDAO.getSession().beginTransaction();
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
		List<TfundergraduateTutorGuidancePerformance> checkoutList=new ArrayList<TfundergraduateTutorGuidancePerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfundergraduateTutorGuidancePerformance tfUndergraduateTutorGuidancePerformance = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				tfUndergraduateTutorGuidancePerformance = this.tfundergraduateTutorGuidancePerformanceDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(tfUndergraduateTutorGuidancePerformance!=null){
					tfUndergraduateTutorGuidancePerformance.setCheckOut("3");
					checkoutList.add(tfUndergraduateTutorGuidancePerformance);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				tfUndergraduateTutorGuidancePerformance=this.tfundergraduateTutorGuidancePerformanceDAO.findById(Integer.parseInt(idsNot[i]));
				if(tfUndergraduateTutorGuidancePerformance!=null){
					tfUndergraduateTutorGuidancePerformance.setCheckOut("2");
					checkoutList.add(tfUndergraduateTutorGuidancePerformance);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tfundergraduateTutorGuidancePerformanceDAO.updateCheckoutStatus(checkoutList)) {
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
	private int pageSize_UTG;
	private String termId_UTG;
	private Department department_UTG;
	private int operstatus;
	private String checkOutStatus_UTG;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfundergraduateTutorGuidancePerformanceDAO tfundergraduateTutorGuidancePerformanceDAO=new TfundergraduateTutorGuidancePerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_UTG() {
		return pageSize_UTG;
	}
	public void setPageSize_UTG(int pageSize_UTG) {
		this.pageSize_UTG = pageSize_UTG;
		session.put("pageSize_UTG", pageSize_UTG);
	}
	public String getTermId_UTG() {
		return termId_UTG;
	}
	public void setTermId_UTG(String termId_UTG) {
		this.termId_UTG = termId_UTG;
		session.put("termId_UTG", termId_UTG);
	}
	public Department getDepartment_UTG() {
		return department_UTG;
	}
	public void setDepartment_UTG(Department department_UTG) {
		this.department_UTG = department_UTG;
		session.put("department_UTG", department_UTG);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_UTG() {
		return checkOutStatus_UTG;
	}
	public void setCheckOutStatus_UTG(String checkOutStatus_UTG) {
		this.checkOutStatus_UTG = checkOutStatus_UTG;
		session.put("checkOutStatus_UTG", checkOutStatus_UTG);
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
