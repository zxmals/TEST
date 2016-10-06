package com.nuaa.ec.science.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherAndinvitedExpertsSpeechDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech;
import com.opensymphony.xwork2.ActionContext;

public class TeacherAndinvitedExpertsSpeechAuditAction implements RequestAware {
	public void doCheckOutTask() {
//		this.getResearchLabList();
		String[] ids = this.checkOutIDs.split(",");
		List<TeacherAndinvitedExpertsSpeech> checkoutList = new ArrayList<TeacherAndinvitedExpertsSpeech>();
		TeacherAndinvitedExpertsSpeech TAExpertSpeech = null;
		for (int i = 0; i < ids.length; i++) {
			TAExpertSpeech = this.TAInviteExpertsSpeechDAO.findById(Integer
					.parseInt(ids[i]));
			// 修改checkout 标志
			TAExpertSpeech.setCheckOut("1");
			checkoutList.add(TAExpertSpeech);
		}
		// 将待审核的项目传向后台
		try {
			if (TAInviteExpertsSpeechDAO.updateCheckoutStatus(checkoutList)) {
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
	public String getTAExpertSpeechList() {
		Transaction tx=this.TAInviteExpertsSpeechDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchlab_TAES") == null) {
			session.put("selectedResearchlab_TAES", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TAES") == null) {
			session.put("pageSize_TAES", 1);
		}
		try {
			this.request.put("TA_InviteExpertSpeechList",
					this.TAInviteExpertsSpeechDAO.findAllWithCondition(
							pageIndex, (Integer) session.get("pageSize_TAES"),
							(String) session.get("foredate_TAES"),
							(String) session.get("afterdate_TAES"),
							(ResearchLab) session
									.get("selectedResearchlab_TAES"),
							(String) session.get("checkOutStatus_TAES")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			this.setOperstatus(-1);
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}

	public String getTAExpertSpeechAfterDivide() {
		Transaction tx = this.TAInviteExpertsSpeechDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchlab_TAES") == null) {
			session.put("selectedResearchlab_TAES", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TAES") == null) {
			session.put("pageSize_TAES", 1);
		}
		try {
			this.request.put("TA_InviteExpertSpeechList", this.TAInviteExpertsSpeechDAO
					.getTAExpertSpeechListAfterDivided(pageIndex,
							(Integer) session.get("pageSize_TAES"),
							(String) session.get("foredate_TAES"),
							(String) session.get("afterdate_TAES"),
							(ResearchLab) session
									.get("selectedResearchlab_TAES"),
							(String) session.get("checkOutStatus_TAES")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			this.setOperstatus(-1);
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}

	public String execute() throws Exception {
		return "success";
	}

	private int pageIndex = 1;
	private int pageSize_TAES;
	private String foredate_TAES;
	private String afterdate_TAES;
	private ResearchLab researchlab_TAES;
	private TeacherAndinvitedExpertsSpeechDAO TAInviteExpertsSpeechDAO = new TeacherAndinvitedExpertsSpeechDAO();
	private String checkOutStatus_TAES="0";
	private String checkOutIDs;
	private int operstatus;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_TAES() {
		return pageSize_TAES;
	}

	public void setPageSize_TAES(int pageSize_TAES) {
		this.pageSize_TAES = pageSize_TAES;
		session.put("pageSize_TAES", pageSize_TAES);
	}

	public String getForedate_TAES() {
		return foredate_TAES;
	}

	public void setForedate_TAES(String foredate_TAES) {
		this.foredate_TAES = foredate_TAES;
		session.put("foredate_TAES", foredate_TAES);
	}

	public String getAfterdate_TAES() {
		return afterdate_TAES;
	}

	public void setAfterdate_TAES(String afterdate_TAES) {
		this.afterdate_TAES = afterdate_TAES;
		session.put("afterdate_TAES", afterdate_TAES);
	}

	public ResearchLab getResearchlab_TAES() {
		return researchlab_TAES;
	}

	public void setResearchlab_TAES(ResearchLab researchlab_TAES) {
		this.researchlab_TAES = researchlab_TAES;
		session.put("selectedResearchlab_TAES", researchlab_TAES);
	}

	public String getCheckOutStatus_TAES() {
		return checkOutStatus_TAES;
	}

	public void setCheckOutStatus_TAES(String checkOutStatus_TAES) {
		this.checkOutStatus_TAES = checkOutStatus_TAES;
		session.put("checkOutStatus_TAES", checkOutStatus_TAES);
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
}
