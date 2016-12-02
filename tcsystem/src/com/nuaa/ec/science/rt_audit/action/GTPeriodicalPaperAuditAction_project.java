package com.nuaa.ec.science.rt_audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.PeriodicalPapersDAO;
import com.nuaa.ec.model.PeriodicalPapers;
import com.nuaa.ec.model.TalentProject;
import com.nuaa.ec.model.Teacher;
import com.opensymphony.xwork2.ActionContext;

public class GTPeriodicalPaperAuditAction_project implements RequestAware{
	/**
	 * 审核团队
	 */
	public void doCheckOut_project() {
		List<PeriodicalPapers> checkoutList = new ArrayList<PeriodicalPapers>();
		List<PeriodicalPapers> checkoutNotList = new ArrayList<PeriodicalPapers>();
		List<PeriodicalPapers> checkoutYesList = new ArrayList<PeriodicalPapers>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot = this.checkOutIDsNot.split(",");
		PeriodicalPapers periodicalPaper = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] != null && ids[i].length() != 0) {
				periodicalPaper = this.periodicalPapersDAO
						.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if (periodicalPaper != null) {
					periodicalPaper.setCheckout("1");
					checkoutList.add(periodicalPaper);
					checkoutYesList.add(periodicalPaper);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				periodicalPaper = this.periodicalPapersDAO
						.findById(Integer.parseInt(idsNot[i]));
				if (periodicalPaper != null) {
					periodicalPaper.setCheckout("2");
					checkoutList.add(periodicalPaper);
					checkoutNotList.add(periodicalPaper);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (periodicalPapersDAO.updateCheckoutStatus(checkoutList)
					&& periodicalPapersDAO
							.cascadeUpdateCheckOutOfMembers(checkoutNotList,
									"2")
					&& periodicalPapersDAO
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
		if (session.get("pageSize_GTPP") != null) {
			pageSize_s = (String) (session.get("pageSize_GTPP") + "");
			if (pageSize_s != null && pageSize_s.trim().length() != 0) {
				pageSize_GTPP = Integer.parseInt(pageSize_s);
			}
		}
		try {
			this.request.put("periodicalPapersList", this.periodicalPapersDAO
					.getAllRecordsWithCondition(pageIndex, pageSize_GTPP,
							(String) session.get("foredate_GTPP"),
							(String) session.get("afterdate_GTPP"),
							((Teacher) session.get("teacher")).getResearchLab(),
							(String) session.get("checkout_GTPP"), isDivided));
			tx = this.periodicalPapersDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}
	private String foredate_GTPP;
	private String afterdate_GTPP;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize_GTPP = 1;
	private String checkout_GTPP = "0";
	private String checkOutIDs;
	private String checkOutIDsNot;
	private String isDivided;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private PeriodicalPapersDAO periodicalPapersDAO=new PeriodicalPapersDAO();
	public String getForedate_GTPP() {
		return foredate_GTPP;
	}
	public void setForedate_GTPP(String foredate_GTPP) {
		this.foredate_GTPP = foredate_GTPP;
		session.put("foredate_GTPP", foredate_GTPP);
	}
	public String getAfterdate_GTPP() {
		return afterdate_GTPP;
	}
	public void setAfterdate_GTPP(String afterdate_GTPP) {
		this.afterdate_GTPP = afterdate_GTPP;
		session.put("afterdate_GTPP", afterdate_GTPP);
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
	public int getPageSize_GTPP() {
		return pageSize_GTPP;
	}
	public void setPageSize_GTPP(int pageSize_GTPP) {
		this.pageSize_GTPP = pageSize_GTPP;
		session.put("pageSize_GTPP", pageSize_GTPP);
	}
	public String getCheckout_GTPP() {
		return checkout_GTPP;
	}
	public void setCheckout_GTPP(String checkout_GTPP) {
		this.checkout_GTPP = checkout_GTPP;
		session.put("checkout_GTPP", checkout_GTPP);
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
