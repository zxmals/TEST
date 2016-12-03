package com.nuaa.ec.science.rt_audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.MainUndertakeAcademicMeetingDAO;
import com.nuaa.ec.model.MainUndertakeAcademicMeeting;
import com.nuaa.ec.model.Teacher;
import com.opensymphony.xwork2.ActionContext;

public class GTMainUndertakeAcademicMeetingAuditAction_project implements RequestAware{
	/**
	 * 审核团队
	 */
	public void doCheckOut_project() {
		List<MainUndertakeAcademicMeeting> checkoutList = new ArrayList<MainUndertakeAcademicMeeting>();
		List<MainUndertakeAcademicMeeting> checkoutNotList = new ArrayList<MainUndertakeAcademicMeeting>();
		List<MainUndertakeAcademicMeeting> checkoutYesList = new ArrayList<MainUndertakeAcademicMeeting>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot = this.checkOutIDsNot.split(",");
		MainUndertakeAcademicMeeting mainUndertakeAcademicMeeting = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] != null && ids[i].length() != 0) {
				mainUndertakeAcademicMeeting = this.mainUndertakeAcademicMeetingDAO
						.findById(ids[i]);
				// 修改checkout 标志
				if (mainUndertakeAcademicMeeting != null) {
					mainUndertakeAcademicMeeting.setCheckout("1");
					checkoutList.add(mainUndertakeAcademicMeeting);
					checkoutYesList.add(mainUndertakeAcademicMeeting);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				mainUndertakeAcademicMeeting = this.mainUndertakeAcademicMeetingDAO
						.findById(idsNot[i]);
				if (mainUndertakeAcademicMeeting != null) {
					mainUndertakeAcademicMeeting.setCheckout("2");
					checkoutList.add(mainUndertakeAcademicMeeting);
					checkoutNotList.add(mainUndertakeAcademicMeeting);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (mainUndertakeAcademicMeetingDAO.updateCheckoutStatus(checkoutList)
					&& mainUndertakeAcademicMeetingDAO
							.cascadeUpdateCheckOutOfMembers(checkoutNotList,
									"2")
					&& mainUndertakeAcademicMeetingDAO
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
		if (session.get("pageSize_GTMUAM") != null) {
			pageSize_s = (String) (session.get("pageSize_GTMUAM") + "");
			if (pageSize_s != null && pageSize_s.trim().length() != 0) {
				pageSize_GTMUAM = Integer.parseInt(pageSize_s);
			}
		}
		try {
			this.request.put("mainUndertakeAcademicMeetingList", this.mainUndertakeAcademicMeetingDAO
					.getAllRecordsWithCondition(pageIndex, pageSize_GTMUAM,
							(String) session.get("foredate_GTMUAM"),
							(String) session.get("afterdate_GTMUAM"),
							((Teacher) session.get("teacher")).getResearchLab(),
							(String) session.get("checkout_GTMUAM"), isDivided));
			tx = this.mainUndertakeAcademicMeetingDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}
	private String foredate_GTMUAM;
	private String afterdate_GTMUAM;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize_GTMUAM = 1;
	private String checkout_GTMUAM = "0";
	private String checkOutIDs;
	private String checkOutIDsNot;
	private String isDivided;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private MainUndertakeAcademicMeetingDAO mainUndertakeAcademicMeetingDAO=new MainUndertakeAcademicMeetingDAO();
	public String getForedate_GTMUAM() {
		return foredate_GTMUAM;
	}
	public void setForedate_GTMUAM(String foredate_GTMUAM) {
		this.foredate_GTMUAM = foredate_GTMUAM;
		session.put("foredate_GTMUAM", foredate_GTMUAM);
	}
	public String getAfterdate_GTMUAM() {
		return afterdate_GTMUAM;
	}
	public void setAfterdate_GTMUAM(String afterdate_GTMUAM) {
		this.afterdate_GTMUAM = afterdate_GTMUAM;
		session.put("afterdate_GTMUAM", afterdate_GTMUAM);
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
	public int getPageSize_GTMUAM() {
		return pageSize_GTMUAM;
	}
	public void setPageSize_GTMUAM(int pageSize_GTMUAM) {
		this.pageSize_GTMUAM = pageSize_GTMUAM;
		session.put("pageSize_GTMUAM", pageSize_GTMUAM);
	}
	public String getCheckout_GTMUAM() {
		return checkout_GTMUAM;
	}
	public void setCheckout_GTMUAM(String checkout_GTMUAM) {
		this.checkout_GTMUAM = checkout_GTMUAM;
		session.put("checkout_GTMUAM", checkout_GTMUAM);
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
