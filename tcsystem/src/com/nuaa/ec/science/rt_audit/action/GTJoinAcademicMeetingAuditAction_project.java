package com.nuaa.ec.science.rt_audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.JoinAcademicMeetingDAO;
import com.nuaa.ec.model.JoinAcademicMeeting;
import com.nuaa.ec.model.Teacher;
import com.opensymphony.xwork2.ActionContext;

public class GTJoinAcademicMeetingAuditAction_project implements RequestAware{
	/**
	 * 审核团队
	 */
	public void doCheckOut_project() {
		List<JoinAcademicMeeting> checkoutList = new ArrayList<JoinAcademicMeeting>();
		List<JoinAcademicMeeting> checkoutNotList = new ArrayList<JoinAcademicMeeting>();
		List<JoinAcademicMeeting> checkoutYesList = new ArrayList<JoinAcademicMeeting>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot = this.checkOutIDsNot.split(",");
		JoinAcademicMeeting joinAcademicMeeting = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] != null && ids[i].length() != 0) {
				joinAcademicMeeting = this.joinAcademicMeetingDAO
						.findById(ids[i]);
				// 修改checkout 标志
				if (joinAcademicMeeting != null) {
					joinAcademicMeeting.setCheckout("1");
					checkoutList.add(joinAcademicMeeting);
					checkoutYesList.add(joinAcademicMeeting);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				joinAcademicMeeting = this.joinAcademicMeetingDAO
						.findById(idsNot[i]);
				if (joinAcademicMeeting != null) {
					joinAcademicMeeting.setCheckout("2");
					checkoutList.add(joinAcademicMeeting);
					checkoutNotList.add(joinAcademicMeeting);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (joinAcademicMeetingDAO.updateCheckoutStatus(checkoutList)
					&& joinAcademicMeetingDAO
							.cascadeUpdateCheckOutOfMembers(checkoutNotList,
									"2")
					&& joinAcademicMeetingDAO
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
		if (session.get("pageSize_GTJAM") != null) {
			pageSize_s = (String) (session.get("pageSize_GTJAM") + "");
			if (pageSize_s != null && pageSize_s.trim().length() != 0) {
				pageSize_GTJAM = Integer.parseInt(pageSize_s);
			}
		}
		try {
			this.request.put("joinAcademicMeetingList", this.joinAcademicMeetingDAO
					.getAllRecordsWithCondition(pageIndex, pageSize_GTJAM,
							(String) session.get("foredate_GTJAM"),
							(String) session.get("afterdate_GTJAM"),
							((Teacher) session.get("teacher")).getResearchLab(),
							(String) session.get("checkout_GTJAM"), isDivided));
			tx = this.joinAcademicMeetingDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}
	private String foredate_GTJAM;
	private String afterdate_GTJAM;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize_GTJAM = 1;
	private String checkout_GTJAM = "0";
	private String checkOutIDs;
	private String checkOutIDsNot;
	private String isDivided;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private JoinAcademicMeetingDAO joinAcademicMeetingDAO=new JoinAcademicMeetingDAO();
	public String getForedate_GTJAM() {
		return foredate_GTJAM;
	}
	public void setForedate_GTJAM(String foredate_GTJAM) {
		this.foredate_GTJAM = foredate_GTJAM;
		session.put("foredate_GTJAM", foredate_GTJAM);
	}
	public String getAfterdate_GTJAM() {
		return afterdate_GTJAM;
	}
	public void setAfterdate_GTJAM(String afterdate_GTJAM) {
		this.afterdate_GTJAM = afterdate_GTJAM;
		session.put("afterdate_GTJAM", afterdate_GTJAM);
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
	public int getPageSize_GTJAM() {
		return pageSize_GTJAM;
	}
	public void setPageSize_GTJAM(int pageSize_GTJAM) {
		this.pageSize_GTJAM = pageSize_GTJAM;
		session.put("pageSize_GTJAM", pageSize_GTJAM);
	}
	public String getCheckout_GTJAM() {
		return checkout_GTJAM;
	}
	public void setCheckout_GTJAM(String checkout_GTJAM) {
		this.checkout_GTJAM = checkout_GTJAM;
		session.put("checkout_GTJAM", checkout_GTJAM);
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
