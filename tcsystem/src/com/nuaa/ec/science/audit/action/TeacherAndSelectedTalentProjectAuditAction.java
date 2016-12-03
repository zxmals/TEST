package com.nuaa.ec.science.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherAndselectedTalentProjectDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndselectedTalentProject;
import com.opensymphony.xwork2.ActionContext;

public class TeacherAndSelectedTalentProjectAuditAction implements RequestAware {
	public void doCheckOutTask(){
		List<TeacherAndselectedTalentProject> checkoutList=new ArrayList<TeacherAndselectedTalentProject>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TeacherAndselectedTalentProject teacherAndSelectedTalentProject = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				teacherAndSelectedTalentProject = this.TASTProjectDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(teacherAndSelectedTalentProject!=null){
					teacherAndSelectedTalentProject.setCheckOut("3");
					checkoutList.add(teacherAndSelectedTalentProject);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				teacherAndSelectedTalentProject=this.TASTProjectDAO.findById(Integer.parseInt(idsNot[i]));
				if(teacherAndSelectedTalentProject!=null){
					teacherAndSelectedTalentProject.setCheckOut("2");
					checkoutList.add(teacherAndSelectedTalentProject);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TASTProjectDAO.updateCheckoutStatus(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getTASTalentProListAfterDivide() {
		Transaction tx = this.TASTProjectDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchLab_TAST") == null) {
			session.put("selectedResearchLab_TAST", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TAST") == null) {
			session.put("pageSize_TAST", 1);
		}
		try {
			this.request.put("TAS_talentProList", this.TASTProjectDAO
					.getTASTalentProListsAfterDivided(pageIndex,
							(Integer) session.get("pageSize_TAST"),
							(String) session.get("foredate_TAST"),
							(String) session.get("afterdate_TAST"),
							(ResearchLab) session
									.get("selectedResearchLab_TAST"),
							(String) session.get("checkOutStatus_TAST")));
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

	public String getTASTalentProjectInfo() {
		Transaction tx = this.TASTProjectDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchLab_TAST") == null) {
			session.put("selectedResearchLab_TAST", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TAST") == null) {
			session.put("pageSize_TAST", 1);
		}
		try {
			this.request.put("TAS_talentProList", this.TASTProjectDAO
					.findAllWithCondition(pageIndex, (Integer) session
							.get("pageSize_TAST"), (String) session
							.get("foredate_TAST"), (String) session
							.get("afterdate_TAST"), (ResearchLab) session
							.get("selectedResearchLab_TAST"), (String) session
							.get("checkOutStatus_TAST")));
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
		/*
		 * if (researchLab == null) { researchLab = new ResearchLab(); }
		 */
		/* session.put("selectedReserchLab_TARR", researchLab); */
		try {
			this.request.put("researchLabList_TAST", researchLabDAO.findAll());
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
		return "success";
	}

	private int pageIndex = 1;
	private int pageSize_TAST;
	private ResearchLabDAO researchLabDAO = new ResearchLabDAO();
	private TeacherAndselectedTalentProjectDAO TASTProjectDAO = new TeacherAndselectedTalentProjectDAO();
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private ResearchLab researchLab_TAST;
	private String foredate_TAST;
	private String afterdate_TAST;
	private int operstatus;
	private Map<String, Object> request;
	private String checkOutStatus_TAST = "0";
	private String checkOutIDs;
	private String checkOutIDsNot;
	public String getCheckOutIDs() {
		return checkOutIDs;
	}
	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}
	public String getCheckOutStatus_TAST() {
		return checkOutStatus_TAST;
	}

	public void setCheckOutStatus_TAST(String checkOutStatus_TAST) {
		this.checkOutStatus_TAST = checkOutStatus_TAST;
		session.put("checkOutStatus_TAST", checkOutStatus_TAST);
	}

	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	public String getForedate_TAST() {
		return foredate_TAST;
	}

	public void setForedate_TAST(String foredate_TAST) {
		this.foredate_TAST = foredate_TAST;
		session.put("foredate_TAST", foredate_TAST);
	}

	public String getAfterdate_TAST() {
		return afterdate_TAST;
	}

	public void setAfterdate_TAST(String afterdate_TAST) {
		this.afterdate_TAST = afterdate_TAST;
		session.put("afterdate_TAST", afterdate_TAST);
	}

	public ResearchLab getResearchLab_TAST() {
		return researchLab_TAST;
	}

	public void setResearchLab_TAST(ResearchLab researchLab_TAST) {
		this.researchLab_TAST = researchLab_TAST;
		session.put("selectedResearchLab_TAST", researchLab_TAST);
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_TAST() {
		return pageSize_TAST;
	}

	public void setPageSize_TAST(int pageSize_TAST) {
		this.pageSize_TAST = pageSize_TAST;
		session.put("pageSize_TAST", pageSize_TAST);
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public String getCheckOutIDsNot() {
		return checkOutIDsNot;
	}
	public void setCheckOutIDsNot(String checkOutIDsNot) {
		this.checkOutIDsNot = checkOutIDsNot;
	}
}
