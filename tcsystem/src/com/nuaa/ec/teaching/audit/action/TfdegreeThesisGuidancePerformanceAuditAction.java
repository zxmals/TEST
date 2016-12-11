package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfdegreeThesisGuidancePerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfdegreeThesisGuidancePerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfdegreeThesisGuidancePerformanceAuditAction implements
		RequestAware {
	public void doCheckOutTask() {
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		List<TfdegreeThesisGuidancePerformance> checkoutList = new ArrayList<TfdegreeThesisGuidancePerformance>();
		TfdegreeThesisGuidancePerformance TfDegreeThesisGuidancePerf = null;
		for (int i = 0; i < ids.length; i++) {
			TfDegreeThesisGuidancePerf = this.TfdegreeThesisGuidancePerfDAO.findById(ids[i]);
			// 修改checkout 标志
			if(TfDegreeThesisGuidancePerf!=null){
				TfDegreeThesisGuidancePerf.setCheckOut("3");
				checkoutList.add(TfDegreeThesisGuidancePerf);
			}
		}
		for(int i=0;i<idsNot.length;i++){
			TfDegreeThesisGuidancePerf=this.TfdegreeThesisGuidancePerfDAO.findById(idsNot[i]);
			if(TfDegreeThesisGuidancePerf!=null){
				TfDegreeThesisGuidancePerf.setCheckOut("2");
				checkoutList.add(TfDegreeThesisGuidancePerf);
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TfdegreeThesisGuidancePerfDAO.updateCheckoutStatus(checkoutList)) {
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
	public String getTfDegreeThesisGuidancePerformListAfterDivide() {
		Transaction tx = null;
		if ((Department) session.get("department_DTG") == null) {
			session.put("department_DTG", new Department());
		}
		if ((Integer) session.get("pageSize_DTG") == null) {
			session.put("pageSize_DTG", 1);
		}
		try {
			this.request.put("TfDegreeThesisGuidancePerfList",
					this.TfdegreeThesisGuidancePerfDAO
					.getTFdegreeThesisGuidancePefro(pageIndex,
							(Integer) session.get("pageSize_DTG"),
							(String) session.get("termId_DTG"),
							(Department) session.get("department_DTG"),
							(String) session.get("checkOutStatus_DTG"),
							true));
			tx = this.TfdegreeThesisGuidancePerfDAO.getSession()
					.beginTransaction();
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}
	public String getTfDegreeThesisGuidancePerformList() {
		Transaction tx = this.TfdegreeThesisGuidancePerfDAO.getSession()
				.beginTransaction();
		if ((Department) session.get("department_DTG") == null) {
			session.put("department_DTG", new Department());
		}
		if ((Integer) session.get("pageSize_DTG") == null) {
			session.put("pageSize_DTG", 1);
		}
		try {
			this.request.put("TfDegreeThesisGuidancePerfList",
					this.TfdegreeThesisGuidancePerfDAO
							.getTFdegreeThesisGuidancePefro(pageIndex,
									(Integer) session.get("pageSize_DTG"),
									(String) session.get("termId_DTG"),
									(Department) session.get("department_DTG"),
									(String) session.get("checkOutStatus_DTG"),
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
	private int pageSize_DTG;
	private String termId_DTG;
	private Department department_DTG;
	private int operstatus;
	private String checkOutStatus_DTG;
	private String checkOutIDs;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private TfdegreeThesisGuidancePerformanceDAO TfdegreeThesisGuidancePerfDAO = new TfdegreeThesisGuidancePerformanceDAO();
	private String checkOutIDsNot;
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
		session.put("pageSize_DTG", pageSize_DTG);
	}

	public String getTermId_DTG() {
		return termId_DTG;
	}

	public void setTermId_DTG(String termId_DTG) {
		this.termId_DTG = termId_DTG;
		session.put("termId_DTG", termId_DTG);
	}

	public Department getDepartment_DTG() {
		return department_DTG;
	}

	public void setDepartment_DTG(Department department_DTG) {
		this.department_DTG = department_DTG;
		session.put("department_DTG", department_DTG);
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
		session.put("checkOutStatus_DTG", checkOutStatus_DTG);
	}

	public String getCheckOutIDs() {
		return checkOutIDs;
	}

	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public String getCheckOutIDsNot() {
		return checkOutIDsNot;
	}
	public void setCheckOutIDsNot(String checkOutIDsNot) {
		this.checkOutIDsNot = checkOutIDsNot;
	}
}
