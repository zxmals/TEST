package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfjoinStudentActivityPerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfjoinStudentActivityPerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfjoinStudentActivityPerformanceAuditAction implements RequestAware{
	public String getTfStudentActivityPerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_JSA") == null) {
			session.put("department_JSA", new Department());
		}
		if ((Integer) session.get("pageSize_JSA") == null) {
			session.put("pageSize_JSA", 1);
		}
		try {
			this.request
					.put("TfJoinStudentActivityPerformanceList",
							this.tfjoinStudentActivityPerformanceDAO.getTfJoinStudentActivityPerformanceListToBeAudited(pageIndex,
									(Integer) session.get("pageSize_JSA"),
									(String) session.get("termId_JSA"),
									(Department) session.get("department_JSA"),
									(String) session.get("checkOutStatus_JSA"),
									isDivided));
			tx = this.tfjoinStudentActivityPerformanceDAO.getSession().beginTransaction();
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
		List<TfjoinStudentActivityPerformance> checkoutList=new ArrayList<TfjoinStudentActivityPerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfjoinStudentActivityPerformance tfJoinStudentActivityPerformance = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				tfJoinStudentActivityPerformance = this.tfjoinStudentActivityPerformanceDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(tfJoinStudentActivityPerformance!=null){
					tfJoinStudentActivityPerformance.setCheckOut("1");
					checkoutList.add(tfJoinStudentActivityPerformance);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				tfJoinStudentActivityPerformance=this.tfjoinStudentActivityPerformanceDAO.findById(Integer.parseInt(idsNot[i]));
				if(tfJoinStudentActivityPerformance!=null){
					tfJoinStudentActivityPerformance.setCheckOut("2");
					checkoutList.add(tfJoinStudentActivityPerformance);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tfjoinStudentActivityPerformanceDAO.updateCheckoutStatus(checkoutList)) {
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
	private int pageSize_JSA;
	private String termId_JSA;
	private Department department_JSA;
	private int operstatus;
	private String checkOutStatus_JSA;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfjoinStudentActivityPerformanceDAO tfjoinStudentActivityPerformanceDAO=new TfjoinStudentActivityPerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_JSA() {
		return pageSize_JSA;
	}
	public void setPageSize_JSA(int pageSize_JSA) {
		this.pageSize_JSA = pageSize_JSA;
		session.put("pageSize_JSA", pageSize_JSA);
	}
	public String getTermId_JSA() {
		return termId_JSA;
	}
	public void setTermId_JSA(String termId_JSA) {
		this.termId_JSA = termId_JSA;
		session.put("termId_JSA", termId_JSA);
	}
	public Department getDepartment_JSA() {
		return department_JSA;
	}
	public void setDepartment_JSA(Department department_JSA) {
		this.department_JSA = department_JSA;
		session.put("department_JSA", department_JSA);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_JSA() {
		return checkOutStatus_JSA;
	}
	public void setCheckOutStatus_JSA(String checkOutStatus_JSA) {
		this.checkOutStatus_JSA = checkOutStatus_JSA;
		session.put("checkOutStatus_JSA", checkOutStatus_JSA);
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
