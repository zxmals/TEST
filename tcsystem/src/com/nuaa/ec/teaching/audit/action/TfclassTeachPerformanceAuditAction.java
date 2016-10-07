package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfclassTeachPefromanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfclassTeachPefromance;
import com.opensymphony.xwork2.ActionContext;

public class TfclassTeachPerformanceAuditAction implements RequestAware {
	public void doCheckOutTask() {
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		List<TfclassTeachPefromance> checkoutList = new ArrayList<TfclassTeachPefromance>();
		TfclassTeachPefromance TfClassTeachPefromance = null;
		for (int i = 0; i < ids.length; i++) {
			TfClassTeachPefromance = this.TFClassTeachPefroDAO.findById(ids[i]);
			// 修改checkout 标志
			if(TfClassTeachPefromance!=null){
				TfClassTeachPefromance.setCheckOut("1");
				checkoutList.add(TfClassTeachPefromance);
			}
		}
		for(int i=0;i<idsNot.length;i++){
			TfClassTeachPefromance=this.TFClassTeachPefroDAO.findById(idsNot[i]);
			if(TfClassTeachPefromance!=null){
				TfClassTeachPefromance.setCheckOut("2");
				checkoutList.add(TfClassTeachPefromance);
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TFClassTeachPefroDAO.updateCheckoutStatus(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getTF_classTeachPerformanceListAfterDivide(){
		Transaction tx = this.TFClassTeachPefroDAO.getSession()
				.beginTransaction();
		if ((Department) session.get("department_CT") == null) {
			session.put("department_CT", new Department());
		}
		if ((Integer) session.get("pageSize_CT") == null) {
			session.put("pageSize_CT", 1);
		}
		try {
			this.request.put("TFClassTeachPefroList", this.TFClassTeachPefroDAO
					.getTFClassTeachPefro(pageIndex,
							(Integer) session.get("pageSize_CT"),
							(String) session.get("termId_CT"),
							(Department) session.get("department_CT"),
							(String) session.get("checkOutStatus_CT"), 
							true));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}
	public String getTF_classTeachPerformanceList() {
		Transaction tx = this.TFClassTeachPefroDAO.getSession()
				.beginTransaction();
		if ((Department) session.get("department_CT") == null) {
			session.put("department_CT", new Department());
		}
		if ((Integer) session.get("pageSize_CT") == null) {
			session.put("pageSize_CT", 1);
		}
		try {
			this.request.put("TFClassTeachPefroList", this.TFClassTeachPefroDAO
					.getTFClassTeachPefro(pageIndex,
							(Integer) session.get("pageSize_CT"),
							(String) session.get("termId_CT"),
							(Department) session.get("department_CT"),
							(String) session.get("checkOutStatus_CT"), 
							false));
			tx.commit();
			this.setOperstatus(1);
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
	private int pageSize_CT;
	private String termId_CT;
	private Department department_CT;
	private int operstatus;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private Map<String, Object> request;
	private TfclassTeachPefromanceDAO TFClassTeachPefroDAO = new TfclassTeachPefromanceDAO();
	private String checkOutStatus_CT;
	private String checkOutIDs;
	public String checkOutIDsNot;
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_CT() {
		return pageSize_CT;
	}

	public void setPageSize_CT(int pageSize_CT) {
		this.pageSize_CT = pageSize_CT;
		session.put("pageSize_CT", pageSize_CT);
	}



	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getCheckOutStatus_CT() {
		return checkOutStatus_CT;
	}

	public void setCheckOutStatus_CT(String checkOutStatus_CT) {
		this.checkOutStatus_CT = checkOutStatus_CT;
		session.put("checkOutStatus_CT", checkOutStatus_CT);
	}

	public String getCheckOutIDs() {
		return checkOutIDs;
	}

	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}

	public String getTermId_CT() {
		return termId_CT;
	}

	public void setTermId_CT(String termId_CT) {
		this.termId_CT = termId_CT;
		session.put("termId_CT", termId_CT);
	}
	public Department getDepartment_CT() {
		return department_CT;
	}

	public void setDepartment_CT(Department department_CT) {
		this.department_CT = department_CT;
		session.put("department_CT", department_CT);
	}
	public String getCheckOutIDsNot() {
		return checkOutIDsNot;
	}
	public void setCheckOutIDsNot(String checkOutIDsNot) {
		this.checkOutIDsNot = checkOutIDsNot;
	}



}
