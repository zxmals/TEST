package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TffineCourseConstructionPerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TffineCourseConstructionPerformance;
import com.opensymphony.xwork2.ActionContext;

public class TffineCourseConstructionPerformanceAuditAction implements RequestAware{
	public String getTffineCourseConstructionPerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_FCC") == null) {
			session.put("department_FCC", new Department());
		}
		if ((Integer) session.get("pageSize_FCC") == null) {
			session.put("pageSize_FCC", 1);
		}
		try {
			this.request
					.put("TfFineCourseConstructionPerformanceList",
							this.tffineCourseConstructionPerformanceDAO.getTffineCourseConstructionPerfList(pageIndex,
									(Integer) session.get("pageSize_FCC"),
									(String) session.get("termId_FCC"),
									(Department) session.get("department_FCC"),
									(String) session.get("checkOutStatus_FCC"),
									isDivided));
									
			tx = this.tffineCourseConstructionPerformanceDAO.getSession().beginTransaction();
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
		List<TffineCourseConstructionPerformance> checkoutList=new ArrayList<TffineCourseConstructionPerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TffineCourseConstructionPerformance tfFineCourseConstructionPerformance = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				tfFineCourseConstructionPerformance = this.tffineCourseConstructionPerformanceDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(tfFineCourseConstructionPerformance!=null){
					tfFineCourseConstructionPerformance.setCheckOut("1");
					checkoutList.add(tfFineCourseConstructionPerformance);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				tfFineCourseConstructionPerformance=this.tffineCourseConstructionPerformanceDAO.findById(Integer.parseInt(idsNot[i]));
				if(tfFineCourseConstructionPerformance!=null){
					tfFineCourseConstructionPerformance.setCheckOut("2");
					checkoutList.add(tfFineCourseConstructionPerformance);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tffineCourseConstructionPerformanceDAO.updateCheckoutStatus(checkoutList)) {
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
	private int pageSize_FCC;
	private String termId_FCC;
	private Department department_FCC;
	private int operstatus;
	private String checkOutStatus_FCC;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TffineCourseConstructionPerformanceDAO tffineCourseConstructionPerformanceDAO=new TffineCourseConstructionPerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_FCC() {
		return pageSize_FCC;
	}
	public void setPageSize_FCC(int pageSize_FCC) {
		this.pageSize_FCC = pageSize_FCC;
		session.put("pageSize_FCC", pageSize_FCC);
	}
	public String getTermId_FCC() {
		return termId_FCC;
	}
	public void setTermId_FCC(String termId_FCC) {
		this.termId_FCC = termId_FCC;
		session.put("termId_FCC", termId_FCC);
	}
	public Department getDepartment_FCC() {
		return department_FCC;
	}
	public void setDepartment_FCC(Department department_FCC) {
		this.department_FCC = department_FCC;
		session.put("department_FCC", department_FCC);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_FCC() {
		return checkOutStatus_FCC;
	}
	public void setCheckOutStatus_FCC(String checkOutStatus_FCC) {
		this.checkOutStatus_FCC = checkOutStatus_FCC;
		session.put("checkOutStatus_FCC", checkOutStatus_FCC);
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
