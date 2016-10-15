package com.nuaa.ec.science.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherAndacademicWorkDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndacademicWork;
import com.opensymphony.xwork2.ActionContext;

public class TeacherAndacademicWorkAuditAction implements RequestAware {
	public void doCheckOutTask() {
		List<TeacherAndacademicWork> checkoutList=new ArrayList<TeacherAndacademicWork>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TeacherAndacademicWork teacherAndAcademicWork = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				teacherAndAcademicWork = this.TAAcademicWorkDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(teacherAndAcademicWork!=null){
					teacherAndAcademicWork.setCheckOut("1");
					checkoutList.add(teacherAndAcademicWork);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				teacherAndAcademicWork=this.TAAcademicWorkDAO.findById(Integer.parseInt(idsNot[i]));
				if(teacherAndAcademicWork!=null){
					teacherAndAcademicWork.setCheckOut("2");
					checkoutList.add(teacherAndAcademicWork);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TAAcademicWorkDAO.updateCheckoutStatus(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTAAcademicWorkAfterDivide() {
		Transaction tx = this.TAAcademicWorkDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchLab_TAAW") == null) {
			session.put("selectedResearchLab_TAAW", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TAAW") == null) {
			session.put("pageSize_TAAW", 1);
		}
		try {
			this.request.put("TAAcademicWorkList", this.TAAcademicWorkDAO
					.getTAAcademicWorkListAfterDivided(pageIndex,
							(Integer) session.get("pageSize_TAAW"),
							(String) session.get("foredate_TAAW"),
							(String) session.get("afterdate_TAAW"),
							(ResearchLab) session.get("selectedResearchLab_TAAW"),
							(String) session.get("checkOutStatus_TAAW")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}

	public String getTAAcademicWork() {
		Transaction tx = this.TAAcademicWorkDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchLab_TAAW") == null) {
			session.put("selectedResearchLab_TAAW", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TAAW") == null) {
			session.put("pageSize_TAAW", 1);
		}
		try {
			this.request.put("TAAcademicWorkList", this.TAAcademicWorkDAO
					.findAllWithCondition(pageIndex,
							(Integer) session.get("pageSize_TAAW"),
							(String) session.get("foredate_TAAW"),
							(String) session.get("afterdate_TAAW"),
							(ResearchLab) session.get("selectedResearchLab_TAAW"),
							(String) session.get("checkOutStatus_TAAW")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}

	public String execute() throws Exception {
		return "success";
	}

	private int pageIndex = 1;
	private int pageSize_TAAW;
	private String foredate_TAAW;
	private String afterdate_TAAW;
	private ResearchLab researchLab_TAAW;
	private String checkOutStatus_TAAW;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private int operstatus;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private Map<String, Object> request;
	private TeacherAndacademicWorkDAO TAAcademicWorkDAO = new TeacherAndacademicWorkDAO();

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_TAAW() {
		return pageSize_TAAW;
	}

	public void setPageSize_TAAW(int pageSize_TAAW) {
		this.pageSize_TAAW = pageSize_TAAW;
		session.put("pageSize_TAAW", pageSize_TAAW);
	}

	public ResearchLab getResearchLab_TAAW() {
		return researchLab_TAAW;
	}

	public void setResearchLab_TAAW(ResearchLab researchLab_TAAW) {
		this.researchLab_TAAW = researchLab_TAAW;
		session.put("selectedResearchLab_TAAW", researchLab_TAAW);
	}

	public String getCheckOutStatus_TAAW() {
		return checkOutStatus_TAAW;
	}

	public void setCheckOutStatus_TAAW(String checkOutStatus_TAAW) {
		this.checkOutStatus_TAAW = checkOutStatus_TAAW;
		session.put("checkOutStatus_TAAW", checkOutStatus_TAAW);
	}

	public String getCheckOutIDs() {
		return checkOutIDs;
	}

	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}

	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getForedate_TAAW() {
		return foredate_TAAW;
	}

	public void setForedate_TAAW(String foredate_TAAW) {
		this.foredate_TAAW = foredate_TAAW;
		session.put("foredate_TAAW", foredate_TAAW);
	}

	public String getAfterdate_TAAW() {
		return afterdate_TAAW;
	}

	public void setAfterdate_TAAW(String afterdate_TAAW) {
		this.afterdate_TAAW = afterdate_TAAW;
		session.put("afterdate_TAAW", afterdate_TAAW);
	}

	public String getCheckOutIDsNot() {
		return checkOutIDsNot;
	}

	public void setCheckOutIDsNot(String checkOutIDsNot) {
		this.checkOutIDsNot = checkOutIDsNot;
	}
}
