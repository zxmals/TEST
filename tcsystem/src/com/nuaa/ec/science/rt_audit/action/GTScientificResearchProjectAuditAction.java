package com.nuaa.ec.science.rt_audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.ScientificResearchProjectDAO;
import com.nuaa.ec.dao.TeacherAndscientificResearchProjectDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.ScientificResearchProject;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.opensymphony.xwork2.ActionContext;

public class GTScientificResearchProjectAuditAction implements RequestAware {
	/**
	 * 取出团队成员
	 */
	public String getAllMembersOfProject() {
		return "success";
	}

	/**
	 * 审核团队中的成员
	 */
	public void doCheckOut_person() {

	}

	/**
	 * 审核团队
	 */
	public void doCheckOut_project() {
		List<ScientificResearchProject> checkoutList = new ArrayList<ScientificResearchProject>();
		List<ScientificResearchProject> checkoutNotList = new ArrayList<ScientificResearchProject>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot = this.checkOutIDsNot.split(",");
		ScientificResearchProject scientificResearchProject = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] != null && ids[i].length() != 0) {
				scientificResearchProject = this.scientificResearchProjectDAO
						.findById(ids[i]);
				// 修改checkout 标志
				if (scientificResearchProject != null) {
					scientificResearchProject.setCheckout("1");
					checkoutList.add(scientificResearchProject);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				scientificResearchProject = this.scientificResearchProjectDAO
						.findById(idsNot[i]);
				if (scientificResearchProject != null) {
					scientificResearchProject.setCheckout("2");
					checkoutList.add(scientificResearchProject);
					checkoutNotList.add(scientificResearchProject);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (scientificResearchProjectDAO.updateCheckoutStatus(checkoutList)
					&& scientificResearchProjectDAO
							.cascadeUpdateCheckOutOfMembers(checkoutNotList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getAllRecord() {
		Transaction tx = null;
		boolean isDivided = false;
		if (this.isDivided != null && this.isDivided.trim().equals("true")) {
			isDivided = true;
		}
		String pageSize_s = null;
		if (session.get("pageSize_GTSRP") != null) {
			pageSize_s = (String) (session.get("pageSize_GTSRP") + "");
			if (pageSize_s != null && pageSize_s.trim().length() != 0) {
				pageSize_GTSRP = Integer.parseInt(pageSize_s);
			}
		}
		System.out.println(pageSize_GTSRP);
		this.request.put("SRPProjectList", this.scientificResearchProjectDAO
				.getAllRecordWithCondition_RT(pageIndex, pageSize_GTSRP,
						(String) session.get("foredate_GTSRP"),
						(String) session.get("afterdate_GTSRP"),
						((Teacher) session.get("teacher")).getResearchLab(),
						(String) session.get("checkout_GTSRP"), isDivided));
		try {
			tx = this.TARProjectDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}

	private String foredate_GTSRP;
	private String afterdate_GTSRP;
	private Integer operstatus;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize_GTSRP = 1;
	private String checkout_GTSRP;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private String isDivided;
	private ResearchLabDAO researchDAO = new ResearchLabDAO();
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private ScientificResearchProjectDAO scientificResearchProjectDAO = new ScientificResearchProjectDAO();
	private TeacherAndscientificResearchProject TARProject = new TeacherAndscientificResearchProject();
	private TeacherAndscientificResearchProjectDAO TARProjectDAO = new TeacherAndscientificResearchProjectDAO();

	public String getForedate_GTSRP() {
		return foredate_GTSRP;
	}

	public void setForedate_GTSRP(String foredate_GTSRP) {
		this.foredate_GTSRP = foredate_GTSRP;
		session.put("foredate_GTSRP", foredate_GTSRP);
	}

	public String getAfterdate_GTSRP() {
		return afterdate_GTSRP;
	}

	public void setAfterdate_GTSRP(String afterdate_GTSRP) {
		this.afterdate_GTSRP = afterdate_GTSRP;
		session.put("afterdate_GTSRP", afterdate_GTSRP);
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
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

	public int getPageSize_GTSRP() {
		return pageSize_GTSRP;
	}

	public void setPageSize_GTSRP(int pageSize_GTSRP) {
		this.pageSize_GTSRP = pageSize_GTSRP;
		session.put("pageSize_GTSRP", pageSize_GTSRP);
	}

	public TeacherAndscientificResearchProject getTARProject() {
		return TARProject;
	}

	public void setTARProject(TeacherAndscientificResearchProject tARProject) {
		TARProject = tARProject;
	}

	public String getIsDivided() {
		return isDivided;
	}

	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}

	public String getCheckout_GTSRP() {
		return checkout_GTSRP;
	}

	public void setCheckout_GTSRP(String checkout_GTSRP) {
		this.checkout_GTSRP = checkout_GTSRP;
		session.put("checkout_GTSRP", checkout_GTSRP);
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
}
