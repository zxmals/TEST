package com.nuaa.ec.science.rt_audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TalentProjectDAO;
import com.nuaa.ec.model.TalentProject;
import com.nuaa.ec.model.Teacher;
import com.opensymphony.xwork2.ActionContext;


public class GTSelectedTalentProjectAuditAction_project implements RequestAware{
	/**
	 * 审核团队
	 */
	public void doCheckOut_project() {
		List<TalentProject> checkoutList = new ArrayList<TalentProject>();
		List<TalentProject> checkoutNotList = new ArrayList<TalentProject>();
		List<TalentProject> checkoutYesList = new ArrayList<TalentProject>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot = this.checkOutIDsNot.split(",");
		TalentProject talentProject = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] != null && ids[i].length() != 0) {
				talentProject = this.talentProjectDAO
						.findById(ids[i]);
				// 修改checkout 标志
				if (talentProject != null) {
					talentProject.setCheckout("1");
					checkoutList.add(talentProject);
					checkoutYesList.add(talentProject);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				talentProject = this.talentProjectDAO
						.findById(idsNot[i]);
				if (talentProject != null) {
					talentProject.setCheckout("2");
					checkoutList.add(talentProject);
					checkoutNotList.add(talentProject);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (talentProjectDAO.updateCheckoutStatus(checkoutList)
					&& talentProjectDAO
							.cascadeUpdateCheckOutOfMembers(checkoutNotList,
									"2")
					&& talentProjectDAO
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
		if (session.get("pageSize_GTTP") != null) {
			pageSize_s = (String) (session.get("pageSize_GTTP") + "");
			if (pageSize_s != null && pageSize_s.trim().length() != 0) {
				pageSize_GTTP = Integer.parseInt(pageSize_s);
			}
		}
		try {
			this.request.put("talentProjectList", this.talentProjectDAO
					.getAllRecordsWithCondition(pageIndex, pageSize_GTTP,
							(String) session.get("foredate_GTTP"),
							(String) session.get("afterdate_GTTP"),
							((Teacher) session.get("teacher")).getResearchLab(),
							(String) session.get("checkout_GTTP"), isDivided));
			tx = this.talentProjectDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}
	private String foredate_GTTP;
	private String afterdate_GTTP;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize_GTTP = 1;
	private String checkout_GTTP = "0";
	private String checkOutIDs;
	private String checkOutIDsNot;
	private String isDivided;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private TalentProjectDAO talentProjectDAO=new TalentProjectDAO();
	public String getForedate_GTTP() {
		return foredate_GTTP;
	}
	public void setForedate_GTTP(String foredate_GTTP) {
		this.foredate_GTTP = foredate_GTTP;
		session.put("foredate_GTTP", foredate_GTTP);
	}
	public String getAfterdate_GTTP() {
		return afterdate_GTTP;
	}
	public void setAfterdate_GTTP(String afterdate_GTTP) {
		this.afterdate_GTTP = afterdate_GTTP;
		session.put("afterdate_GTTP", afterdate_GTTP);
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
	public int getPageSize_GTTP() {
		return pageSize_GTTP;
	}
	public void setPageSize_GTTP(int pageSize_GTTP) {
		this.pageSize_GTTP = pageSize_GTTP;
		session.put("pageSize_GTTP", pageSize_GTTP);
	}
	public String getCheckout_GTTP() {
		return checkout_GTTP;
	}
	public void setCheckout_GTTP(String checkout_GTTP) {
		this.checkout_GTTP = checkout_GTTP;
		session.put("checkout_GTTP", checkout_GTTP);
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
