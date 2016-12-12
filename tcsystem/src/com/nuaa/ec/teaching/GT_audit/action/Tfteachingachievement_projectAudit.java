package com.nuaa.ec.teaching.GT_audit.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfteachingAchievementProjectDAO;
import com.nuaa.ec.dao.TfteachingPaperProjectDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingAchievementProject;
import com.nuaa.ec.model.TfteachingPaperProject;
import com.nuaa.ec.model.Tfterm;

public class Tfteachingachievement_projectAudit implements RequestAware,SessionAware{
	public void doCheckOutTask(){
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");	
		List<TfteachingAchievementProject> checkoutList = new ArrayList<TfteachingAchievementProject>();
		List<TfteachingAchievementProject> checkoutYESList = new ArrayList<TfteachingAchievementProject>();
		List<TfteachingAchievementProject> checkoutNOTList = new ArrayList<TfteachingAchievementProject>();

		TfteachingAchievementProject tfteachingAchievementProject = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].length() != 0 && ids[i] != null) {
				tfteachingAchievementProject = this.tfteachingAchievementProjectDAO.findById(ids[i]);
				if (tfteachingAchievementProject!=null) {
					tfteachingAchievementProject.setCheckOut("1");;
					checkoutList.add(tfteachingAchievementProject);
					checkoutYESList.add(tfteachingAchievementProject);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				tfteachingAchievementProject = this.tfteachingAchievementProjectDAO.findById(idsNot[i]);
				if (tfteachingAchievementProject!=null) {
					tfteachingAchievementProject.setCheckOut("2");
					checkoutList.add(tfteachingAchievementProject);
					checkoutNOTList.add(tfteachingAchievementProject);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tfteachingAchievementProjectDAO.updateCheckOutStatus(checkoutList) && tfteachingAchievementProjectDAO.cascadeUpdateProjectMembersCheckout(checkoutYESList,"1")&&tfteachingAchievementProjectDAO.cascadeUpdateProjectMembersCheckout(checkoutNOTList,"2")) {
				ServletActionContext.getResponse().getWriter().write("succ");
			}else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String getAllRecord_project() throws Exception{
		Transaction tx = null;
		if ((Integer)session.get("pageSize_TFTA") == null) {
			session.put("pageSize_TFTA", 1);
		}
		if ((Tfterm)session.get("term_TFTA") == null) {
			session.put("term_TFTA", new Tfterm());
		}
		try {
			this.request.put("TFteachingAchievement", this.tfteachingAchievementProjectDAO.getTFteachingAchievementProject(pageIndex,
					(Integer) session.get("pageSize_TFTA"),
					(Tfterm) session.get("term_TFTA"),
					(String)session.get("checkOutStatus_TFTA"),
					((Teacher)session.get("teacher")).getDepartment(),
					false
					));
			tx = tfteachingAchievementProjectDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			this.setOperstatus(-1);
			tx.rollback();
			throw e;
		}
		
		return "success";
	}
	
	public String  getAllRecord_projectAfterDivide(){
		Transaction tx = this.tfteachingAchievementProjectDAO.getSession().beginTransaction();
		if ((Integer)session.get("pageSize_TFTA") == null) {
			session.put("pageSize_TFTA", 1);
		}
		if ((Tfterm)session.get("term_TFTA") == null) {
			session.put("term_TFTA", new Tfterm());
		}
		try {
			this.request.put("TFteachingAchievement", this.tfteachingAchievementProjectDAO.getTFteachingAchievementProject(pageIndex,
					(Integer) session.get("pageSize_TFTA"),
					(Tfterm) session.get("term_TFTA"),
					(String)session.get("checkOutStatus_TFTA"),
					((Teacher)session.get("teacher")).getDepartment(),
					true
					));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		
		return "success";
		
	}
	
	public String execute() throws Exception{
		return "success";
	}
	
	private int pageIndex = 1;
	private int pageSize_TFTA;
	private Department department_TFTA;
	private int operstatus;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private TfteachingAchievementProjectDAO tfteachingAchievementProjectDAO = new TfteachingAchievementProjectDAO();
	private String checkOutStatus_TFTA;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private Tfterm term_TFTA;
	
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}


	public int getPageIndex() {
		return pageIndex;
	}


	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}


	public int getPageSize_TFTA() {
		return pageSize_TFTA;
	}


	public void setPageSize_TFTA(int pageSize_TFTA) {
		this.pageSize_TFTA = pageSize_TFTA;
		session.put("pageSize_TFTA", pageSize_TFTA);
	}


	public Department getDepartment_TFTA() {
		return department_TFTA;
	}


	public void setDepartment_TFTA(Department department_TFTA) {
		this.department_TFTA = department_TFTA;
	}


	public int getOperstatus() {
		return operstatus;
	}


	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	public TfteachingAchievementProjectDAO getTfteachingAchievementProjectDAO() {
		return tfteachingAchievementProjectDAO;
	}

	public void setTfteachingAchievementProjectDAO(
			TfteachingAchievementProjectDAO tfteachingAchievementProjectDAO) {
		this.tfteachingAchievementProjectDAO = tfteachingAchievementProjectDAO;
	}

	public String getCheckOutStatus_TFTA() {
		return checkOutStatus_TFTA;
	}


	public void setCheckOutStatus_TFTA(String checkOutStatus_TFTA) {
		this.checkOutStatus_TFTA = checkOutStatus_TFTA;
		session.put("checkOutStatus_TFTA", checkOutStatus_TFTA);
	}


	public String getCheckOutID() {
		return checkOutIDs;
	}


	public void setCheckOutID(String checkOutID) {
		this.checkOutIDs = checkOutID;
	}


	public String getCheckOutIDsNot() {
		return checkOutIDsNot;
	}


	public void setCheckOutIDsNot(String checkOutIDsNot) {
		this.checkOutIDsNot = checkOutIDsNot;
	}

	public Tfterm getTerm_TFTA() {
		return term_TFTA;
	}

	public void setTerm_TFTA(Tfterm term_TFTA) {
		this.term_TFTA = term_TFTA;
		session.put("term_TFTA", term_TFTA);
	}

	public String getCheckOutIDs() {
		return checkOutIDs;
	}

	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	

}
