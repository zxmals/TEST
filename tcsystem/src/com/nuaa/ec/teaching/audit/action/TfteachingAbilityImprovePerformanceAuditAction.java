package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfteachingAbilityImprovePerformance;
import com.opensymphony.xwork2.ActionContext;
import com.nuaa.ec.dao.TfteachingAbilityImprovePerformanceDAO;

public class TfteachingAbilityImprovePerformanceAuditAction implements RequestAware{
	public void doCheckOutTask() {
		List<TfteachingAbilityImprovePerformance> checkoutList=new ArrayList<TfteachingAbilityImprovePerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfteachingAbilityImprovePerformance TfteachingCompetitionPerfList = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				TfteachingCompetitionPerfList = this.TfteachingAbilityImproProfDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(TfteachingCompetitionPerfList!=null){
					TfteachingCompetitionPerfList.setCheckOut("1");
					checkoutList.add(TfteachingCompetitionPerfList);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				TfteachingCompetitionPerfList=this.TfteachingAbilityImproProfDAO.findById(Integer.parseInt(idsNot[i]));
				if(TfteachingCompetitionPerfList!=null){
					TfteachingCompetitionPerfList.setCheckOut("2");
					checkoutList.add(TfteachingCompetitionPerfList);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TfteachingAbilityImproProfDAO.updateCheckoutStatus(checkoutList)) {
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
	public String getTeachingAbilityImprovePerfList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_TAI") == null) {
			session.put("department_TAI", new Department());
		}
		if ((Integer) session.get("pageSize_TAI") == null) {
			session.put("pageSize_TAI", 1);
		}
		try {
			this.request
					.put("TfteachingAbilityImprovePerfList",
							this.TfteachingAbilityImproProfDAO.getTFTeachingAbilityImprovePefroList(pageIndex,
									(Integer) session.get("pageSize_TAI"),
									(String) session.get("termId_TAI"),
									(Department) session.get("department_TAI"),
									(String) session.get("checkOutStatus_TAI"),
									isDivided));
									
			tx = this.TfteachingAbilityImproProfDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		} finally {
//			this.TfteachingAbilityImproProfDAO.closeSession();
		}
		return "success";
	}
	public String execute() throws Exception{
		return "success";
	}
	private int pageIndex = 1;
	private int pageSize_TAI;
	private String termId_TAI;
	private Department department_TAI;
	private int operstatus;
	private String checkOutStatus_TAI;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfteachingAbilityImprovePerformanceDAO TfteachingAbilityImproProfDAO=new TfteachingAbilityImprovePerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_TAI() {
		return pageSize_TAI;
	}
	public void setPageSize_TAI(int pageSize_TAI) {
		this.pageSize_TAI = pageSize_TAI;
		session.put("pageSize_TAI", pageSize_TAI);
	}
	public String getTermId_TAI() {
		return termId_TAI;
	}
	public void setTermId_TAI(String termId_TAI) {
		this.termId_TAI = termId_TAI;
		session.put("termId_TAI", termId_TAI);
	}
	public Department getDepartment_TAI() {
		return department_TAI;
	}
	public void setDepartment_TAI(Department department_TAI) {
		this.department_TAI = department_TAI;
		session.put("department_TAI", department_TAI);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_TAI() {
		return checkOutStatus_TAI;
	}
	public void setCheckOutStatus_TAI(String checkOutStatus_TAI) {
		this.checkOutStatus_TAI = checkOutStatus_TAI;
		session.put("checkOutStatus_TAI", checkOutStatus_TAI);
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
