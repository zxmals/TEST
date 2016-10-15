package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfprofessionalProjectDeclarePerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfprofessionalProjectDeclarePerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfprofessionalProjectDeclarePerformanceAuditAction implements RequestAware{
	public String getTfprofessionalProjectDeclarePerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_PPD") == null) {
			session.put("department_PPD", new Department());
		}
		if ((Integer) session.get("pageSize_PPD") == null) {
			session.put("pageSize_PPD", 1);
		}
		try {
			this.request
					.put("TfprofessionalProjectDeclarePerformanceList",
							this.tfprofessionalProjectDeclarePerformanceDAO.getTfProfessionalProjectDeclarePerfList(pageIndex,
									(Integer) session.get("pageSize_PPD"),
									(String) session.get("termId_PPD"),
									(Department) session.get("department_PPD"),
									(String) session.get("checkOutStatus_PPD"),
									isDivided));
									
			tx = this.tfprofessionalProjectDeclarePerformanceDAO.getSession().beginTransaction();
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
		List<TfprofessionalProjectDeclarePerformance> checkoutList=new ArrayList<TfprofessionalProjectDeclarePerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfprofessionalProjectDeclarePerformance TfprofessionalProjectDeclarePerformance = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				TfprofessionalProjectDeclarePerformance = this.tfprofessionalProjectDeclarePerformanceDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(TfprofessionalProjectDeclarePerformance!=null){
					TfprofessionalProjectDeclarePerformance.setCheckOut("1");
					checkoutList.add(TfprofessionalProjectDeclarePerformance);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				TfprofessionalProjectDeclarePerformance=this.tfprofessionalProjectDeclarePerformanceDAO.findById(Integer.parseInt(idsNot[i]));
				if(TfprofessionalProjectDeclarePerformance!=null){
					TfprofessionalProjectDeclarePerformance.setCheckOut("2");
					checkoutList.add(TfprofessionalProjectDeclarePerformance);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tfprofessionalProjectDeclarePerformanceDAO.updateCheckoutStatus(checkoutList)) {
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
	private int pageSize_PPD;
	private String termId_PPD;
	private Department department_PPD;
	private int operstatus;
	private String checkOutStatus_PPD;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfprofessionalProjectDeclarePerformanceDAO tfprofessionalProjectDeclarePerformanceDAO=new TfprofessionalProjectDeclarePerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_PPD() {
		return pageSize_PPD;
	}
	public void setPageSize_PPD(int pageSize_PPD) {
		this.pageSize_PPD = pageSize_PPD;
		session.put("pageSize_PPD", pageSize_PPD);
	}
	public String getTermId_PPD() {
		return termId_PPD;
	}
	public void setTermId_PPD(String termId_PPD) {
		this.termId_PPD = termId_PPD;
		session.put("termId_PPD", termId_PPD);
	}
	public Department getDepartment_PPD() {
		return department_PPD;
	}
	public void setDepartment_PPD(Department department_PPD) {
		this.department_PPD = department_PPD;
		session.put("department_PPD", department_PPD);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_PPD() {
		return checkOutStatus_PPD;
	}
	public void setCheckOutStatus_PPD(String checkOutStatus_PPD) {
		this.checkOutStatus_PPD = checkOutStatus_PPD;
		session.put("checkOutStatus_PPD", checkOutStatus_PPD);
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
