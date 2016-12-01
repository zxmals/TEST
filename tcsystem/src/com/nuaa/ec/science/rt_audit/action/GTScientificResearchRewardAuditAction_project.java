package com.nuaa.ec.science.rt_audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.ScientificResearchRewardDAO;
import com.nuaa.ec.model.ScientificResearchReward;
import com.nuaa.ec.model.Teacher;
import com.opensymphony.xwork2.ActionContext;

public class GTScientificResearchRewardAuditAction_project implements RequestAware{
	/**
	 * 审核团队
	 */
	public void doCheckOut_project() {
		List<ScientificResearchReward> checkoutList = new ArrayList<ScientificResearchReward>();
		List<ScientificResearchReward> checkoutNotList = new ArrayList<ScientificResearchReward>();
		List<ScientificResearchReward> checkoutYesList = new ArrayList<ScientificResearchReward>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot = this.checkOutIDsNot.split(",");
		ScientificResearchReward scienReschReward = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] != null && ids[i].length() != 0) {
				scienReschReward = this.scienReschRewardDAO
						.findById(ids[i]);
				// 修改checkout 标志
				if (scienReschReward != null) {
					scienReschReward.setCheckout("1");
					checkoutList.add(scienReschReward);
					checkoutYesList.add(scienReschReward);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				scienReschReward = this.scienReschRewardDAO
						.findById(idsNot[i]);
				if (scienReschReward != null) {
					scienReschReward.setCheckout("2");
					checkoutList.add(scienReschReward);
					checkoutNotList.add(scienReschReward);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (scienReschRewardDAO.updateCheckoutStatus(checkoutList)
					&& scienReschRewardDAO
							.cascadeUpdateCheckOutOfMembers(checkoutNotList,
									"2")
					&& scienReschRewardDAO
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

	public String getAllRecord(){
		Transaction tx = null;
		boolean isDivided = false;
		if (this.isDivided != null && this.isDivided.trim().equals("true")) {
			isDivided = true;
		}
		String pageSize_s = null;
		if (session.get("pageSize_GTSRR") != null) {
			pageSize_s = (String) (session.get("pageSize_GTSRR") + "");
			if (pageSize_s != null && pageSize_s.trim().length() != 0) {
				pageSize_GTSRR = Integer.parseInt(pageSize_s);
			}
		}
		try {
			this.request.put("scienReschRewardList", this.scienReschRewardDAO
					.getAllRecordsWithCondition(pageIndex, pageSize_GTSRR,
							(String) session.get("foredate_GTSRR"),
							(String) session.get("afterdate_GTSRR"),
							((Teacher) session.get("teacher")).getResearchLab(),
							(String) session.get("checkout_GTSRR"), isDivided));
			tx = this.scienReschRewardDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}
	private String foredate_GTSRR;
	private String afterdate_GTSRR;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize_GTSRR = 1;
	private String checkout_GTSRR;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private String isDivided;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private ScientificResearchRewardDAO scienReschRewardDAO=new ScientificResearchRewardDAO();
	public String getForedate_GTSRR() {
		return foredate_GTSRR;
	}
	public void setForedate_GTSRR(String foredate_GTSRR) {
		this.foredate_GTSRR = foredate_GTSRR;
		session.put("foredate_GTSRR", foredate_GTSRR);
	}
	public String getAfterdate_GTSRR() {
		return afterdate_GTSRR;
	}
	public void setAfterdate_GTSRR(String afterdate_GTSRR) {
		this.afterdate_GTSRR = afterdate_GTSRR;
		session.put("afterdate_GTSRR", afterdate_GTSRR);
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
	public int getPageSize_GTSRR() {
		return pageSize_GTSRR;
	}
	public void setPageSize_GTSRR(int pageSize_GTSRR) {
		this.pageSize_GTSRR = pageSize_GTSRR;
		session.put("pageSize_GTSRR", pageSize_GTSRR);
	}
	public String getCheckout_GTSRR() {
		return checkout_GTSRR;
	}
	public void setCheckout_GTSRR(String checkout_GTSRR) {
		this.checkout_GTSRR = checkout_GTSRR;
		session.put("checkout_GTSRR", checkout_GTSRR);
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
