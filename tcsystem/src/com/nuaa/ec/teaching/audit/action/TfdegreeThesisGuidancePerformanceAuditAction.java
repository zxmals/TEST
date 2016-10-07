package com.nuaa.ec.teaching.audit.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.dao.TfdegreeThesisGuidancePerformanceDAO;
import com.nuaa.ec.model.Department;
import com.opensymphony.xwork2.ActionContext;

public class TfdegreeThesisGuidancePerformanceAuditAction implements RequestAware{
	public String execute() throws Exception{
		return "success";
	}
	public String getTfDegreeThesisGuidancePerformList(){
		return "success";
	}
	private int pageIndex=1;
	private int pageSize_DTG;
	private String termId_DTG;
	private Department department_DTG;
	private int operstatus;
	private String checkOutStatus_DTG;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfdegreeThesisGuidancePerformanceDAO TfdegreeThesisGuidancePerfDAO=new TfdegreeThesisGuidancePerformanceDAO();
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_DTG() {
		return pageSize_DTG;
	}
	public void setPageSize_DTG(int pageSize_DTG) {
		this.pageSize_DTG = pageSize_DTG;
	}
	public String getTermId_DTG() {
		return termId_DTG;
	}
	public void setTermId_DTG(String termId_DTG) {
		this.termId_DTG = termId_DTG;
	}
	public Department getDepartment_DTG() {
		return department_DTG;
	}
	public void setDepartment_DTG(Department department_DTG) {
		this.department_DTG = department_DTG;
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_DTG() {
		return checkOutStatus_DTG;
	}
	public void setCheckOutStatus_DTG(String checkOutStatus_DTG) {
		this.checkOutStatus_DTG = checkOutStatus_DTG;
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
}
