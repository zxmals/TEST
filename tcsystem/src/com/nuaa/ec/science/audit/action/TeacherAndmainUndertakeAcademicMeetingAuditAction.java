package com.nuaa.ec.science.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherAndmainUndertakeAcademicMeetingDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting;
import com.opensymphony.xwork2.ActionContext;

public class TeacherAndmainUndertakeAcademicMeetingAuditAction implements
		RequestAware {
	public void doCheckOutTask() {
		List<TeacherAndmainUndertakeAcademicMeeting> checkoutList=new ArrayList<TeacherAndmainUndertakeAcademicMeeting>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TeacherAndmainUndertakeAcademicMeeting teacherAndUndertakeAcademicMeeting = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				teacherAndUndertakeAcademicMeeting = this.TAUAacdemicMeetingDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(teacherAndUndertakeAcademicMeeting!=null){
					teacherAndUndertakeAcademicMeeting.setCheckOut("3");
					checkoutList.add(teacherAndUndertakeAcademicMeeting);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				teacherAndUndertakeAcademicMeeting=this.TAUAacdemicMeetingDAO.findById(Integer.parseInt(idsNot[i]));
				if(teacherAndUndertakeAcademicMeeting!=null){
					teacherAndUndertakeAcademicMeeting.setCheckOut("2");
					checkoutList.add(teacherAndUndertakeAcademicMeeting);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TAUAacdemicMeetingDAO.updateCheckoutStatus(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getTAUAdemicMettingListAfterDivide(){
		Transaction tx=this.TAUAacdemicMeetingDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchLab_TAUA") == null) {
			session.put("selectedResearchLab_TAUA", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TAUA") == null) {
			session.put("pageSize_TAUA", 1);
		}
		try{
			this.request.put("TAUAcademicMeetingList", this.TAUAacdemicMeetingDAO.getTAUAcademicAfterDivided(pageIndex, (Integer) session
					.get("pageSize_TAUA"), (String) session
					.get("foredate_TAUA"), (String) session
					.get("afterdate_TAUA"), (ResearchLab) session
					.get("selectedResearchLab_TAUA"), (String) session
					.get("checkOutStatus_TAUA")));
			tx.commit();
			this.setOperstatus(1);
		}catch(Exception ex){
			this.setOperstatus(-1);
			ex.printStackTrace();
			tx.rollback();
		}
//		this.getResearchLabList();
		return "success";
	}
	public String getTAUAcademicMeetingList() {
		Transaction tx = this.TAUAacdemicMeetingDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchLab_TAUA") == null) {
			session.put("selectedResearchLab_TAUA", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TAUA") == null) {
			session.put("pageSize_TAUA", 1);
		}
		try {
			this.request.put("TAUAcademicMeetingList", this.TAUAacdemicMeetingDAO
					.findAllWithCondition(pageIndex, (Integer) session
							.get("pageSize_TAUA"), (String) session
							.get("foredate_TAUA"), (String) session
							.get("afterdate_TAUA"), (ResearchLab) session
							.get("selectedResearchLab_TAUA"), (String) session
							.get("checkOutStatus_TAUA")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			this.setOperstatus(-1);
			ex.printStackTrace();
			tx.rollback();
		}
//		this.getResearchLabList();
		return "success";
	}
	public void getResearchLabList() {
		Transaction tx = null;
		try {
			this.request.put("researchLabList_TAUA", researchLabDAO.findAll());
			tx = this.researchLabDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			tx.rollback();
			this.setOperstatus(-1);
			ex.printStackTrace();
		}
	}
	public String execute() throws Exception {
		System.out.println("进入action...");
		return "success";
	}

	private int pageIndex = 1;
	private int pageSize_TAUA;
	private String foredate_TAUA;
	private String afterdate_TAUA;
	private String checkOutStatus_TAUA;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private Map<String, Object> request;
	private ResearchLabDAO researchLabDAO = new ResearchLabDAO();
	private TeacherAndmainUndertakeAcademicMeetingDAO TAUAacdemicMeetingDAO = new TeacherAndmainUndertakeAcademicMeetingDAO();
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private int operstatus;
	private ResearchLab researchLab_TAUA;

	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	public ResearchLab getResearchLab_TAUA() {
		return researchLab_TAUA;
	}

	public void setResearchLab_TAUA(ResearchLab researchLab_TAUA) {
		this.researchLab_TAUA = researchLab_TAUA;
		session.put("selectedResearchLab_TAUA", researchLab_TAUA);
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

	public int getPageSize_TAUA() {
		return pageSize_TAUA;
	}

	public void setPageSize_TAUA(int pageSize_TAUA) {
		this.pageSize_TAUA = pageSize_TAUA;
		session.put("pageSize_TAUA", pageSize_TAUA);
	}

	public String getForedate_TAUA() {
		return foredate_TAUA;
	}

	public void setForedate_TAUA(String foredate_TAUA) {
		this.foredate_TAUA = foredate_TAUA;
		session.put("foredate_TAUA", foredate_TAUA);
	}

	public String getAfterdate_TAUA() {
		return afterdate_TAUA;
	}

	public void setAfterdate_TAUA(String afterdate_TAUA) {
		this.afterdate_TAUA = afterdate_TAUA;
		session.put("afterdate_TAUA", afterdate_TAUA);
	}

	public String getCheckOutStatus_TAUA() {
		return checkOutStatus_TAUA;
	}

	public void setCheckOutStatus_TAUA(String checkOutStatus_TAUA) {
		this.checkOutStatus_TAUA = checkOutStatus_TAUA;
		session.put("checkOutStatus_TAUA", checkOutStatus_TAUA);
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
