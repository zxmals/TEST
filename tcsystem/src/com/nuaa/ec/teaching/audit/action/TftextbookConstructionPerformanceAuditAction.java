package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TftextbookConstructionPerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TftextbookConstructionPerformance;
import com.opensymphony.xwork2.ActionContext;

public class TftextbookConstructionPerformanceAuditAction implements RequestAware{
	public String getTftextbookConstructionPerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_TBC") == null) {
			session.put("department_TBC", new Department());
		}
		if ((Integer) session.get("pageSize_TBC") == null) {
			session.put("pageSize_TBC", 1);
		}
		try {
			this.request
					.put("TftextbookConstructionPerformanceList",
							this.tftextbookConstructionPerformanceDAO.getTftextbookConstructionPerfList(pageIndex,
									(Integer) session.get("pageSize_TBC"),
									(String) session.get("termId_TBC"),
									(Department) session.get("department_TBC"),
									(String) session.get("checkOutStatus_TBC"),
									isDivided));
									
			tx = this.tftextbookConstructionPerformanceDAO.getSession().beginTransaction();
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
		List<TftextbookConstructionPerformance> checkoutList=new ArrayList<TftextbookConstructionPerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TftextbookConstructionPerformance TftextbookConstructionPerformance = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				TftextbookConstructionPerformance = this.tftextbookConstructionPerformanceDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(TftextbookConstructionPerformance!=null){
					TftextbookConstructionPerformance.setCheckOut("1");
					checkoutList.add(TftextbookConstructionPerformance);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				TftextbookConstructionPerformance=this.tftextbookConstructionPerformanceDAO.findById(Integer.parseInt(idsNot[i]));
				if(TftextbookConstructionPerformance!=null){
					TftextbookConstructionPerformance.setCheckOut("2");
					checkoutList.add(TftextbookConstructionPerformance);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tftextbookConstructionPerformanceDAO.updateCheckoutStatus(checkoutList)) {
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
	private int pageSize_TBC;
	private String termId_TBC;
	private Department department_TBC;
	private int operstatus;
	private String checkOutStatus_TBC;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TftextbookConstructionPerformanceDAO tftextbookConstructionPerformanceDAO=new TftextbookConstructionPerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_TBC() {
		return pageSize_TBC;
	}
	public void setPageSize_TBC(int pageSize_TBC) {
		this.pageSize_TBC = pageSize_TBC;
		session.put("pageSize_TBC", pageSize_TBC);
	}
	public String getTermId_TBC() {
		return termId_TBC;
	}
	public void setTermId_TBC(String termId_TBC) {
		this.termId_TBC = termId_TBC;
		session.put("termId_TBC", termId_TBC);
	}
	public Department getDepartment_TBC() {
		return department_TBC;
	}
	public void setDepartment_TBC(Department department_TBC) {
		this.department_TBC = department_TBC;
		session.put("department_TBC", department_TBC);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_TBC() {
		return checkOutStatus_TBC;
	}
	public void setCheckOutStatus_TBC(String checkOutStatus_TBC) {
		this.checkOutStatus_TBC = checkOutStatus_TBC;
		session.put("checkOutStatus_TBC", checkOutStatus_TBC);
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
