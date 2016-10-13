package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfsummerCourseInternationalConstructionPerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance;
import com.opensymphony.xwork2.ActionContext;
public class TfsummerCourseInternationalConstructionPerformanceAuditAction implements RequestAware{
	public String getTfSummerAndInternationalCourseConstructionPerformanceList(){
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_SCI") == null) {
			session.put("department_SCI", new Department());
		}
		if ((Integer) session.get("pageSize_SCI") == null) {
			session.put("pageSize_SCI", 1);
		}
		try {
			this.request
					.put("TfSummerAndInternationalConstructionPerfList",
							this.tfsummerCourseInternationalConstructionPerformanceDAO.getTfSummerAndInternationalConstructionPerformanceListToBeAudited(pageIndex,
									(Integer) session.get("pageSize_SCI"),
									(String) session.get("termId_SCI"),
									(Department) session.get("department_SCI"),
									(String) session.get("checkOutStatus_SCI"),
									isDivided));
									
			tx = this.tfsummerCourseInternationalConstructionPerformanceDAO.getSession().beginTransaction();
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
		List<TfsummerCourseInternationalConstructionPerformance> checkoutList=new ArrayList<TfsummerCourseInternationalConstructionPerformance>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TfsummerCourseInternationalConstructionPerformance tfsummerCourseInternationalConstructionPerf = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				tfsummerCourseInternationalConstructionPerf = this.tfsummerCourseInternationalConstructionPerformanceDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(tfsummerCourseInternationalConstructionPerf!=null){
					tfsummerCourseInternationalConstructionPerf.setCheckOut("1");
					checkoutList.add(tfsummerCourseInternationalConstructionPerf);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				tfsummerCourseInternationalConstructionPerf=this.tfsummerCourseInternationalConstructionPerformanceDAO.findById(Integer.parseInt(idsNot[i]));
				if(tfsummerCourseInternationalConstructionPerf!=null){
					tfsummerCourseInternationalConstructionPerf.setCheckOut("2");
					checkoutList.add(tfsummerCourseInternationalConstructionPerf);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tfsummerCourseInternationalConstructionPerformanceDAO.updateCheckoutStatus(checkoutList)) {
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
	private int pageSize_SCI;
	private String termId_SCI;
	private Department department_SCI;
	private int operstatus;
	private String checkOutStatus_SCI;
	private String checkOutIDs;
	private Map<String,Object> request;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TfsummerCourseInternationalConstructionPerformanceDAO tfsummerCourseInternationalConstructionPerformanceDAO=new TfsummerCourseInternationalConstructionPerformanceDAO();
	private String isDivided;
	private String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_SCI() {
		return pageSize_SCI;
	}
	public void setPageSize_SCI(int pageSize_SCI) {
		this.pageSize_SCI = pageSize_SCI;
		session.put("pageSize_SCI", pageSize_SCI);
	}
	public String getTermId_SCI() {
		return termId_SCI;
	}
	public void setTermId_SCI(String termId_SCI) {
		this.termId_SCI = termId_SCI;
		session.put("termId_SCI", termId_SCI);
	}
	public Department getDepartment_SCI() {
		return department_SCI;
	}
	public void setDepartment_SCI(Department department_SCI) {
		this.department_SCI = department_SCI;
		session.put("department_SCI", department_SCI);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getCheckOutStatus_SCI() {
		return checkOutStatus_SCI;
	}
	public void setCheckOutStatus_SCI(String checkOutStatus_SCI) {
		this.checkOutStatus_SCI = checkOutStatus_SCI;
		session.put("checkOutStatus_SCI", checkOutStatus_SCI);
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
