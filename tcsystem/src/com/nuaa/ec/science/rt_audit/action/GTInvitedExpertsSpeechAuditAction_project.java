package com.nuaa.ec.science.rt_audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.InvitedExpertsSpeechDAO;
import com.nuaa.ec.model.InvitedExpertsSpeech;
import com.nuaa.ec.model.Teacher;
import com.opensymphony.xwork2.ActionContext;

public class GTInvitedExpertsSpeechAuditAction_project implements RequestAware{
	/**
	 * 审核团队
	 */
	public void doCheckOut_project() {
		List<InvitedExpertsSpeech> checkoutList = new ArrayList<InvitedExpertsSpeech>();
		List<InvitedExpertsSpeech> checkoutNotList = new ArrayList<InvitedExpertsSpeech>();
		List<InvitedExpertsSpeech> checkoutYesList = new ArrayList<InvitedExpertsSpeech>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot = this.checkOutIDsNot.split(",");
		InvitedExpertsSpeech invitedExpertsSpeech = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] != null && ids[i].length() != 0) {
				invitedExpertsSpeech = this.invitedExpertsSpeechDAO
						.findById(ids[i]);
				// 修改checkout 标志
				if (invitedExpertsSpeech != null) {
					invitedExpertsSpeech.setCheckout("1");
					checkoutList.add(invitedExpertsSpeech);
					checkoutYesList.add(invitedExpertsSpeech);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				invitedExpertsSpeech = this.invitedExpertsSpeechDAO
						.findById(idsNot[i]);
				if (invitedExpertsSpeech != null) {
					invitedExpertsSpeech.setCheckout("2");
					checkoutList.add(invitedExpertsSpeech);
					checkoutNotList.add(invitedExpertsSpeech);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (invitedExpertsSpeechDAO.updateCheckoutStatus(checkoutList)
					&& invitedExpertsSpeechDAO
							.cascadeUpdateCheckOutOfMembers(checkoutNotList,
									"2")
					&& invitedExpertsSpeechDAO
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
		if (session.get("pageSize_GTIES") != null) {
			pageSize_s = (String) (session.get("pageSize_GTIES") + "");
			if (pageSize_s != null && pageSize_s.trim().length() != 0) {
				pageSize_GTIES = Integer.parseInt(pageSize_s);
			}
		}
		try {
			this.request.put("InvitedExpertsSpeechList", this.invitedExpertsSpeechDAO
					.getAllRecordsWithCondition(pageIndex, pageSize_GTIES,
							(String) session.get("foredate_GTIES"),
							(String) session.get("afterdate_GTIES"),
							((Teacher) session.get("teacher")).getResearchLab(),
							(String) session.get("checkout_GTIES"), isDivided));
			tx = this.invitedExpertsSpeechDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}
	private String foredate_GTIES;
	private String afterdate_GTIES;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize_GTIES = 1;
	private String checkout_GTIES = "0";
	private String checkOutIDs;
	private String checkOutIDsNot;
	private String isDivided;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private InvitedExpertsSpeechDAO invitedExpertsSpeechDAO=new InvitedExpertsSpeechDAO();
	public String getForedate_GTIES() {
		return foredate_GTIES;
	}
	public void setForedate_GTIES(String foredate_GTIES) {
		this.foredate_GTIES = foredate_GTIES;
		session.put("foredate_GTIES", foredate_GTIES);
	}
	public String getAfterdate_GTIES() {
		return afterdate_GTIES;
	}
	public void setAfterdate_GTIES(String afterdate_GTIES) {
		this.afterdate_GTIES = afterdate_GTIES;
		session.put("afterdate_GTIES", afterdate_GTIES);
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
	public int getPageSize_GTIES() {
		return pageSize_GTIES;
	}
	public void setPageSize_GTIES(int pageSize_GTIES) {
		this.pageSize_GTIES = pageSize_GTIES;
		session.put("pageSize_GTIES", pageSize_GTIES);
	}
	public String getCheckout_GTIES() {
		return checkout_GTIES;
	}
	public void setCheckout_GTIES(String checkout_GTIES) {
		this.checkout_GTIES = checkout_GTIES;
		session.put("checkout_GTIES", checkout_GTIES);
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


