package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TffamousTeacherTeamPerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TffamousTeacherTeamPerformance;
import com.opensymphony.xwork2.ActionContext;

public class TffamousTeacherTeamPerformanceAuditAction implements RequestAware{
	public void doCheckOutTask() {
		List<TffamousTeacherTeamPerformance> checkoutList=new ArrayList<TffamousTeacherTeamPerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TffamousTeacherTeamPerformance TfFamousTeacherTeamPerfList = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				TfFamousTeacherTeamPerfList = this.TfFamousTeacherTeamPerfDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(TfFamousTeacherTeamPerfList!=null){
					TfFamousTeacherTeamPerfList.setCheckOut("3");
					checkoutList.add(TfFamousTeacherTeamPerfList);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				TfFamousTeacherTeamPerfList=this.TfFamousTeacherTeamPerfDAO.findById(Integer.parseInt(idsNot[i]));
				if(TfFamousTeacherTeamPerfList!=null){
					TfFamousTeacherTeamPerfList.setCheckOut("2");
					checkoutList.add(TfFamousTeacherTeamPerfList);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TfFamousTeacherTeamPerfDAO.updateCheckoutStatus(checkoutList)) {
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
	public String getTfFamousTeacherTermPerfList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_FTT") == null) {
			session.put("department_FTT", new Department());
		}
		if ((Integer) session.get("pageSize_FTT") == null) {
			session.put("pageSize_FTT", 1);
		}
		try {
			this.request
					.put("TfFamousTeacherTeamPerfList",
							this.TfFamousTeacherTeamPerfDAO.getTFfamousTeacherTeamPefroList(pageIndex,(Integer) session.get("pageSize_FTT"),
									(String) session.get("termId_FTT"),
									(Department) session.get("department_FTT"),
									(String) session.get("checkOutStatus_FTT"),
									isDivided));
									
			tx = this.TfFamousTeacherTeamPerfDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		} 
		return "success";
	}
	public String execute() throws Exception {
		return "success";
	}
	private int pageIndex = 1;
	private int pageSize_FTT;
	private String termId_FTT;
	private Department department_FTT;
	private int operstatus;
	private String checkOutStatus_FTT;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TffamousTeacherTeamPerformanceDAO TfFamousTeacherTeamPerfDAO=new TffamousTeacherTeamPerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_FTT() {
		return pageSize_FTT;
	}
	public void setPageSize_FTT(int pageSize_FTT) {
		this.pageSize_FTT = pageSize_FTT;
		session.put("pageSize_FTT", pageSize_FTT);
	}
	public String getTermId_FTT() {
		return termId_FTT;
	}
	public void setTermId_FTT(String termId_FTT) {
		this.termId_FTT = termId_FTT;
		session.put("termId_FTT", termId_FTT);
	}
	public Department getDepartment_FTT() {
		return department_FTT;
	}
	public void setDepartment_FTT(Department department_FTT) {
		this.department_FTT = department_FTT;
		session.put("department_FTT", department_FTT);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_FTT() {
		return checkOutStatus_FTT;
	}
	public void setCheckOutStatus_FTT(String checkOutStatus_FTT) {
		this.checkOutStatus_FTT = checkOutStatus_FTT;
		session.put("checkOutStatus_FTT", checkOutStatus_FTT);
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
