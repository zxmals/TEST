package com.nuaa.ec.science.rt_audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.AcademicWorkDAO;
import com.nuaa.ec.model.AcademicWork;
import com.nuaa.ec.model.Teacher;
import com.opensymphony.xwork2.ActionContext;

public class GTAcademicWorkAuditAction_project implements RequestAware{
	/**
	 * 审核团队
	 */
	public void doCheckOut_project() {
		List<AcademicWork> checkoutList = new ArrayList<AcademicWork>();
		List<AcademicWork> checkoutNotList = new ArrayList<AcademicWork>();
		List<AcademicWork> checkoutYesList = new ArrayList<AcademicWork>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot = this.checkOutIDsNot.split(",");
		AcademicWork academicWork = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] != null && ids[i].length() != 0) {
				academicWork = this.academicWorkDAO
						.findById(ids[i]);
				// 修改checkout 标志
				if (academicWork != null) {
					academicWork.setCheckout("1");
					checkoutList.add(academicWork);
					checkoutYesList.add(academicWork);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				academicWork = this.academicWorkDAO
						.findById(idsNot[i]);
				if (academicWork != null) {
					academicWork.setCheckout("2");
					checkoutList.add(academicWork);
					checkoutNotList.add(academicWork);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (academicWorkDAO.updateCheckoutStatus(checkoutList)
					&& academicWorkDAO
							.cascadeUpdateCheckOutOfMembers(checkoutNotList,
									"2")
					&& academicWorkDAO
							.cascadeUpdateCheckOutOfMembers(checkoutYesList, "1")) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取出所有符合查询条件的记录
	 * @return
	 */
	public String getAllRecord(){
		Transaction tx = null;
		boolean isDivided = false;
		if (this.isDivided != null && this.isDivided.trim().equals("true")) {
			isDivided = true;
		}
		String pageSize_s = null;
		if (session.get("pageSize_GTAW") != null) {
			pageSize_s = (String) (session.get("pageSize_GTAW") + "");
			if (pageSize_s != null && pageSize_s.trim().length() != 0) {
				pageSize_GTAW = Integer.parseInt(pageSize_s);
			}
		}
		try {
			this.request.put("academicWorkList", this.academicWorkDAO
					.getAllRecordsWithCondition(pageIndex, pageSize_GTAW,
							(String) session.get("foredate_GTAW"),
							(String) session.get("afterdate_GTAW"),
							((Teacher) session.get("teacher")).getResearchLab(),
							(String) session.get("checkout_GTAW"), isDivided));
			tx = this.academicWorkDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}
	private String foredate_GTAW;
	private String afterdate_GTAW;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize_GTAW = 1;
	private String checkout_GTAW = "0";
	private String checkOutIDs;
	private String checkOutIDsNot;
	private String isDivided;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private AcademicWorkDAO academicWorkDAO=new AcademicWorkDAO();
	public String getForedate_GTAW() {
		return foredate_GTAW;
	}
	public void setForedate_GTAW(String foredate_GTAW) {
		this.foredate_GTAW = foredate_GTAW;
		session.put("foredate_GTAW", foredate_GTAW);
	}
	public String getAfterdate_GTAW() {
		return afterdate_GTAW;
	}
	public void setAfterdate_GTAW(String afterdate_GTAW) {
		this.afterdate_GTAW = afterdate_GTAW;
		session.put("afterdate_GTAW", afterdate_GTAW);
	}
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_GTAW() {
		return pageSize_GTAW;
	}
	public void setPageSize_GTAW(int pageSize_GTAW) {
		this.pageSize_GTAW = pageSize_GTAW;
		session.put("pageSize_GTAW", pageSize_GTAW);
	}
	public String getCheckout_GTAW() {
		return checkout_GTAW;
	}
	public void setCheckout_GTAW(String checkout_GTAW) {
		this.checkout_GTAW = checkout_GTAW;
		session.put("checkout_GTAW", checkout_GTAW);
	}
	public String getCheckOutIDs() {
		return checkOutIDs;
	}
	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}
	public String getCheckOutIDsNot() {
		return checkOutIDsNot;
	}
	public void setCheckOutIDsNot(String checkOutIDsNot) {
		this.checkOutIDsNot = checkOutIDsNot;
	}
	public String getIsDivided() {
		return isDivided;
	}
	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}
}
