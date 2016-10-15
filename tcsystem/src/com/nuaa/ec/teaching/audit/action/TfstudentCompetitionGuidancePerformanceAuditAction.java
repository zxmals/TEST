package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfstudentCompetitionGuidancePerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfstudentCompetitionGuidancePerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfstudentCompetitionGuidancePerformanceAuditAction implements RequestAware{
	public String getTfStudentCompetitionGuidancePerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_SCG") == null) {
			session.put("department_SCG", new Department());
		}
		if ((Integer) session.get("pageSize_SCG") == null) {
			session.put("pageSize_SCG", 1);
		}
		try {
			this.request
					.put("TfStudentCompetitionGuidancePerformanceList",
							this.tfstudentCompetitionGuidancePerformanceDAO.getTfStudentCompetitionGuidancePerformanceListToBeAudited(pageIndex,
									(Integer) session.get("pageSize_SCG"),
									(String) session.get("termId_SCG"),
									(Department) session.get("department_SCG"),
									(String) session.get("checkOutStatus_SCG"),
									isDivided));
			tx = this.tfstudentCompetitionGuidancePerformanceDAO.getSession().beginTransaction();
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
		List<TfstudentCompetitionGuidancePerformance> checkoutList=new ArrayList<TfstudentCompetitionGuidancePerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfstudentCompetitionGuidancePerformance tfStudentCompetitionGuidancePerformance = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				tfStudentCompetitionGuidancePerformance = this.tfstudentCompetitionGuidancePerformanceDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(tfStudentCompetitionGuidancePerformance!=null){
					tfStudentCompetitionGuidancePerformance.setCheckOut("1");
					checkoutList.add(tfStudentCompetitionGuidancePerformance);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				tfStudentCompetitionGuidancePerformance=this.tfstudentCompetitionGuidancePerformanceDAO.findById(Integer.parseInt(idsNot[i]));
				if(tfStudentCompetitionGuidancePerformance!=null){
					tfStudentCompetitionGuidancePerformance.setCheckOut("2");
					checkoutList.add(tfStudentCompetitionGuidancePerformance);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tfstudentCompetitionGuidancePerformanceDAO.updateCheckoutStatus(checkoutList)) {
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
	private int pageSize_SCG;
	private String termId_SCG;
	private Department department_SCG;
	private int operstatus;
	private String checkOutStatus_SCG;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfstudentCompetitionGuidancePerformanceDAO tfstudentCompetitionGuidancePerformanceDAO=new TfstudentCompetitionGuidancePerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_SCG() {
		return pageSize_SCG;
	}
	public void setPageSize_SCG(int pageSize_SCG) {
		this.pageSize_SCG = pageSize_SCG;
		session.put("pageSize_SCG", pageSize_SCG);
	}
	public String getTermId_SCG() {
		return termId_SCG;
	}
	public void setTermId_SCG(String termId_SCG) {
		this.termId_SCG = termId_SCG;
		session.put("termId_SCG", termId_SCG);
	}
	public Department getDepartment_SCG() {
		return department_SCG;
	}
	public void setDepartment_SCG(Department department_SCG) {
		this.department_SCG = department_SCG;
		session.put("department_SCG", department_SCG);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_SCG() {
		return checkOutStatus_SCG;
	}
	public void setCheckOutStatus_SCG(String checkOutStatus_SCG) {
		this.checkOutStatus_SCG = checkOutStatus_SCG;
		session.put("checkOutStatus_SCG", checkOutStatus_SCG);
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
